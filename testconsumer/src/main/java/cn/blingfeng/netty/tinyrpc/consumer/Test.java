package cn.blingfeng.netty.tinyrpc.consumer;

import cn.blingfeng.netty.tinyrpc.pojo.User;
import cn.blingfeng.netty.tinyrpc.proxy.ProxyUtil;
import cn.blingfeng.netty.tinyrpc.service.LoginService;

public class Test {
    public static void main(String[] args) {
        LoginService loginService = (LoginService) ProxyUtil.createProxy(LoginService.class);
        User user = new User("blingfeng", "123456");
        boolean isLogin = loginService.login(user);
        System.out.println(isLogin);
    }
}
