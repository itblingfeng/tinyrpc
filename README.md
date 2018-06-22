<h2>A simple rpc framework based on netty4</h2>
- tinyrpc module
  - client 用于与server服务提供方通信
  - codec 用于消费方，服务提供方数据流的编解码
  - entity 用于消费方，服务提供方通信的实体
  - proxy 负责对指定接口生成代理
  - serialize 序列化反序列化
  - server 用于与消费方进行通信
  - util 工具类
  - test-consumer 测试用例消费者模块
  - test-interface 测试用例服务接口定义
  - test-pojo 测试用例实体
  - test-provider 测试用例服务提供模块
  - test-service 测试用例服务具体实现模块
  
  <b>此项目为个人学习netty所用</b>
  
  整体流程
  - 服务提供方
    - 1.在指定端口启动netty服务端
    - 2.收到RpcRequest，从缓存中找对应的invoker，如果没有则根据interfaceName找到对应服务ref
    - 2.将服务ref进行包装，封装为invoker并加入缓存
    - 3.进行服务调用并返回调用结果RpcResponse
  - 消费方
    - 1.根据服务接口创建代理
    - 2.通过代理对象方法调用后，新开一个线程启动netty客户端。
    - 3.根据请求的方法向服务端发送RpcRequest。
    - 4.根据RpcRequest中生成的Id将调用线程put进waiterMap并调用LockSupport.park()休眠
    - 5.客户端线程收到响应结果，根据RpcResponse中Id对调用线程进行唤醒
    - 6.调用方收到结果并返回 
    
<h3>测试用例</h3>

consumer
```java
public class Test {
    public static void main(String[] args) {
        LoginService loginService = (LoginService) ProxyUtil.createProxy(LoginService.class);
        User user = new User("blingfeng", "123456");
        boolean isLogin = loginService.login(user);
        System.out.println(isLogin);
    }
}
```

provider

```java
 public class Test {
     public static void main(String[] args) {
         LoginService loginService = new LoginServiceImpl();
         RefUtil.putRef(LoginService.class.getName(), loginService);
         new ServerLoader(28080).start();
     }
 }
```