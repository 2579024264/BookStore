package com.service.impl;

import com.pojo.User;

public interface UserService {
    //需要的业务，一个业务一个方法

    /**
     * 注册用户
     * @param user
     */
    public void regisUser(User user);
    /**
     * 登录
     */
    public User login(User user);

    /**
     * 检查  用户名是否存在
     * @param username
     * @return 返回true表示用户存在，返回false表示用户不存在
     */
    public boolean exitsUsername(String username);

}
