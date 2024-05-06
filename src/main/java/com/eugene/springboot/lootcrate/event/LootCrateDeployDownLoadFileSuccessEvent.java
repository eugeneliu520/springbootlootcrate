package com.eugene.springboot.lootcrate.event;

import java.time.Duration;

/**
 * @author eugeneliu
 * @ClassName LootCrateDeployStartedEvent
 * @Description TODO
 * @Date 2024/5/1 11:58 AM
 **/
public class LootCrateDeployDownLoadFileSuccessEvent {

    private final Duration timeTaken;

    public LootCrateDeployDownLoadFileSuccessEvent(Duration timeTaken) {
        this.timeTaken = timeTaken;
    }
}
