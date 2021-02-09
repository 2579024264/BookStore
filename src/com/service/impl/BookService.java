package com.service.impl;

import com.pojo.Book;
import com.pojo.Page;

import java.math.BigDecimal;
import java.util.List;

public interface BookService {
    public void addBook(Book book);

    public void delteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book>queryBooks();

    public Page<Book> page(int pageNo, int pageSize);


    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
