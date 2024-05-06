package com.eugene.springboot.lootcrate.deploy;

import com.eugene.springboot.lootcrate.container.LifecycleContainer;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * @author eugeneliu
 * @ClassName DeployContext
 * @Description TODO
 * @Date 2024/5/4 3:39 PM
 **/
public interface DeployContext {

    void init() throws Throwable;
    Boolean deploy();

    void createClassLoader();
    void createContainer() throws Throwable;

    Boolean initContainer();

    LifecycleContainer getContainer();

    DeployStrategy getDeployStrategy();

    ClassLoader getContainerClassLoader();


    DependencyStrategy getDependencyStrategy();

    String getContainerBizCode();

    String getContainerBizVersion();

    Map<String, Object> getDeployOptions();

    ApplicationContext getParentApplicationContext();

    Map<String, AbstractBeanDefinition> getImportBeanDefinitionMap();

    String getBootClass();

    /**
     * resolveConfig
     * @return
     */
    boolean resolveConfig();
}
