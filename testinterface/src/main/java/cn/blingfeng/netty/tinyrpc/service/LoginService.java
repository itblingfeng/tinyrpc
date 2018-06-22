package cn.blingfeng.netty.tinyrpc.service;

import cn.blingfeng.netty.tinyrpc.pojo.User;

public interface LoginService {
    boolean login(User user);
}
