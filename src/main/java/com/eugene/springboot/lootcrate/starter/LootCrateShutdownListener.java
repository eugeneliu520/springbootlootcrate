package com.eugene.springboot.lootcrate.starter;

import com.eugene.springboot.lootcrate.registry.RuntimeContainerRegistry;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

/**
 * @author eugeneliu
 * @ClassName LootCrateShutdownHook
 * @Description TODO
 * @Date 2024/4/30 11:01 AM
 **/
@Component
public class LootCrateShutdownListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
//        lootcrate给释放
        //1、将register 释放
        //2、设置等待时间
        RuntimeContainerRegistry.releaseContainer();
    }
}
