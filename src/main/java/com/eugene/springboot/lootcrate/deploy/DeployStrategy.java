package com.eugene.springboot.lootcrate.deploy;

import com.eugene.springboot.lootcrate.container.LifecycleContainer;

/**
 * @author eugeneliu
 * @ClassName DeployStrategy
 * @Description TODO
 * @Date 2023/1/29 3:44 PM
 **/
public interface DeployStrategy {

    /**
     * deploy
     * @param container
     * @return
     * @throws Throwable
     */
    Boolean deploy (LifecycleContainer container) throws Throwable ;
}
