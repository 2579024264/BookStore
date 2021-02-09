package com.web;

import com.pojo.Cart;
import com.pojo.Order;
import com.pojo.OrderItem;
import com.pojo.User;
import com.service.impl.OrderService;
import com.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet {
    OrderService orderService=new OrderServiceImpl();

    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //先获取购物车对象，获取userId
        Cart cart=(Cart)request.getSession().getAttribute("cart");
        //获取用户信息，以获得userId
        User loginUser=(User) request.getSession().getAttribute("loginUser");
        if (loginUser==null){
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
            return;
        }
        //调用OrderService.createOrder(Cart,userId)生成订单 生成订单最后返回的是订单号
        String orderId=orderService.createOrder(cart,loginUser.getId());
        request.getSession().setAttribute("orderId",orderId);
//        //请求转发到/page/cart/checkout.jsp
//        request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(request,response);
        //防止通过f5刷新重复提交表单使用重定向的方式
        response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
    }

    protected void myorder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户号
        User loginUser=(User)request.getSession().getAttribute("loginUser");
        Integer userId=loginUser.getId();
        //由订单号获取订单
        List<Order> orders =orderService.searchOrder(userId);
        //将订单保存到session域中，前端进行遍历显示
        request.getSession().setAttribute("orders",orders);
        //请求转发到订单页面  这里没有数据的保存操作，所以可以使用请求转发，重定向没必要
        request.getRequestDispatcher("/pages/order/order.jsp").forward(request,response);

    }
    //所有的订单，并且进行管理。仅供管理员
    protected void managerOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询所有的订单
        List<Order> orderList=orderService.searchAllOrder();
        //保存所有的订单于session域中
        request.getSession().setAttribute("orderList",orderList);
        //请求转发到订单管理页面
        request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request,response);
//        response.sendRedirect(request.getContextPath()+"/pages/manager/order_manager.jsp");
    }
    //发货管理
    protected void sendOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取订单号
        String order_id=request.getParameter("order_id");
        //将特定订单的置为发货 1,表示发货
        orderService.sendout(order_id);
//        response.getWriter().write(order_id);
//        response.sendRedirect(request.getContextPath()+"/orderServlet?action=managerOrder");
    }
    //查询指定订单的订单项
    protected void searchOrderItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取订单号
        String order_id=request.getParameter("order_id");
        //通过订单号查询所有的订单项
        List<OrderItem> orderItems=orderService.searchOrderItems(order_id);
        //将订单项保存到session域中
        request.getSession().setAttribute("orderItems",orderItems);
        //请求转发到订单项页面
        request.getRequestDispatcher("/pages/order/order_detail.jsp").forward(request,response);
    }

}
