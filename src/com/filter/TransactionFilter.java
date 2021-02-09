package com.filter;

import com.utils.jdbc;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            //先允许完成请求 若请求报错则会抛出错误，不会执行下面的事务提交
            filterChain.doFilter(servletRequest,servletResponse);
            //只有当一个请求完全通过之后才可以提交事务
            jdbc.commitAndClose();//提交事务
        } catch (Exception e) {
            jdbc.rollbackAndClose();//回滚事务
            e.printStackTrace();
            throw new RuntimeException(e);//把异常抛给Tomcat管理展示友好的错误页面
        }
    }

    @Override
    public void destroy() {

    }
}
