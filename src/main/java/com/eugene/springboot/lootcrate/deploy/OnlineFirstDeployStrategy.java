package com.eugene.springboot.lootcrate.deploy;

import com.eugene.springboot.lootcrate.container.LifecycleContainer;
import com.eugene.springboot.lootcrate.registry.RuntimeContainerRegistry;

import java.util.concurrent.TimeUnit;

/**
 * @author eugeneliu
 * @ClassName OnlineFirstDeployStrategy
 * @Description TODO
 * @Date 2023/1/29 3:46 PM
 **/
public class OnlineFirstDeployStrategy implements DeployStrategy {

    public OnlineFirstDeployStrategy() {
    }

    //TODO 考虑多线程
    @Override
    public Boolean deploy(LifecycleContainer container) throws Throwable {
        container.start();
        LifecycleContainer toBeRemoveContainer = online(container);
        //替换了，但是要等下才能执行destroy操作，等待在途的请求处理完成，另外路由也需要刷新
        //TODO sleep
        try{
            TimeUnit.SECONDS.sleep(10);
        }catch (InterruptedException e){

        }

        if (toBeRemoveContainer != null){
            toBeRemoveContainer.destroy();
        }
        return Boolean.TRUE;
    }


    public LifecycleContainer online(LifecycleContainer container) throws Throwable {
        return RuntimeContainerRegistry.publishContainer(container);
    }


    public LifecycleContainer offline(LifecycleContainer container) throws Throwable {
        return null;
    }
}
