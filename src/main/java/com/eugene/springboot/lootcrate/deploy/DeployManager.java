package com.eugene.springboot.lootcrate.deploy;

/**
 * @author eugeneliu
 * @ClassName DeployManager
 * @Description TODO
 * @Date 2023/1/29 12:05 PM
 **/
public interface DeployManager {
    /**
     * deploy
     * @param deployRequest
     * @return
     * @throws Throwable
     */
    Boolean deploy(DeployRequest deployRequest) throws Throwable;

//    Boolean addTerminateCommand(TerminateCommand terminateCommand);  TODO

    void init();
}
