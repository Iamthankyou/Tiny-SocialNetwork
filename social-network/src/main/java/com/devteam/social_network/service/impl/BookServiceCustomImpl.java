package com.devteam.social_network.service.impl;

import com.devteam.social_network.domain.Book;
import com.devteam.social_network.repos.BookRepoService;
import com.devteam.social_network.sdo.TopTenBookSdo;
import com.devteam.social_network.service.BookService;
import com.devteam.social_network.service.BookServiceCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceCustomImpl implements BookServiceCustom {
    @Autowired
    BookService bookService;

    @Autowired
    BookRepoService bookRepoService;

    public Book insertBook(Book book){
        return bookService.save(book);
    }
    public List<Book> getListBookByType(String type){
        return bookService.findAll().stream()
                .filter(book -> type.equals(book.getType())).collect(Collectors.toList());
    }

    @Override
    public List<Book> getListBookByName(String name) {
        return bookRepoService.getListBookByName(name);
    }

    @Override
    public Book getBookByName(String name) {
        return bookRepoService.getBookByName(name);
    }

    public Book getBookById(String bookId){
        return bookService.findAll().stream().filter(book -> bookId.equals(book.getISBN())).findFirst().orElse(null);
    }

    @Override
    public List<Book> getListBookByListId(List<String> listId) {
//        List<Book> result = new ArrayList<Book>();
//        for (String id : listId){
//            System.out.println(id);
//            result.add(this.getBookById(id));
//        }
        return bookService.findAll().stream()
                                    .filter(book -> listId.stream()
                                            .anyMatch(isbn -> isbn.equals(book.getISBN())))
                                            .collect(Collectors.toList());
//        return result;
    }

    @Override
    public List<Book> getAllBook() {
        return bookService.findAll();
    }

    @Override
    public List<TopTenBookSdo> getTopTenBookSeller() {
        return bookRepoService.getTopTenBookSeller();
    }
}
