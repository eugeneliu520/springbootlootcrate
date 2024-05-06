package com.eugene.springboot.lootcrate.deploy;

import com.eugene.springboot.lootcrate.container.ContainerBuilder;
import com.eugene.springboot.lootcrate.container.ContainerImpl;
import com.eugene.springboot.lootcrate.container.LifecycleContainer;
import com.eugene.springboot.lootcrate.utils.ClassNameMatchUtils;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author eugeneliu
 * @ClassName DeployStrategy
 * @Description TODO
 * @Date 2023/1/29 3:44 PM
 **/
public class DependencyStrategyOfImportBeans implements DependencyStrategy{
    DependencyContext dependencyContext;
    /**
     * deploy
     * @return
     * @throws Throwable
     */
    @Override
    public LifecycleContainer createContainer(DeployContext deployContext) throws Throwable {
        Map<String, AbstractBeanDefinition> importBeanDefinitionMap = getExportSpringContext(deployContext.getParentApplicationContext(),dependencyContext.getImportFromParentBeans());
        LifecycleContainer container = ContainerBuilder.getInstance()
                .containerBizCode(deployContext.getContainerBizCode())
                .containerBizVersion(deployContext.getContainerBizVersion())
                .bootClass(deployContext.getBootClass())
                .containerClassLoader(deployContext.getContainerClassLoader())
                .deployOptions(deployContext.getDeployOptions())
                .importBeanDefinitionMap(importBeanDefinitionMap)
                .build();
        return container;
    }

    public Map<String, AbstractBeanDefinition> getExportSpringContext(ApplicationContext parentSpringContext, String[] accessParentBeanPattern) {
        Map<String, AbstractBeanDefinition> importBeanDefinitionMap = new HashMap<>();
        String[] beanNames = parentSpringContext.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            Object bean = parentSpringContext.getBean(beanName);
            String classStr = bean.getClass().getName();
            for (String pattern : accessParentBeanPattern) {
                if (ClassNameMatchUtils.isMatch(classStr, pattern)) {
                    AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(bean.getClass(), (Supplier) () -> bean)
//                    f.setbestroyMethodName(" destroy ")
                            .getBeanDefinition();
                    importBeanDefinitionMap.put(beanName, beanDefinition);
//                    log.info(beanName);
                }
            }
        }
        return importBeanDefinitionMap;
    }


}
