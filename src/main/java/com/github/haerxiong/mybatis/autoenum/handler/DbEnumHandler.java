package com.github.haerxiong.mybatis.autoenum.handler;

import cn.hutool.core.util.ClassUtil;
import com.github.haerxiong.mybatis.autoenum.core.DbEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

/**
 * 枚举类型处理器
 * 自动将实体类中类型为DbEnum子类的属性，转换为枚举
 */
@MappedTypes(value = {})
public class DbEnumHandler<E extends Enum<E> & DbEnum> extends BaseTypeHandler<E> {

    public static String enumPackage;

    /**
     * 动态修改@MappedTypes(value = {})的value值
     * 原理：类的注解，是动态代理生成的类，通过获取代理对象，反射修改其值
     */
    static {
        try {
            MappedTypes annotation = DbEnumHandler.class.getAnnotation(MappedTypes.class);
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
            Field memberValues = invocationHandler.getClass().getDeclaredField("memberValues");
            memberValues.setAccessible(true);
            Map values = (Map) memberValues.get(invocationHandler);

            Set<Class<?>> classes = ClassUtil.scanPackageBySuper(enumPackage, DbEnum.class);
            Class[] allDbEnums = classes.toArray(new Class[classes.size()]);

            values.put("value", allDbEnums);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private final Class<E> type;

    /**
     * construct with parameter.
     */
    public DbEnumHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String code = rs.getString(columnName);
        return rs.wasNull() ? null : this.codeOf(this.type, code);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String code = rs.getString(columnIndex);
        return rs.wasNull() ? null : this.codeOf(this.type, code);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String code = cs.getString(columnIndex);
        return cs.wasNull() ? null : this.codeOf(this.type, code);
    }

    public <T extends Enum<?> & DbEnum> T codeOf(Class<T> enumClass, String code) {
        T[] enumConstants = enumClass.getEnumConstants();
        for (T t : enumConstants) {
            if (t.getCode().equals(code)) {
                return t;
            }
        }
        return null;
    }
}
