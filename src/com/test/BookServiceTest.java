package com.test;

import com.pojo.Book;
import com.pojo.Page;
import com.service.impl.BookService;
import com.service.impl.BookServiceIml;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookServiceTest {

    private BookService bookService=new BookServiceIml();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"西游记","罗贯中", new BigDecimal(10),100,2000,null));
    }

    @Test
    public void delteBookById() {
        bookService.delteBookById(21);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22,"王自愿","杨凡", new BigDecimal(10),100,2000,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(20));
    }

    @Test
    public void queryBooks() {
        for (Book querybook :bookService.queryBooks()){
            System.out.println(querybook);
        }
    }
    @Test
    public void Page() {
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }
}