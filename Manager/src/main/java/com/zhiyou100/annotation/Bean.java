package com.zhiyou100.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//表示要被工厂实例化的类
@Retention(RetentionPolicy.RUNTIME)
public @interface Bean {
}
