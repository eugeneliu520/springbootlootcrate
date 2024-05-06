package com.eugene.springboot.lootcrate.classloader;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * @author eugeneliu
 * @ClassName ContainerClassLoaderFactory
 * @Description TODO
 * @Date 2023/1/29 11:39 AM
 **/
public class ContainerClassLoaderFactory {
    public static ClassLoader create(URL[] urls, ClassLoader parentClassLoader) {
//        List<URL> urls = null;
//        try {
//            urls = ClassPathResolver.resolveBundleClasspath(url);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        ClassLoader classLoader = null;
        try {
            classLoader = new ContainerClassLoaderImpl(urls, parentClassLoader, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return classLoader;
    }
}
