package com.eugene.springboot.lootcrate.deploy;

import lombok.Data;

import java.util.Map;

/**
 * @author eugeneliu
 * @ClassName DeployRequest
 * @Description TODO
 * @Date 2023/1/29 3:42 PM
 **/
@Data
public class DeployRequest {
    private final String deployContainerBizVersion;
    private final String deployContainerBizCode;
    private final String deployStrategyType;
    private final String deployVersion;
    private final String jarFileMd5Verify;
    private final String jarFileUrl;
    private final Map<String, Object> options;

    public DeployRequest(String deployContainerBizVersion, String deployContainerBizCode, String deployStrategyType, String deployVersion,
                         String jarFileUrl, String jarFileMd5Verify, Map<String, Object> options) {
        this.deployContainerBizVersion = deployContainerBizVersion;
        this.deployContainerBizCode = deployContainerBizCode;
        this.deployStrategyType = deployStrategyType;
        this.deployVersion = deployVersion;
        this.jarFileMd5Verify = jarFileMd5Verify;
        this.jarFileUrl = jarFileUrl;
        this.options = options;
    }

    public DeployStrategyEnum getDeployStrategyType() {
        return DeployStrategyEnum.valueOf(deployStrategyType);
    }

    public String getDeployVersion() {
        return deployVersion;
    }

    public String getJarFileMd5Verify() {
        return jarFileMd5Verify;
    }

    public String getJarFileUrl() {
        return jarFileUrl;
    }

}

