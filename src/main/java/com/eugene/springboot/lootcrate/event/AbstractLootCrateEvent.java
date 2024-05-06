package com.eugene.springboot.lootcrate.event;

import com.eugene.springboot.lootcrate.deploy.DeployContext;
import com.eugene.springboot.lootcrate.deploy.DeployContextImpl;

import java.time.Duration;

/**
 * @author eugeneliu
 * @ClassName LootCrateEvent
 * @Description TODO
 * @Date 2024/4/30 4:14 PM
 **/
public abstract class AbstractLootCrateEvent extends LootCrateEvent{

    /** System time when the event happened. */
    private final long timestamp;
    private final Duration timeTaken;
    private final DeployContext deployContext;

    public AbstractLootCrateEvent(DeployContext deployContext, Object source, Duration timeTaken){
        super(source);
        this.deployContext = deployContext;
        this.timestamp = System.currentTimeMillis();
        this.timeTaken = timeTaken;
    }
}
