package com.eugene.springboot.lootcrate.deploy;

import com.eugene.springboot.lootcrate.classloader.ContainerClassLoaderFactory;
import com.eugene.springboot.lootcrate.container.ContainerImpl;
import com.eugene.springboot.lootcrate.event.LootCrateDeployContainerInitSuccessEvent;
import com.eugene.springboot.lootcrate.event.LootCrateDeployStartEvent;
import com.eugene.springboot.lootcrate.event.LootCrateDeploySuccessEvent;
import com.eugene.springboot.lootcrate.event.LootCrateEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

import java.net.URL;
import java.time.Duration;

/**
 * @author eugeneliu
 * @ClassName DeployManagerImpl
 * @Description TODO
 * @Date 2023/1/29 3:41 PM
 **/
@Slf4j
public class DeployManagerImpl implements DeployManager {
    private final ApplicationContext parentApplicationContext;
    private final LootCrateEventPublisher lootCrateEventPublisher;

    public DeployManagerImpl(DeployManagerBuilder deployManagerBuilder) {
        lootCrateEventPublisher = deployManagerBuilder.getLootCrateEventPublisher();
        parentApplicationContext = deployManagerBuilder.getParentApplicationContext();
//        this.containerInitAfterProcessors = deployManagerBuilder.getContainerInitAfterProcessors();
    }

    /**
     * 在宿主启动的时候做些事情，比如上报之类的
     */
    @Override
    public void init() {

    }

    /**
     * 当前是否有正在部署的？要检童，怎么判断当前有正在部署的...
     */
    @Override
    public Boolean deploy(final DeployRequest deployRequest) throws Throwable {
        long startTime = System.nanoTime();
        DeployContext deployContext = new DeployContextImpl(deployRequest);
        Duration timeTakenToStart = Duration.ofNanos(System.nanoTime() - startTime);
        lootCrateEventPublisher.publishEvent(new LootCrateDeployStartEvent(deployContext,"",timeTakenToStart));
        //TODO
        DependencyContext dependencyContext = new DependencyContext();
        //TODO 导入策略实现 基于dependencyContext
        Duration timeTakenToInit = Duration.ofNanos(System.nanoTime() - startTime);
        deployContext.init();
        lootCrateEventPublisher.publishEvent(new LootCrateDeployContainerInitSuccessEvent(deployContext,"",timeTakenToInit));
        deployContext.deploy();
        Duration timeTakenToSuccess = Duration.ofNanos(System.nanoTime() - startTime);
        lootCrateEventPublisher.publishEvent(new LootCrateDeploySuccessEvent(deployContext,"",timeTakenToSuccess));
        return true;
    }


}
