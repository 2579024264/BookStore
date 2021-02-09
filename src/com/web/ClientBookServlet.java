package com.web;

import com.pojo.Book;
import com.pojo.Page;
import com.service.impl.BookService;
import com.service.impl.BookServiceIml;
import com.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet {
    private BookService bookService=new BookServiceIml();
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数pageNo和pageSize
        int pageNo= WebUtils.parse(req.getParameter("pageNo"),1);
        int pageSize= Page.PAGE_SIZE;
        //2.代用BookService.page(pageNo,pageSize)：page对象
        Page<Book>page=bookService.page(pageNo,pageSize);
        page.setUrl("client/bookServlet?action=page");
        //3.保存page对象到request域中
        req.setAttribute("page",page);
        //4.请求转发到page/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/index/index.jsp").forward(req,resp);
    }
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数pageNo和pageSize
        int pageNo= WebUtils.parse(req.getParameter("pageNo"),1);
        int pageSize= Page.PAGE_SIZE;
        int min=WebUtils.parse(req.getParameter("min"),0);
        int max=WebUtils.parse(req.getParameter("max"),Integer.MAX_VALUE);
        //2.代用BookService.page(pageNo,pageSize)：page对象
        Page<Book>page=bookService.pageByPrice(pageNo,pageSize,min,max);
        StringBuilder sb=new StringBuilder("client/bookServlet?action=pageByPrice");
        if (req.getParameter(("min")) !=null){
            sb.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max")!=null){
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString());
        //3.保存page对象到request域中
        req.setAttribute("page",page);
        //4.请求转发到page/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/index/index.jsp").forward(req,resp);
    }

}
