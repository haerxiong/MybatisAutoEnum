使用Mybatis时，提供实体类中枚举，自动转换功能。

使用步骤：
1. maven引入
1. 创建自定义的枚举类，继承DbEnum
1. yml中配置枚举所在的包路径（可选，默认扫描main方法启动类所在包）

``` maven
<!-- https://mvnrepository.com/artifact/com.github.haerxiong/autoenum-spring-boot-starter -->
<dependency>
    <groupId>com.github.haerxiong</groupId>
    <artifactId>autoenum-spring-boot-starter</artifactId>
    <version>1.0.1</version>
</dependency>
```