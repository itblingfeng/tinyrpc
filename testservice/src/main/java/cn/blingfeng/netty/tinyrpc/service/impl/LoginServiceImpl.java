package cn.blingfeng.netty.tinyrpc.service.impl;

import cn.blingfeng.netty.tinyrpc.pojo.User;
import cn.blingfeng.netty.tinyrpc.service.LoginService;

public class LoginServiceImpl implements LoginService {
    @Override
    public boolean login(User user) {
        return "blingfeng".equals(user.getUsername()) && "123456".equals(user.getPassword());
    }
}