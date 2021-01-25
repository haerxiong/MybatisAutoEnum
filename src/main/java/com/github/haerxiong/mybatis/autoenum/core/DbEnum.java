package com.github.haerxiong.mybatis.autoenum.core;

public interface DbEnum<E extends Enum<?>, T> {

    String getCode();

    String getName();
}
