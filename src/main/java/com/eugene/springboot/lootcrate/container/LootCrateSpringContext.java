package com.eugene.springboot.lootcrate.container;

import lombok.NonNull;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author eugeneliu
 * @ClassName ContainerSpringContext
 * @Description TODO
 * @Date 2023/1/29 11:59 AM
 **/
public class LootCrateSpringContext extends AnnotationConfigApplicationContext implements GenericContainerSpringContext {

    public LootCrateSpringContext(ClassLoader classLoader,
                                  Class bootClazz,
                                  DefaultListableBeanFactory beanFactory,
                                  String activeEnvironment,
                                  ApplicationContext parent){
        super(beanFactory);
        setClassLoader(classLoader);
        register(bootClazz);
        getEnvironment().setActiveProfiles(activeEnvironment);
        this.setParent(parent);
    }

    @Override
    protected void prepareBeanFactory(@NonNull ConfigurableListableBeanFactory beanFactory){
        super.prepareBeanFactory(beanFactory);
    }

}
