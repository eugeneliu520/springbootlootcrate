package com.eugene.springboot.lootcrate.event;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * @author eugeneliu
 * @ClassName LootCrateListenerErrorLoggingHandler
 * @Description TODO
 * @Date 2024/5/1 10:56 AM
 **/
@Component
public class LootCrateListenerErrorLoggingHandler implements LootCrateEventListenerErrorHandler {
    private static final Log logger = LogFactory.getLog(LootCrateListenerErrorLoggingHandler.class);

    @Override
    public void handleError(Throwable throwable) {
        logger.warn("Error calling ApplicationEventListener", throwable);
    }
}
