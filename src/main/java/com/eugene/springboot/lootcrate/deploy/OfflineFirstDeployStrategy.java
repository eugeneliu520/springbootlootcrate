package com.eugene.springboot.lootcrate.deploy;

import com.eugene.springboot.lootcrate.container.LifecycleContainer;

/**
 * @author eugeneliu
 * @ClassName OfflineFirstDeployStrategy
 * @Description TODO
 * @Date 2023/1/29 3:46 PM
 **/
public class OfflineFirstDeployStrategy implements DeployStrategy {

    public OfflineFirstDeployStrategy() {

    }

    @Override
    public Boolean deploy(LifecycleContainer container) throws Throwable {
        return null;
    }
}
