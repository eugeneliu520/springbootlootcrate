package com.eugene.springboot.lootcrate.container;

import com.eugene.springboot.lootcrate.deploy.DeployContext;
import com.eugene.springboot.lootcrate.deploy.DeployContextImpl;
import com.eugene.springboot.lootcrate.utils.Constants;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @author eugeneliu
 * @ClassName ContainerImpl
 * @Description TODO
 * @Date 2023/1/29 11:58 AM
 **/
public class ContainerImpl extends AbstractContainer {
    /**
     * ＊容器实现适配器，限定容器类加载器 ClassLoader ，容器实现无需关注 ClassLoader 具体类型
     *
     * @param builder
     */
    public ContainerImpl(ContainerBuilder builder) throws Throwable {
        super(builder);
    }

    @Override
    public boolean initApplicationContext() throws Throwable {
//        ConfigurableEnvironment configurableEnvironment = getConfig();
        Class<?> bootClazz = this.classLoader.loadClass(bootClass);
        final DefaultListableBeanFactory containerBeanFactory = new DefaultListableBeanFactory();
        if (!CollectionUtils.isEmpty(importBeanDefinitionMap)){
            importBeanDefinitionMap.forEach((beanName, beanDefinition) ->
                    containerBeanFactory.registerBeanDefinition(beanName, beanDefinition)
            );
        }

        LootCrateSpringContext containerSpringContext = new LootCrateSpringContext(this.classLoader,
                bootClazz,
                containerBeanFactory,
                this.getActiveEnvironment(),
                this.parentApplicationContext
                );
        applicationContext = containerSpringContext;
        return true;
    }

    private ConfigurableEnvironment getConfig() {
        ConfigurableEnvironment configurableEnvironment = null;
        try {
            configurableEnvironment = ConfigurableEnvironmentFactory.create(this.getClassLoader());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configurableEnvironment;
    }

    @Override
    public void handleImportBean() throws Throwable {
        if (importBeanDefinitionMap == null ){
            return ;
        }
        for (String beanName: importBeanDefinitionMap.keySet()) {
            AbstractBeanDefinition beanDefinition = importBeanDefinitionMap.get(beanName);
            DefaultListableBeanFactory factory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();
            factory.registerBeanDefinition(beanName, beanDefinition);
        }
    }

    @Override
    public String getActiveEnvironment() {
        return String.valueOf(this.options.get(Constants.ACTIVE_ENVIRONMENT));
    }
}
