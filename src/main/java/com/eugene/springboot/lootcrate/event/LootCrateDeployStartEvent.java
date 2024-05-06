package com.eugene.springboot.lootcrate.event;

import com.eugene.springboot.lootcrate.deploy.DeployContext;
import com.eugene.springboot.lootcrate.deploy.DeployContextImpl;

import java.time.Duration;

/**
 * @author eugeneliu
 * @ClassName LootCrateDeployStartedEvent
 * @Description TODO
 * @Date 2024/5/1 11:58 AM
 **/
public class LootCrateDeployStartEvent extends AbstractLootCrateEvent{

    public LootCrateDeployStartEvent(DeployContext deployContext, Object source, Duration timeTaken) {
        super(deployContext,source,timeTaken);
    }
}
