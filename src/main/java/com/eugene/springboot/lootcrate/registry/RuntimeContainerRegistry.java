package com.eugene.springboot.lootcrate.registry;

import com.eugene.springboot.lootcrate.container.LifecycleContainer;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author eugeneliu
 * @ClassName RuntimeContainerRgistry
 * @Description TODO
 * @Date 2023/1/29 3:58 PM
 **/
public class RuntimeContainerRegistry {
    private static final Map<String, LifecycleContainer> containersByType = new ConcurrentHashMap();

    public static LifecycleContainer publishContainer(LifecycleContainer lifecycleContainer) {
        LifecycleContainer oldLifecycleContainerTemp = containersByType.get(lifecycleContainer.getBizCode());
//        lifecycleContainer.setDeployment(deployment);
        containersByType.put(lifecycleContainer.getBizCode(), lifecycleContainer);
        return oldLifecycleContainerTemp;
    }

    public static LifecycleContainer removeContainer(String containerType) {
        LifecycleContainer lifecycleContainer = containersByType.remove(containerType);
        return lifecycleContainer;
    }

    public static LifecycleContainer getContainer(String containerType) throws Exception {
        LifecycleContainer container = containersByType.get(containerType);
        if (container.enableStatus()){
            return container;
        }else{
            throw new Exception();
        }
    }

    public static List<LifecycleContainer> getAllLifecycleContainer() {
        return null;
    }

    public static boolean releaseContainer() {
        //TODO
        //遍历，挨个释放，调用容器的状态，设置为释放状态
        for(LifecycleContainer container:containersByType.values()){
            container.disable();
        }
        //暂停5s
        try{
            TimeUnit.SECONDS.sleep(10);
        }catch (InterruptedException e){

        }

        try {
            for(LifecycleContainer container:containersByType.values()){
                container.destroy();
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        containersByType.clear();
        return true;
    }
}
