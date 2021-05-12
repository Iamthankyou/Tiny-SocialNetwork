package com.devteam.social_network.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import com.devteam.social_network.domain.ERole;
import com.devteam.social_network.domain.Role;
import com.devteam.social_network.domain.User;
import com.devteam.social_network.payload.request.ForgotRequest;
import com.devteam.social_network.payload.request.LoginRequest;
import com.devteam.social_network.payload.request.NewPassword;
import com.devteam.social_network.payload.request.SignupRequest;
import com.devteam.social_network.payload.response.JwtResponse;
import com.devteam.social_network.payload.response.MessageResponse;
import com.devteam.social_network.payload.response.UserResponse;
import com.devteam.social_network.repos.RoleRepository;
import com.devteam.social_network.repos.UserRepository;
import com.devteam.social_network.security.jwt.JwtUtils;
import com.devteam.social_network.security.services.UserDetailsImpl;
import com.devteam.social_network.service.ForgotService;
import com.sun.mail.imap.Utility;
import io.swagger.models.Response;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private ForgotService forgotService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt,
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByNickName(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(),
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()), signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getGender());

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);


		String email = signUpRequest.getEmail();
		String token = RandomString.make(30);

		try {
			forgotService.updateResetPasswordToken(token, email);
			String resetPasswordLink =  "http://localhost:8998/api/auth/active?token=" + token;
			sendEmailActive(email, resetPasswordLink);
//			model.addAttribute("message", "");

		} catch (UsernameNotFoundException ex) {
			return ResponseEntity.ok(new MessageResponse(ex.getMessage()));
		} catch (MessagingException e) {
			e.printStackTrace();
			return ResponseEntity.ok(new MessageResponse(e.getMessage()));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return ResponseEntity.ok(new MessageResponse(e.getMessage()));
		}


		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@GetMapping("/active")
	public ResponseEntity<?>  showResetPasswordForm(@Param(value = "token") String token){
		User customer = forgotService.getByResetPasswordToken(token);

		if (customer == null) {
			return ResponseEntity.ok(new MessageResponse("Not found customer"));
		}

		try {
			forgotService.updateActive(customer,"true");
		} catch (UsernameNotFoundException ex) {
			return ResponseEntity.ok(new MessageResponse(ex.getMessage()));
		}

		return ResponseEntity.ok(new MessageResponse("User activefully !"));
	}

	@PostMapping("/getUserWithEmail")
	public ResponseEntity<?> getUserWithMail(@Valid @RequestBody ForgotRequest forgotRequest) {
		String email = forgotRequest.getEmail();
		User user = userRepository.findByEmail(email);

		return ResponseEntity.ok(new UserResponse(user.getNickName(),user.getEmail(),user.getFirstName(), user.getLastName(), user.getIsActive(), user.getGender()));
	}


	@PostMapping("/getUserWithUserName")
	public ResponseEntity<?> getUserWithUserName(@Valid @RequestBody UserResponse userResponse) {
		String username = userResponse.getUsername();
		User user = userRepository.findByEmail(username);

		return ResponseEntity.ok(new UserResponse(user.getNickName(),user.getEmail(),user.getFirstName(), user.getLastName(), user.getIsActive(), user.getGender()));
	}


	@PostMapping("/forgot")
	public ResponseEntity<?> forgotUser(@Valid @RequestBody ForgotRequest forgotRequest) {
		String email = forgotRequest.getEmail();
		String token = RandomString.make(30);

		try {
			forgotService.updateResetPasswordToken(token, email);
			String resetPasswordLink =  "http://localhost:8998/api/auth/reset_password?token=" + token;
			sendEmail(email, resetPasswordLink);
//			model.addAttribute("message", "");

		} catch (UsernameNotFoundException ex) {
			return ResponseEntity.ok(new MessageResponse(ex.getMessage()));
		} catch (MessagingException e) {
			e.printStackTrace();
			return ResponseEntity.ok(new MessageResponse(e.getMessage()));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return ResponseEntity.ok(new MessageResponse(e.getMessage()));
		}

		return ResponseEntity.ok(new MessageResponse("Send mail successfully!"));

	}

	@PostMapping("/reset_password")
	public ResponseEntity<?>  showResetPasswordForm(@Param(value = "token") String token, @Valid @RequestBody NewPassword newPassword) {
		User customer = forgotService.getByResetPasswordToken(token);

		if (customer == null) {
			return ResponseEntity.ok(new MessageResponse("Not found customer"));
		}

		String password = newPassword.getPassword();

		try {
			forgotService.updatePassword(customer,password);
		} catch (UsernameNotFoundException ex) {
			return ResponseEntity.ok(new MessageResponse(ex.getMessage()));
		}

		return ResponseEntity.ok(new MessageResponse("Change password successfully"));
	}

	public void sendEmailActive(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("social@gmail.com", "Social Support");
		helper.setTo(recipientEmail);

		String subject = "Here's the link to active user";

		String content = "<p>Hello,</p>"
				+ "<p>Welcome to join SocialMedia </p>"
				+ "<p>Click the link active user:</p>"
				+ "<p><a href=\"" + link + "\">ACTIVE</a></p>"
				+ "<br>"
				+ "<p>Thank you, "
				+ "so much.</p>";

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);
	}

	public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("social@gmail.com", "Social Support");
		helper.setTo(recipientEmail);

		String subject = "Here's the link to reset your password";

		String content = "<p>Hello,</p>"
				+ "<p>You have requested to reset your password.</p>"
				+ "<p>Click the link below to change your password:</p>"
				+ "<p><a href=\"" + link + "\">Change my password</a></p>"
				+ "<br>"
				+ "<p>Ignore this email if you do remember your password, "
				+ "or you have not made the request.</p>";

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);
	}
}
