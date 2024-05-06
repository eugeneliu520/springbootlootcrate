package com.eugene.springboot.lootcrate.deploy;

import com.eugene.springboot.lootcrate.container.ContainerBuilder;
import com.eugene.springboot.lootcrate.container.ContainerImpl;
import com.eugene.springboot.lootcrate.container.LifecycleContainer;

/**
 * @author eugeneliu
 * @ClassName DeployStrategy
 * @Description TODO
 * @Date 2023/1/29 3:44 PM
 **/
public class DependencyStrategyOfUserParentContext implements DependencyStrategy{
    DependencyContext dependencyContext;
    /**
     * deploy
     * @return
     * @throws Throwable
     */
    @Override
    public LifecycleContainer createContainer(DeployContext deployContext) throws Throwable {
        LifecycleContainer container = ContainerBuilder.getInstance()
                .containerBizCode(deployContext.getContainerBizCode())
                .containerBizVersion(deployContext.getContainerBizVersion())
                .parentApplicationContext(deployContext.getParentApplicationContext())
                .bootClass(deployContext.getBootClass())
                .containerClassLoader(deployContext.getContainerClassLoader())
                .deployOptions(deployContext.getDeployOptions())
                .build();
        return container;
    }
}
