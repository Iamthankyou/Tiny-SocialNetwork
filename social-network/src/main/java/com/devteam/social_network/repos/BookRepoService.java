package com.devteam.social_network.repos;

import com.devteam.social_network.domain.Book;
import com.devteam.social_network.sdo.TopTenBookSdo;

import java.util.List;

public interface BookRepoService {
    public List<Book> getListBookByName(String name);
    public Book getBookByName(String name);
    public List<TopTenBookSdo> getTopTenBookSeller();
}
