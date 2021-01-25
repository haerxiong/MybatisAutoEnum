package cn.lw.mybatis.autoenum.core;

import cn.lw.mybatis.autoenum.handler.DbEnumHandler;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisProperties后置处理器，配置MyBatis的枚举处理器typeHandle
 * 相当用户在yml中配置：  mybatis.type-handlers-package
 */
@Configuration
@ConditionalOnClass({TypeHandler.class, MybatisProperties.class})
public class MainConfig implements BeanPostProcessor {

    @Value("${lw.mybatis.enumPackage}")
    String enumPackage;

    public static final String HANDLER_PACKAGE = "cn.lw.handler";

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MybatisProperties) {
            DbEnumHandler.enumPackage = enumPackage;
            MybatisProperties properties = (MybatisProperties) bean;
            String src = properties.getTypeHandlersPackage();
            if (src != null) {
                // mybatis.type-handlers-package可以接受多个路径，分隔符有多种，英文逗号为其中一种
                src += "," + HANDLER_PACKAGE;
            } else {
                src = HANDLER_PACKAGE;
            }
            properties.setTypeHandlersPackage(src);
        }
        return bean;
    }
}
