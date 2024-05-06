package com.eugene.springboot.lootcrate.starter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author eugeneliu
 * @ClassName RootCrateWebMvcConfig
 * @Description TODO
 * @Date 2024/4/29 8:14 PM
 **/
@Configuration
public class RootCrateWebMvcConfig implements WebMvcConfigurer {

    @Value("${lootcrate.invokePrefixPath}")
    private  String invokePrefixPath;

    @Value("${lootcrate.invokePrefixPath}")
    private  String serviceName;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        String prefixPath = invokePrefixPath !=null && !"".equals(invokePrefixPath) ?
                invokePrefixPath:serviceName;
                configurer.addPathPrefix(prefixPath, c -> c.isAnnotationPresent(LootCrateControllerMarker.class));
    }

}
