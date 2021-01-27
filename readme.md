## 功能：使用后，Mybatis查询结果，自动转换为对应的枚举。

### 一、e.g. 以自带的CommonYesNoEnum类为例：

1. 假设数据库中记录  del字段，值为1
1. 观察变化```mapper.getOne(id).getDel()```

    改造前：
    ```java
    class Student {
        int del;
    }
    ```
    改造后：
    ```java
    class Student {
        CommonYesNoEnum del;
    }
    ```

### 二、使用方法：

1. maven引入
1. 创建自定义的枚举类，继承DbEnum。可以参照common包下的
   ```CommonYesNoEnum```
1. yml中配置枚举所在的包路径（可选，默认扫描main方法启动类所在包）

``` maven
<dependency>
    <groupId>com.github.haerxiong</groupId>
    <artifactId>autoenum-spring-boot-starter</artifactId>
    <version>1.0.1</version>
</dependency>
```

---


