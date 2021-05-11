package com.devteam.social_network.repos.impl;

import com.devteam.social_network.common.DataUtil;
import com.devteam.social_network.domain.Book;
import com.devteam.social_network.repos.BookRepoService;
import com.devteam.social_network.sdo.TopTenBookSdo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepoServiceImpl implements BookRepoService {

    @Autowired
    EntityManager em;
    @Override
    public List<Book> getListBookByName(String name) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ISBN,NAME,AUTHOR,PUBLISH,WIDTH,HEIGHT,PAGE_NUMBER,MASS,PRICE,DISCOUNT,IMAGE_BOOK,TYPE from BOOK ");
        sql.append(" WHERE NAME LIKE N'%"+name+"%' ");
        Query query = em.createNativeQuery(sql.toString(),Book.class);
        List<Book> list = query.getResultList();
        return list;
    }

    @Override
    public Book getBookByName(String name) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ISBN,NAME,AUTHOR,PUBLISH,WIDTH,HEIGHT,PAGE_NUMBER,MASS,PRICE,DISCOUNT,IMAGE_BOOK,TYPE from BOOK ");
        sql.append(" WHERE NAME = N'"+name+ "'");
        Query query = em.createNativeQuery(sql.toString(),Book.class);
        List<Book> list = query.getResultList();
        return list.get(0);
    }

    @Override
    public List<TopTenBookSdo> getTopTenBookSeller() {
        StringBuilder sql = new StringBuilder();
        sql.append(" select top 10 detail_bill.isbn,count(detail_bill.isbn) as sl,author,discount,height,image_book,mass,name,page_number,price,publish,type,width from detail_bill ");
        sql.append(" inner join book on detail_bill.isbn = book.isbn ");
        sql.append(" group by detail_bill.isbn,name,author,discount,height,image_book,mass,name,page_number,price,publish,type,width ");
        sql.append(" order by sl desc ");
        Query query = em.createNativeQuery(sql.toString());
        List<TopTenBookSdo> result = new ArrayList<>();
        List<Object[]> objects = query.getResultList();
        if(!DataUtil.isNullOrEmpty(objects)){
            for(Object[] obj : objects){
                result.add(DataUtil.convertObjectsToClass(obj,new TopTenBookSdo()));
            }
        }
        return result;
    }
}
