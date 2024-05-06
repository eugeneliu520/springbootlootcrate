package com.eugene.springboot.lootcrate.classloader;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * @author eugeneliu
 * @ClassName ContainerClassLoaderImpl
 * @Description TODO
 * @Date 2023/1/29 11:40 AM
 **/
public class ContainerClassLoaderImpl extends BaseClassLoader {
    static {
        registerAsParallelCapable();
    }

    public ContainerClassLoaderImpl(URL[] urls, ClassLoader parentClassLoader, boolean importParentNonClassResources) throws IOException {
        super(urls, parentClassLoader, importParentNonClassResources);
        String[] excludePackagesFromParent = readExcludeParentPackages();
        if (excludePackagesFromParent != null) {
            this.excludeParentPackages = unionPackages(excludePackagesFromParent);
            recordExcludeParentPackages();
        }
    }

    /**
     * ＊打印 excluded packages
     */
    private void recordExcludeParentPackages() {
//        log.warn(" excluded packages from parent classLoader {}:", this.getParent().getClass().getSimpleName());
        for (String excludeParentPackage : this.excludeParentPackages) {
//            log.info("\ t {}", excludeParentPackage);
        }
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            Class<?> loadedClass = findLoadedClass(name);
            //如果未加载，且非 excluded parent packages 中的类，则从父类加载器加载
            if (loadedClass == null && !containsClass(this.excludeParentPackages, name)) {
                loadedClass = tryLoadFromParent(name);
            }
            if (loadedClass == null) {
                loadedClass = loadFromClasspath(name);
            }
            if (resolve) {
                resolveClass(loadedClass);
            }
            return loadedClass;
        }
    }

    private String[] readExcludeParentPackages() throws IOException {
        final Enumeration<URL> resources = this.getResources(" CLASSLOADER _ CONF _ FILE ");
        Set<String> excludeParentPackagesSet = new HashSet<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            try (InputStream inputStream = resource.openStream()) {
                Properties properties = new Properties();
                properties.load(inputStream);
                String excludePackagePackages = properties.getProperty("EXCLUDE_PARENT_PACKAGES_PROP");
                if (excludePackagePackages!= null) {
                    String[] excludePackagePackagesArray = excludePackagePackages.split(",");
                    excludeParentPackagesSet.addAll(Arrays.asList(excludePackagePackagesArray));
                }
            }
        }
        return excludeParentPackagesSet.toArray(new String[0]);
    }


//    @Override
//    public void finalize () {
//        //log.warn(" gabedge collect ");
//    }
}
