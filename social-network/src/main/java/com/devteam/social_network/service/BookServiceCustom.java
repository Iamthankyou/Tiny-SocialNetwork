package com.devteam.social_network.service;

import com.devteam.social_network.domain.Book;
import com.devteam.social_network.sdo.TopTenBookSdo;

import java.util.List;

public interface BookServiceCustom {
    public Book insertBook(Book book);

    public List<Book> getListBookByType(String type);
    public List<Book> getListBookByName(String name);
    public Book getBookByName(String name);
    public Book getBookById(String bookId);
    public List<Book> getListBookByListId(List<String> listId);
    public List<Book> getAllBook();
    public List<TopTenBookSdo> getTopTenBookSeller();
}
