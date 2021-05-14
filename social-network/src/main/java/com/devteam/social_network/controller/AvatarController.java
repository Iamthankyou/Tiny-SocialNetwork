package com.devteam.social_network.controller;

import com.devteam.social_network.domain.User;
import com.devteam.social_network.payload.request.LoginRequest;
import com.devteam.social_network.payload.response.AvatarResponse;
import com.devteam.social_network.payload.response.MessageResponse;
import com.devteam.social_network.payload.response.UserResponse;
import com.devteam.social_network.repos.UserRepository;
import com.devteam.social_network.service.AvatarService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/file")
public class AvatarController {
    @Autowired
    AvatarService avatarService;

    @Autowired
    AuthenticationManager authenticationManager;


    @Autowired
    UserRepository userRepository;

    @PostMapping("upload-file")
    @ApiOperation("upload-file")
    public ResponseEntity<List<String>> uploadFile(@RequestParam("files") MultipartFile[] multipartFile, @RequestHeader (name="Authorization") String token){
        String root = "http://localhost:8998/file/images/";
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        UserDetails userDetail = (UserDetails) authentication.getPrincipal();

        Optional<User> user = userRepository.findByNickName(userDetail.getUsername());


        System.out.print(userDetail.getUsername());
        System.out.print("???");

        if (user != null){
            for (int i = 0 ; i < multipartFile.length ; i++){
                user.get().setAvatar(root+multipartFile[i].getOriginalFilename());
            }

            userRepository.save(user.get());
            return ResponseEntity.status(HttpStatus.OK).body(avatarService.uploadFile(multipartFile));

        }

        return ResponseEntity.notFound().build();

    }

    @GetMapping("images/{photo}")
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable("photo")String photo){
        if(!photo.equals("") || photo != null){
            try{
                Path path = Paths.get(System.getProperty("user.dir"));
                path = Paths.get(path.toString(), "../").normalize().resolve("Uploads");

                Path filename = Paths.get(path.toString(),photo);
                System.out.println(filename);
                byte[] buffer = Files.readAllBytes(filename);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok().contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType("image/png"))
                        .body(byteArrayResource);
            }catch (Exception e){

            }
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/getAvatarWithUserName")
    public ResponseEntity<?> getAvatarWithUserName(@Valid @RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        Optional<User> user = userRepository.findByNickName(username);

        return ResponseEntity.ok(new AvatarResponse(user.get().getAvatar()));
    }

}
