package com.eugene.springboot.lootcrate.deploy;

/**
 * @author eugeneliu
 * @ClassName DeployStrategyFactory
 * @Description TODO
 * @Date 2023/1/29 3:45 PM
 **/
public class DeployStrategyFactory {
    public static DeployStrategy strategy(DeployRequest deployRequest) {
        DeployStrategyEnum strategyEnum = deployRequest.getDeployStrategyType();
        switch (strategyEnum) {
            case ONLINE_FIRST_RELOAD:
            return new OnlineFirstDeployStrategy();
            case OFFLINE_FIRST_RELOAD:
            return new OfflineFirstDeployStrategy();
            default:
                return new OnlineFirstDeployStrategy();
        }
    }
}


