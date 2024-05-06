package com.eugene.springboot.lootcrate.starter;

import com.eugene.springboot.lootcrate.deploy.DeployManager;
import com.eugene.springboot.lootcrate.deploy.DeployManagerBuilder;
import com.eugene.springboot.lootcrate.event.LootCrateEventPublisher;
import com.eugene.springboot.lootcrate.event.LootCrateEventListenerErrorHandler;
import com.eugene.springboot.lootcrate.event.LootCrateListener;
import com.eugene.springboot.lootcrate.event.LootCrateListenerErrorLoggingHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;

/**
 * @author eugeneliu
 * @ClassName LootCrateAutoconfiguration
 * @Description TODO
 * @Date 2024/4/19 11:41 AM
 **/
@Configuration
@Import({DeployController.class,
        LootCrateInvokeController.class,
        LootCrateShutdownListener.class,
        LootCrateInitApplicationRunner.class,
        LootCrateListenerErrorLoggingHandler.class})
public class LootCrateAutoconfiguration implements ApplicationContextAware {

    private ApplicationContext parentApplicationContext;

    @Autowired
    List<LootCrateEventListenerErrorHandler> errorHandlers;

    @Autowired
    private List<LootCrateListener> lootCrateListeners;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.parentApplicationContext = applicationContext;
    }

    @Bean
    public DeployManager deployManager(){
        LootCrateEventPublisher lootCrateEventPublisher = new LootCrateEventPublisher();
        lootCrateEventPublisher.addListeners(lootCrateListeners);
        lootCrateEventPublisher.addErrorHandlers(errorHandlers);


        DeployManager deployManager = DeployManagerBuilder.getInstance()
                .parentApplicationContext(parentApplicationContext)
                .lootCrateEventPublisher(lootCrateEventPublisher)
                .build();
        return deployManager;
    }
}
