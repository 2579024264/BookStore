package com.web;

import com.pojo.Book;
import com.pojo.Page;
import com.service.impl.BookService;
import com.service.impl.BookServiceIml;
import com.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet{
    private BookService bookService=new BookServiceIml();
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.获取请求的参数  封装成为Book对象
        Book book= WebUtils.copyParamToBean(req.getParameterMap(),new Book());
//        2.调用BookService.addBook()保存图书
        bookService.addBook(book);
//        3.跳转到图书列表页面
//        req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req,resp);
        //这里使用重定向的方法
        //req.getContextPath()方法获取工程名
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }



    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.获取请求的参数ID，图书编程
        int id=WebUtils.parse(req.getParameter("id"),0);
//          2.调用bookService.deleteBookById(),删除图书
        bookService.delteBookById(id);
//          3.重定向回图书列表哦管理页面
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }


    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //保存修改图书的操作
//        1.获取请求的参数=封装成为Book对象
            Book book=WebUtils.copyParamToBean(req.getParameterMap(),new Book());
//        2.调用BookService.updateBook(book)；修改图书
        bookService.updateBook(book);
//        3.重定向回图书列表管理页面  /book/manager/bookServlet?action=list
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page$pageNo="+req.getParameter("pageNo"));
    }
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取图书id
        int id = WebUtils.parse(req.getParameter("id"), 0);
        //2.调用bookService.queryBookid()返回图书对象；
        Book book=bookService.queryBookById(id);
        //3.把book对象保存在request域中
        req.setAttribute("book",book);
        //4.请求转发到pages/manager/book_edit.jsp页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.通过BookService查询全部图书
        List<Book> bookList=bookService.queryBooks();
        //2.把全部图书保存到Request域中
        req.setAttribute("books",bookList);
        //3.请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数pageNo和pageSize
        int pageNo=WebUtils.parse(req.getParameter("pageNo"),1);
        int pageSize= Page.PAGE_SIZE;
        //2.代用BookService.page(pageNo,pageSize)：page对象
        Page<Book>page=bookService.page(pageNo,pageSize);
        page.setUrl("manager/bookServlet?action=page");
        //3.保存page对象到request域中
        req.setAttribute("page",page);
        //4.请求转发到page/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

}
