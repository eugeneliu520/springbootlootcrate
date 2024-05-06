package com.eugene.springboot.lootcrate.event;

/**
 * @author eugeneliu
 * @ClassName LootCrateEventListenErrorHandler
 * @Description TODO
 * @Date 2024/5/1 10:45 AM
 **/
public interface LootCrateEventListenerErrorHandler {
    /**
     * handle error
     * @param t
     */
    void handleError(Throwable t);
}
