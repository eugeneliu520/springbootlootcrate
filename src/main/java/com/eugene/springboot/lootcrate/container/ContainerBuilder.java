package com.eugene.springboot.lootcrate.container;

import com.eugene.springboot.lootcrate.deploy.DeployContext;
import com.eugene.springboot.lootcrate.deploy.DeployManager;
import com.eugene.springboot.lootcrate.deploy.DeployManagerBuilder;
import com.eugene.springboot.lootcrate.deploy.DeployManagerImpl;
import com.eugene.springboot.lootcrate.utils.Constants;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.io.IOException;
import java.util.Map;

/**
 * @author eugeneliu
 * @ClassName
 * @Description TODO
 * @Date 2023/1/29 11:58 AM
 **/
public class ContainerBuilder {
    private String containerBizCode;
    private String containerBizVersion;
    private ClassLoader containerClassLoader;
    private Map<String, Object> deployOptions;
    private ApplicationContext parentApplicationContext;
    private Map<String, AbstractBeanDefinition> importBeanDefinitionMap;
    private String bootClass;


    public static ContainerBuilder getInstance() {
        return new ContainerBuilder();
    }

    private ContainerBuilder(){

    }

    public LifecycleContainer build() throws Throwable {
        return new ContainerImpl(this);
    }


    public ContainerBuilder containerBizCode(String containerBizCode) {
        this.containerBizCode = containerBizCode;
        return this;
    }

    public ContainerBuilder containerBizVersion(String containerBizVersion) {
        this.containerBizVersion = containerBizVersion;
        return this;
    }

    public ContainerBuilder containerClassLoader(ClassLoader containerClassLoader) {
        this.containerClassLoader = containerClassLoader;
        return this;
    }

    public ContainerBuilder deployOptions(Map<String, Object> deployOptions) {
        this.deployOptions = deployOptions;
        return this;
    }

    public ContainerBuilder parentApplicationContext(ApplicationContext parentApplicationContext) {
        this.parentApplicationContext = parentApplicationContext;
        return this;
    }

    public ContainerBuilder importBeanDefinitionMap(Map<String, AbstractBeanDefinition> importBeanDefinitionMap) {
        this.importBeanDefinitionMap = importBeanDefinitionMap;
        return this;
    }

    public ContainerBuilder bootClass(String bootClass) {
        this.bootClass = bootClass;
        return this;
    }

    public String getContainerBizCode() {
        return containerBizCode;
    }

    public String getContainerBizVersion() {
        return containerBizVersion;
    }

    public ClassLoader getContainerClassLoader() {
        return containerClassLoader;
    }

    public Map<String, Object> getDeployOptions() {
        return deployOptions;
    }

    public ApplicationContext getParentApplicationContext() {
        return parentApplicationContext;
    }

    public Map<String, AbstractBeanDefinition> getImportBeanDefinitionMap() {
        return importBeanDefinitionMap;
    }

    public String getBootClass() {
        return bootClass;
    }
}
