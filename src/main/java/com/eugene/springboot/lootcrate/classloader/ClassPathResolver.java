package com.eugene.springboot.lootcrate.classloader;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author eugeneliu
 * @ClassName ClassPathResolver
 * @Description TODO
 * @Date 2023/1/29 11:39 AM
 **/
public class ClassPathResolver {
    public static final String directoryPath = "";
    public static final String libPrefixPath = "";

    public static List<URL> resolveBundleClasspath(URL url) throws Exception{
        return resolveBundleClasspath(url,directoryPath,libPrefixPath);
    }

    public static List<URL> resolveBundleClasspath(URL url,String directoryPath,String libPrefixPath) throws Exception{
//        final Archive archive = createArchiveFromUrl(url);
        List<URL> urls = new ArrayList<>();
//        final Iterator<Archive> archiveIterator = archive.getNestedArchives(entry -> {
//            if (entry.isDirectory()){
//                return entry.getName().equals(directoryPath);
//            }
//            return entry.getName().startsWith(libPrefixPath);
//        },null);
//        while (archiveIterator.hasNext()){
//            urls.add(archiveIterator.next().getUrl());
//        }
        return urls;
    }

//    public static final Archive createArchiveFromUrl(URL url) throws Exception {
//        File root = new File(url.toURI().getSchemeSpecificPart());
//        return new JarFileArchive(root);
//    }
}
