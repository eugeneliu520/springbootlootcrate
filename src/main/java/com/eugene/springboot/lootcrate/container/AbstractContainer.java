package com.eugene.springboot.lootcrate.container;

import com.eugene.springboot.lootcrate.deploy.DeployContext;
import lombok.Getter;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * @author eugeneliu
 * @ClassName AbstractContainer
 * @Description TODO
 * @Date 2023/1/29 11:56 AM
 **/
@Getter
public abstract class AbstractContainer implements LifecycleContainer {
    /*
     * asee Container # getid ()*/
    protected final String bizCode;
    protected final String bizVersion;
    private boolean enable = true;
    /**
     * ＊容器类加载器
     *
     * @ see Container # getClassLoader ()
     **/
    protected final ClassLoader classLoader;
    /**
     * ＊容器启动参数
     */
    protected final Map<String, Object> options;
    /**
     * ＊容器 ApplicationContext 父 applicationContext
     */
    protected ApplicationContext parentApplicationContext;
    /**
     * 容器对应 ApplicationContext
     **/
    protected GenericContainerSpringContext applicationContext;

    protected final Map<String,AbstractBeanDefinition> importBeanDefinitionMap;
    protected final String bootClass;

    /**
     * ＊容器实现适配器，限定容器类加载器 ClassLoader ，容器实现无需关注 ClassLoader 具体类型
     **/
    public AbstractContainer(ContainerBuilder builder) {
        this.bizCode = builder.getContainerBizCode();
        this.bizVersion = builder.getContainerBizVersion();
        this.classLoader = builder.getContainerClassLoader();
        this.options = builder.getDeployOptions();
        this.parentApplicationContext = builder.getParentApplicationContext();
        this.importBeanDefinitionMap = builder.getImportBeanDefinitionMap();
        this.bootClass = builder.getBootClass();
    }

    @Override
    public boolean init() throws Throwable {
        return initApplicationContext();
    }

    @Override
    public void start() throws Throwable {
        applicationContext.refresh();
        handleImportBean();
    }

    @Override
    public void destroy() {
        applicationContext.close();
    }

    @Override
    public void disable (){
        enable = false;
    }

    @Override
    public boolean enableStatus (){
        return enable;
    }

    protected abstract boolean initApplicationContext() throws Throwable;

    protected abstract void handleImportBean() throws Throwable;

    @Override
    public Object getServiceByName(String serviceName){
        return applicationContext.getBean(serviceName);
    }

}
