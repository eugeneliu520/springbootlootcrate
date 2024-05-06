package com.eugene.springboot.lootcrate.starter;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author eugeneliu
 * @ClassName RootCrateInvokeController
 * @Description TODO
 * @Date 2024/4/29 8:12 PM
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@RestController
@RequestMapping
public @interface LootCrateControllerMarker {
    /**
     */
    @AliasFor(annotation = RequestMapping.class)
    String value() default "";
}
