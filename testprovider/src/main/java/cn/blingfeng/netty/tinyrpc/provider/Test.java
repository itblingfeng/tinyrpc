package cn.blingfeng.netty.tinyrpc.provider;

import cn.blingfeng.netty.tinyrpc.server.RefUtil;
import cn.blingfeng.netty.tinyrpc.server.ServerLoader;
import cn.blingfeng.netty.tinyrpc.service.LoginService;
import cn.blingfeng.netty.tinyrpc.service.impl.LoginServiceImpl;

public class Test {
    public static void main(String[] args) {
        LoginService loginService = new LoginServiceImpl();
        RefUtil.putRef(LoginService.class.getName(), loginService);
        new ServerLoader(28080).start();
    }
}
