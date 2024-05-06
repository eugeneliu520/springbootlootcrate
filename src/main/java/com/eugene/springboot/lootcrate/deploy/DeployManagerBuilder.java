package com.eugene.springboot.lootcrate.deploy;

import com.eugene.springboot.lootcrate.event.LootCrateEventPublisher;
import lombok.Getter;
import org.springframework.context.ApplicationContext;


/**
 * @author eugeneliu
 * @ClassName DeployManagerBuilder
 * @Description TODO
 * @Date 2023/1/29 12:06 PM
 **/
@Getter
public class DeployManagerBuilder {
    private ApplicationContext parentApplicationContext;
    private LootCrateEventPublisher lootCrateEventPublisher;

    public static DeployManagerBuilder getInstance() {
        return new DeployManagerBuilder();
    }

    public DeployManagerBuilder parentApplicationContext(ApplicationContext parentApplicationContext) {
        this.parentApplicationContext = parentApplicationContext;
        return this;
    }

    public DeployManagerBuilder lootCrateEventPublisher(LootCrateEventPublisher lootCrateEventPublisher) {
        this.lootCrateEventPublisher = lootCrateEventPublisher;
        return this;
    }

    public DeployManager build() {
        return new DeployManagerImpl(this);
    }

}
