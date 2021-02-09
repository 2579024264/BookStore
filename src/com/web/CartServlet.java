package com.web;

import com.google.gson.Gson;
import com.pojo.Book;
import com.pojo.Cart;
import com.pojo.CartItem;
import com.service.impl.BookService;
import com.service.impl.BookServiceIml;
import com.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {
    private BookService bookService=new BookServiceIml();
    /**
     * 添加商品
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("加入购物车");
//        System.out.println(request.getParameter("id"));
        //获取请求的参数 商品编号
        int id= WebUtils.parse(request.getParameter("id"),0);
        //调用bookService.queryBookById(id):Book得到图书的信息
        Book book=bookService.queryBookById(id);
        //把图书信息转换成CartItem商品项
        CartItem cartItem=new CartItem(book.getName(),book.getId(),1,book.getPrice(),book.getPrice());
        //将购物车对象保存在Session域中
        Cart cart=(Cart)request.getSession().getAttribute("cart");
        if (cart==null){
            cart=new Cart();
        }
        //掉用Cart.addItem(CartItem);添加商品项
        cart.addItem(cartItem);
        //最后一个添加的商品保存在一个单独的Session域中
        request.getSession().setAttribute("lastName",cartItem.getName());
        //重定向回原来商品所在的地址页面
                //在请求头中有一个Referer属性，该属性保存的是发起请求的浏览器地址。
        response.sendRedirect(request.getHeader("Referer"));
    }
    //删除商品项
    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=WebUtils.parse(request.getParameter("id"),0);
        //获取购物车对象
        Cart cart=(Cart)request.getSession().getAttribute("cart");
        //删除
        cart.deleteItem(id);
        //重定向回到购物车展示页面
        response.sendRedirect(request.getHeader("Referer"));
    }
    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //1.获取session中的购物城对象
        Cart cart=(Cart) request.getSession().getAttribute("cart");
//        清空购物车
        cart.clear();
        //重定向回到原来购物车的展示页面
        response.sendRedirect(request.getHeader("Referer"));
    }
    //修改商品数量
    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //获取请求的参数。商品编号和数量
        int id=WebUtils.parse(request.getParameter("id"),0);
        int count=WebUtils.parse(request.getParameter("count"),1);
    //获取Session域中的cart对象
    Cart cart=(Cart)request.getSession().getAttribute("cart");
    if (cart!=null){
        //修改商品数量
        cart.updateCount(id,count);
        //重定向回到之前的页面
        response.sendRedirect(request.getHeader("Referer"));
    }

    }
    protected void ajaxItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求的参数 商品编号
        int id= WebUtils.parse(request.getParameter("id"),0);
        //调用bookService.queryBookById(id):Book得到图书的信息
        Book book=bookService.queryBookById(id);
        //把图书信息转换成CartItem商品项
        CartItem cartItem=new CartItem(book.getName(),book.getId(),1,book.getPrice(),book.getPrice());
        //判断session域中的cart是否为空，保证所有的商品项都保存在一个购物车中
        Cart cart=(Cart)request.getSession().getAttribute("cart");
        if (cart==null){
            cart=new Cart();
        }
        //掉用Cart.addItem(CartItem);添加商品项
        cart.addItem(cartItem);
        //将购物车对象保存在Session域中
        request.getSession().setAttribute("cart",cart);
        //最后一个添加的商品保存在一个单独的Session域中
        request.getSession().setAttribute("lastName",cartItem.getName());
        //返回购物车总的商品数量和最后一个添加的商品名称
        Map<String,Object> resultMap=new HashMap<String,Object>();
        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());
        Gson gson=new Gson();
//        将map对象转换为json数据格式
        String resultMapJsonString=gson.toJson(resultMap);
        //响应ajax
        response.getWriter().write(resultMapJsonString);

    }
}
