package com.eugene.springboot.lootcrate.container;

/**
 * @author eugeneliu
 * @ClassName Lifecycle
 * @Description TODO
 * @Date 2023/1/29 12:03 PM
 **/
public interface Lifecycle {
    boolean init () throws Throwable ;

    void start () throws Throwable ;

    /**
     *销毁方法，应该由容器运行时环境负责托管调用。*/
    void destroy () throws Throwable ;

    /**
     *失效，应该由容器运行时环境负责托管调用。*/
    void disable () ;

    boolean enableStatus ();

}
