package com.eugene.springboot.lootcrate.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author eugeneliu
 * @ClassName LootCrateStarterProperties
 * @Description TODO
 * @Date 2024/4/19 11:39 AM
 **/
@ConfigurationProperties(prefix = "lootcrate")
public class LootCrateStarterProperties {
    private String fileLocation;

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }
}
