package com.eugene.springboot.lootcrate.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.core.ResolvableType;

/**
 * @author eugeneliu
 * @ClassName LootCrateListener
 * @Description TODO
 * @Date 2024/4/30 4:14 PM
 **/
public interface LootCrateListener<E extends LootCrateEvent> {

    /**
     * handle event
     * @param event
     */
    void onApplicationEvent(E event);

    /**
     * check if support event
     * @param eventType
     * @return
     */
    boolean supportsEventType(ResolvableType eventType);
}
