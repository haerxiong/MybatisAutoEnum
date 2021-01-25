package cn.lw.mybatis.autoenum.core;

public interface DbEnum<E extends Enum<?>, T> {

    String getCode();

    String getName();
}
