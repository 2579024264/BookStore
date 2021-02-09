package com.web;

import com.google.gson.Gson;
import com.pojo.User;
import com.service.impl.UserService;
import com.service.impl.UserServiceImpl;
import com.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    private UserService userService=new UserServiceImpl();
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求的参数
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        //调用userService.login()处理登录业务
        User loginUser=userService.login(new User(null,username,password,null));
        //如果等于null,说明登录失败!
        if(loginUser==null){
            //把错误信息，和回显的表单项信息，保存到Request域中
            request.setAttribute("msg","输入的账号或者密码错误");
            request.setAttribute("username",username);
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else{
            //登录成功将用户信息保存再session域中。
            request.getSession().setAttribute("loginUser",loginUser);
            //登录成功后将用户名保存在session域中。
            request.getSession().setAttribute("user",username);
            //登录成功 跳转到登录成功页面
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
        }
    }

    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String email=request.getParameter("email");
        String code=request.getParameter("code");

        //使用beautyutils的目的：因为从客户端获取的数据，要注入对象，可是当参数比较多的时候，就需要一直使用set方法进行注入
        //这时候使用beautyutils就可以很好的直接将参数进行注入。

        //这里的参数可以换成，request.getParameterMap(),传值类型为Map.
        //使用泛型作为返回值，结果不用进行类型转换。
        User user=WebUtils.copyParamToBean(request.getParameterMap(),new User());
        //获取Session中的验证码
        String token=(String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除Session域中的验证码
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        //验证码判断
        if (token!=null&&token.equalsIgnoreCase(code)){
            //判断账号是否被注册过
            if (userService.exitsUsername(user.getUsername())){
                request.setAttribute("msg","用户名已存在");
                request.setAttribute("username",username);
                request.setAttribute("password",password);
                request.setAttribute("code",code);
                //跳转到注册页面
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
            }else{
                //可用  进行注册
                userService.regisUser(user);
                //注册成功，跳到注册成功页面
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request,response);
            }

        }else{
            //跳回注册页面
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
        }

    }
    protected void ajaxExistsUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //获取请求的参数username
        String username=request.getParameter("username");
        //调用book.serviceUsername();
        boolean exitsUsername=userService.exitsUsername(username);
        //把返回的结果封装成为Map对象
        Map<String,Object> resultMap=new HashMap<>();
        //数据除了是一个bean对象可以转换为json外，还可以通过创建一个Map对象的方式来传递数据
        resultMap.put("existUsername",exitsUsername);
        Gson gson=new Gson();
        String json=gson.toJson(resultMap);
        response.getWriter().write(json);
    }
    protected void loginout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1.销毁Session中的用户登录信息
        request.getSession().invalidate();
//        2.重定向到首页
        response.sendRedirect(request.getContextPath());
    }


}
