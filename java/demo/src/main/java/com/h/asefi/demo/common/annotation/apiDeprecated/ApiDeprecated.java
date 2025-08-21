package com.h.asefi.demo.common.annotation.apiDeprecated;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiDeprecated {
    String since() default "";

    String link() default "";
}
