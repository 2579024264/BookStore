package com.test;

import com.dao.BookDao;
import com.dao.impl.BookDaoImpl;
import com.pojo.Book;
import com.pojo.Page;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {
    private BookDao bookdao=new BookDaoImpl();

    @Test
    public void addBook() {
        bookdao.addBook(new Book(null,"lhh","张荣超",new BigDecimal(9999),111111,0,null));
    }

    @Test
    public void deletBookById() {
    }

    @Test
    public void updateBook() {
        bookdao.updateBook(new Book(21,"张浩毅","张荣超",new BigDecimal(9999),222222,0,null));

    }

    @Test
    public void queryBookById() {
        System.out.println(bookdao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook:bookdao.queryBooks()){
            System.out.println(queryBook);
        }
    }
    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookdao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
//        System.out.println(bookdao.queryForPageItems(8, Page.PAGE_SIZE));
        for (Book book:bookdao.queryForPageItems(8,Page.PAGE_SIZE)){
            System.out.println(book);
        }

    }

}