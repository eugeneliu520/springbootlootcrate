package com.eugene.springboot.lootcrate.event;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author eugeneliu
 * @ClassName LootCrateEventBroadcaster
 * @Description TODO
 * @Date 2024/4/30 4:15 PM
 **/
public class LootCrateEventPublisher {
    private List<LootCrateListener> listeners = new ArrayList<>();
    private List<LootCrateEventListenerErrorHandler> errorHandlers = new ArrayList<>();

    public void addListener(LootCrateListener listener){
        listeners.add(listener);
    }

    public void addListeners(List<LootCrateListener> listeners){
        listeners.addAll(listeners);
    }

    public void addErrorHandlers(List<LootCrateEventListenerErrorHandler> handlers){
        errorHandlers.addAll(handlers);
    }

    public boolean removeListener(LootCrateListener listener){
        listeners.remove(listener);
        return true;
    }

    public List<LootCrateListener<?>> getApplicationListeners(final LootCrateEvent event, ResolvableType type){
        List<LootCrateListener<?>> allListeners = new ArrayList<>();
        for(LootCrateListener listener:listeners){
            if (listener.supportsEventType(type)){
                allListeners.add(listener);
            }
        }
        return allListeners;
    }

    public void publishEvent(final LootCrateEvent event) {
        multicastEvent(event,null);
    }

    public void multicastEvent(final LootCrateEvent event, @Nullable ResolvableType eventType) {
        ResolvableType type = (eventType != null ? eventType : ResolvableType.forInstance(event));
        Executor executor = null;
        for (LootCrateListener<?> listener : getApplicationListeners(event, type)) {
            if (executor != null) {
                executor.execute(() -> invokeListener(listener, event));
            }else {
                invokeListener(listener, event);
            }
        }
    }

    protected void invokeListener(LootCrateListener<?> listener, LootCrateEvent event) {
        List<LootCrateEventListenerErrorHandler> errorHandler = this.errorHandlers;
        if (!CollectionUtils.isEmpty(errorHandlers)) {
            try {
                doInvokeListener(listener, event);
            }
            catch (Throwable err) {
                errorHandlers.forEach(handler->handler.handleError(err));
            }
        }else {
            doInvokeListener(listener, event);
        }
    }

    private void doInvokeListener(LootCrateListener listener, LootCrateEvent event) {
        try {
            listener.onApplicationEvent(event);
        } catch (ClassCastException ex) {
            String msg = ex.getMessage();
            if (msg == null || matchesClassCastMessage(msg, event.getClass())) {
                // Possibly a lambda-defined listener which we could not resolve the generic event type for
                // -> let's suppress the exception.
                Log loggerToUse =  LogFactory.getLog(getClass());
                if (loggerToUse.isTraceEnabled()) {
                    loggerToUse.trace("Non-matching event type for listener: " + listener, ex);
                }
            }
            else {
                throw ex;
            }
        }
    }


    private boolean matchesClassCastMessage(String classCastMessage, Class<?> eventClass) {
        // On Java 8, the message starts with the class name: "java.lang.String cannot be cast..."
        if (classCastMessage.startsWith(eventClass.getName())) {
            return true;
        }
        // On Java 11, the message starts with "class ..." a.k.a. Class.toString()
        if (classCastMessage.startsWith(eventClass.toString())) {
            return true;
        }
        // On Java 9, the message used to contain the module name: "java.base/java.lang.String cannot be cast..."
        int moduleSeparatorIndex = classCastMessage.indexOf('/');
        if (moduleSeparatorIndex != -1 && classCastMessage.startsWith(eventClass.getName(), moduleSeparatorIndex + 1)) {
            return true;
        }
        // Assuming an unrelated class cast failure...
        return false;
    }


}
