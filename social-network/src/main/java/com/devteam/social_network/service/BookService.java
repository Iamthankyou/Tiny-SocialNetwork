package com.devteam.social_network.service;


import com.devteam.social_network.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface BookService extends JpaRepository<Book,Long> {
}
