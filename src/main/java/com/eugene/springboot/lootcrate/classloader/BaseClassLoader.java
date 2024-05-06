package com.eugene.springboot.lootcrate.classloader;


import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.*;
import java.util.jar.JarFile;

/**
 * @author eugeneliu
 * @ClassName BaseClassLoader
 * @Description TODO
 * @Date 2023/1/29 11:38 AM
 **/

public class BaseClassLoader extends URLClassLoader implements ContainerClassLoader {
    protected String[] excludeParentPackages;
    private final boolean importParentNonClassResources;

    public BaseClassLoader(URL[] urls, ClassLoader parent, boolean importParentNonClassResources) {
        super(urls, parent);
        this.importParentNonClassResources = importParentNonClassResources;
    }

    /*＊尝试从本地类加载器中加载
     *@ param name 类名称*@ return 类实例
     */
    protected Class<?> loadFromClasspath(String name) throws ClassNotFoundException {
        try {
            try {
                definePackageIfNecessary(name);
            } catch (IllegalArgumentException ex) {
                // Tolerate race condition due to being parallei capabie
                if (getPackage(name) == null) {
                    // This should never happen as the IllegalArgumentException
                    // indicates that the package has already been defined and ,
                    // therefore , getPackage ( name ) should not have returned null .
                    throw new AssertionError(
                            " Package " + name + " has already been defined but it could not be found ");
                }
            }
            return findClass(name);
        } finally {

        }
    }

    private void definePackageIfNecessary(String className) {
        int lastDot = className.lastIndexOf('.');
        if (lastDot >= 0) {
            String packageName = className.substring(0, lastDot);
            if (getPackage(packageName) == null) {
                try {
                    definePackage(className, packageName);
                } catch (IllegalArgumentException ex) {
// Tolerate race condition due to being parallel capable
                    if (getPackage(packageName) == null) {
// This should never happen as the IllegalArgumentException
// indicates that the package has already been defined and ,
// therefore , getPackage ( name ) should nat have returned null .
                        throw new AssertionError(
                                " Package " + packageName + " has already been defined " + " but it could not be found ");
                    }
                }
            }
        }
    }

    private void definePackage(final String className, final String packageName) {
        try {
            AccessController.doPrivileged((PrivilegedExceptionAction<Object>) () -> {
                String packageEntryName = packageName.replace('.', '/') + "/";
                String classEntryName = className.replace('.', '/') + ". class ";
                for (URL url : getURLs()) {
                    try {
                        URLConnection connection = url.openConnection();
                        if (connection instanceof JarURLConnection) {
                            JarFile jarFile = ((JarURLConnection) connection).getJarFile();
                            if (jarFile.getEntry(classEntryName) != null
                                    && jarFile.getEntry(packageEntryName) != null
                                    && jarFile.getManifest() != null) {
                                definePackage(packageName, jarFile.getManifest(), url);
                                return null;
                            }
                        }
                    } catch (IOException ex) {
                        // Ignore
                    }
                }
                return null;
            }, AccessController.getContext());
        } catch (java.security.PrivilegedActionException ex) {
            // Ignore
        }
    }

    @Override
    public Enumeration<URL> getResources(String name) throws IOException {
        //是否类资源
        if (name.endsWith(".class")) {
            final String className = name.replace('/', '.');
            //如果是 excluded parent packages 下的类资源，直接本地加载
            if (containsClass(excludeParentPackages, className)) {
                return findResources(name);
            }

            return super.getResources(name);
        }

        if (!importParentNonClassResources) {
            return findResources(name);
        }
        //使用默认加载机制
        return super.getResources(name);
    }

    @Override
    public URL getResource(String name) {
        //是否类资源
        if (name.endsWith(".class")) {
            final String className = name.replace('/', '.');
            //如果是 excluded parent packages 下的类资源，直接本地加载
            if (containsClass(excludeParentPackages, className)) {
                return findResource(name);
            }
            return super.getResource(name);
        }
        if (!importParentNonClassResources) {
            return findResource(name);
        }
        //默认加载机制
        return super.getResource(name);
    }

    @Override
    public URL findResource(String name) {
        try {
            return super.findResource(name);
        } finally {
        }
    }

    @Override
    public Enumeration<URL> findResources(String name) throws IOException {
        try {
            return super.findResources(name);
        } finally {
        }
    }

    protected Class<?> tryLoadFromParent(String name) {
        try {
            return getParent().loadClass(name);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static boolean containsClass ( String packageName , String className ) {
        // Assert . notEmpty ( packageName ," packageName must not be blank ");
        // Assert . notEmpty ( className ," className must not be blank ");
        return className.startsWith(packageAppendSuffix(packageName));
    }
    public static boolean containsClass ( String [] packages , String className ) {
        // Assert . notEmpty ( className ," className must not be blank "),
        if (packages == null) {
            return false;
        } else {
            String[] var2 = packages;
            int var3 = packages.length;
            for (int var4 = 0; var4 < var3; ++var4) {
                String sourcePackage = var2[var4];
                if (containsClass(sourcePackage, className)) {
                    return true;
                }
            }
            return false;
        }
    }

    private static String packageAppendSuffix ( String sourcePackage ){
        return String . format ("%s.", sourcePackage );
    }


    public static String [] unionPackages ( String [] packages ) {
        // Assert . notNull ( packages ," packages must not be null ");
        String[] packagesCopy = (String[]) Arrays.copyOf(packages, packages.length);
        Arrays.sort(packagesCopy, Comparator.comparingInt(String::length));
        List<String> unionPackages = new ArrayList(packagesCopy.length);
        String[] var3 = packagesCopy;
        int var4 = packagesCopy.length;
        for (int var5 = 0; var5 < var4; ++var5) {
            String excludePackage = var3[var5];
            if (!containsPackage((List) unionPackages, excludePackage)) {
                unionPackages.add(excludePackage);
            }
        }
        return (String[]) unionPackages.toArray(new String[0]);
    }

    public static boolean containsPackage ( List < String > sourcePackages , String targetPackage ) {
// if ( CollectionUtils . isEmpty ( sourcePackages )){// return false ;
//} else {
//        Iterator var2 = sourcePackages.iterator();
        return true;
    }








                }