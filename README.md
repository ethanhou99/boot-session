# Session Controller

## Requirements
实现一个Session控制器(实现client端)。这里的Session 可以想象为一些有时间长度的会话请求。会话起停需要通过向服务器提供的http 接口发送请求来控制。

## Environment
Java JDK 11
Spring boot 2.0.3
IDE Eclipse

## 运行步骤
1. 导入整个maven项目
2. 启动Pythonserver
2. 打开8081端口
3. 运行SessionControllerApplication的main函数 (Run as > Java Application) 
4. 打开 http://localhost:8080 进行操作

## Functional completeness
- 代码覆盖率 89%
- [x] 根据接口描述和协议类型发送 session start/stop请求给服务端
- [x] 可支持同时(向同一个server)并发发送多个请求
- [x] Stop请求需要根据session 时长来发送
- [x] 创建日志来记录session 状态 (发送时间,发送url,和body,结果,连接信息)
- [x] 支持动态调整并发参数
- [x] 支持动态调整所有session时长
- [x] 有异常处理
- [x] 可优雅停止
- [ ] 支持异步发送 ，并提供方案说明 （准备使用Ajax去进行异步操作，由于时间关系暂未支持）

## Flowchart
<img src="https://github.com/ethanhou99/boot-session/blob/master/flowchart.png"/>

## Implementation and Algorithms
- 全局利用ConcurrentHashMap来

## Interface
<img src="https://github.com/ethanhou99/boot-session/blob/master/Template.png"/>

## Build Success Info.
```
[INFO] Scanning for projects...
[INFO] 
[INFO] ---------------------< com.javainuse:boot-session >---------------------
[INFO] Building Spring-Boot-Session-Controller 2.0.3.RELEASE
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-clean-plugin:3.0.0:clean (default-clean) @ boot-session ---
[INFO] Deleting /Users/ethanhou/Desktop/SessionController/boot-session/target
[INFO] 
[INFO] --- maven-resources-plugin:3.0.1:resources (default-resources) @ boot-session ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] Copying 2 resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.7.0:compile (default-compile) @ boot-session ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 9 source files to /Users/ethanhou/Desktop/SessionController/boot-session/target/classes
[INFO] 
[INFO] --- maven-resources-plugin:3.0.1:testResources (default-testResources) @ boot-session ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 0 resource
[INFO] 
[INFO] --- maven-compiler-plugin:3.7.0:testCompile (default-testCompile) @ boot-session ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-surefire-plugin:2.21.0:test (default-test) @ boot-session ---
[INFO] 
[INFO] --- maven-jar-plugin:3.0.2:jar (default-jar) @ boot-session ---
[INFO] Building jar: /Users/ethanhou/Desktop/SessionController/boot-session/target/boot-session-2.0.3.RELEASE.jar
[INFO] 
[INFO] --- spring-boot-maven-plugin:2.0.3.RELEASE:repackage (default) @ boot-session ---
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  5.333 s
[INFO] Finished at: 2020-08-03T01:49:52-04:00
[INFO] ------------------------------------------------------------------------
```
