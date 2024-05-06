package com.eugene.springboot.lootcrate.deploy;

import java.util.function.BiFunction;

/**
 * @author eugeneliu
 * @ClassName ExecuterByClassLoader
 * @Description TODO
 * @Date 2024/5/4 4:25 PM
 **/
public interface ExecuteByClassLoader {
    Object execute() throws Throwable;

    BiFunction<ClassLoader,ExecuteByClassLoader,Object> CHANGE_CLASSLOADER = (classLoader, executor) ->{
        ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            Thread.currentThread().setContextClassLoader(classLoader);
            return executor.execute();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            Thread.currentThread().setContextClassLoader(originalClassLoader);
        }
    };
}
