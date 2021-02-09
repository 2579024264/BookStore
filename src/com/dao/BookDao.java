package com.dao;

import com.pojo.Book;

import java.math.BigDecimal;
import java.util.List;

public interface BookDao {
    public int addBook(Book book );

    public int deletBookById(Integer id);

    public int updateBook(Book book);
    //图书查询
    public Book queryBookById(Integer id);
    //图书查询
    public List<Book> queryBooks();

    public Integer queryForPageTotalCount();

    public List<Book>queryForPageItems(int begin,int pageSize);

    //通过价格区间进行图书查询

    Integer queryForPageTotalCountByPrice(int min, int max);

    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
