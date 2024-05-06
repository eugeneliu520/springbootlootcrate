package com.eugene.springboot.lootcrate.deploy;

import com.eugene.springboot.lootcrate.container.LifecycleContainer;

import java.net.URL;

/**
 * @author eugeneliu
 * @ClassName DeployContext
 * @Description TODO
 * @Date 2024/5/4 3:39 PM
 **/
abstract class AbstractDeployContext implements DeployContext{

    @Override
    final public void init() throws Throwable {
        createClassLoader();
        createContainer();
        initContainer();
    }

    @Override
    public Boolean initContainer() {
        return (Boolean) ExecuteByClassLoader.CHANGE_CLASSLOADER.apply(this.getContainerClassLoader(),()->this.getContainer().init());
    }

    @Override
    public Boolean deploy() {
        return (Boolean) ExecuteByClassLoader.CHANGE_CLASSLOADER.apply(this.getContainerClassLoader(),()->this.getDeployStrategy().deploy(this.getContainer()));
    }

    @Override
    public void createContainer() throws Throwable {
        DependencyStrategy dependencyStrategy = this.getDependencyStrategy();
        LifecycleContainer container = dependencyStrategy.createContainer(this);
        setContainer(container);
    }


    protected abstract  void setContainer(LifecycleContainer container);


    @Override
    public boolean resolveConfig(){
        ClassLoader classLoader = getContainerClassLoader();
        URL resourceUrl = classLoader.getResource("config.properties");
        if (resourceUrl == null) {
            System.err.println("Cannot find config.properties in the JAR file.");
            return;
        }

//        // 读取配置文件
//        Properties props = new Properties();
//        try (InputStream in = resourceUrl.openStream()) {
//            props.load(in);
//        }

    }
}
