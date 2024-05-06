package com.eugene.springboot.lootcrate.deploy;

import com.eugene.springboot.lootcrate.classloader.ContainerClassLoaderFactory;
import com.eugene.springboot.lootcrate.container.ContainerImpl;
import com.eugene.springboot.lootcrate.container.LifecycleContainer;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * @author eugeneliu
 * @ClassName DeployContext
 * @Description TODO
 * @Date 2024/5/1 4:31 PM
 **/
public class DeployContextImpl extends AbstractDeployContext{
    final private JarFile deployFile;
    final private URL deployUrls;
    final private DeployStrategy deployStrategy;
    private DependencyStrategy dependencyStrategy;
    final private String containerBizCode;
    final private String containerBizVersion;
    final private Map<String ,Object > deployOptions;
    final private String bootClass;
    private ClassLoader containerClassLoader;
    private LifecycleContainer container;
    private ApplicationContext parentApplicationContext;
    private Map<String, AbstractBeanDefinition> importBeanDefinitionMap;

    public DeployContextImpl(DeployRequest deployRequest) throws Exception {
        this.containerBizCode = deployRequest.getDeployContainerBizCode();
        this.containerBizVersion = deployRequest.getDeployContainerBizVersion();
        this.deployOptions = deployRequest.getOptions();
        this.deployStrategy = DeployStrategyFactory.strategy(deployRequest);
        this.dependencyStrategy = null;
        try {
            File deployJarFile = new File(deployRequest.getJarFileUrl());
            this.deployFile = new JarFile(deployJarFile);
            this.deployUrls = deployJarFile.toURI().toURL();
            this.bootClass = getMainClass(this.deployFile);
        } catch (IOException e) {
            throw new Exception();
        }
    }

    public static String getMainClass(JarFile deployFile) throws IOException {
        Manifest manifest = deployFile.getManifest();
        Attributes attributes = manifest.getMainAttributes();
        String mainClass = attributes.getValue("Main-Class");
        return mainClass;
    }

    public JarFile getDeployFile() {
        return deployFile;
    }

    public URL getDeployUrls() {
        return deployUrls;
    }

    @Override
    public DeployStrategy getDeployStrategy() {
        return deployStrategy;
    }

    @Override
    public ClassLoader getContainerClassLoader() {
        return containerClassLoader;
    }

    @Override
    public LifecycleContainer getContainer() {
        return container;
    }

    @Override
    public ApplicationContext getParentApplicationContext() {
        return parentApplicationContext;
    }

    public void setParentApplicationContext(ApplicationContext parentApplicationContext) {
        this.parentApplicationContext = parentApplicationContext;
    }

    @Override
    public String getBootClass() {
        return bootClass;
    }

    @Override
    public Map<String, AbstractBeanDefinition> getImportBeanDefinitionMap() {
        return importBeanDefinitionMap;
    }

    public void setImportBeanDefinitionMap(Map<String, AbstractBeanDefinition> importBeanDefinitionMap) {
        this.importBeanDefinitionMap = importBeanDefinitionMap;
    }

    @Override
    public String getContainerBizCode() {
        return containerBizCode;
    }

    @Override
    public String getContainerBizVersion() {
        return containerBizVersion;
    }

    @Override
    public Map<String, Object> getDeployOptions() {
        return deployOptions;
    }

    @Override
    public void createClassLoader() {
        final ClassLoader classLoader = ContainerClassLoaderFactory.create(new URL[]{getDeployUrls()}, this.getClass().getClassLoader());
        this.containerClassLoader = classLoader;
    }

    @Override
    public DependencyStrategy getDependencyStrategy() {
        return dependencyStrategy;
    }

    @Override
    protected void setContainer(LifecycleContainer container) {
        this.container = container;
    }


}
