package com.eugene.springboot.lootcrate.starter;

import com.eugene.springboot.lootcrate.deploy.DeployManager;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author eugeneliu
 * @ClassName LootCrateInitApplicationRunner
 * @Description TODO
 * @Date 2024/4/30 10:53 AM
 **/
@Component
public class LootCrateInitApplicationRunner implements ApplicationRunner {

    @Resource
    DeployManager deployManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //TODO 执行启动所有的jar逻辑
        //启动完成之前要把所有的子容器都启动起来，ApplicationRunner中实现启动lootcrate
    }
}
