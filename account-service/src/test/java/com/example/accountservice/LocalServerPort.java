package com.example.accountservice;

import org.springframework.beans.factory.annotation.Value;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD,METHOD,PARAMETER,ANNOTATION_TYPE})
@Retention(RUNTIME)
@Documented
@Value("${local.server.port}")
public @interface LocalServerPort{

}
