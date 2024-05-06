package com.eugene.springboot.lootcrate.deploy;

/**
 * @author eugeneliu
 * @ClassName DependencyContext
 * @Description TODO
 * @Date 2024/4/17 2:15 PM
 **/
public class DependencyContext {
    private String[] readExcludeParentPackages;
    private String[] importFromParentBeans;
    private String[] readExcludeCratePackages;

    public String[] getReadExcludeParentPackages() {
        return readExcludeParentPackages;
    }

    public void setReadExcludeParentPackages(String[] readExcludeParentPackages) {
        this.readExcludeParentPackages = readExcludeParentPackages;
    }

    public String[] getImportFromParentBeans() {
        return importFromParentBeans;
    }

    public void setImportFromParentBeans(String[] importFromParentBeans) {
        this.importFromParentBeans = importFromParentBeans;
    }

    public String[] getReadExcludeCratePackages() {
        return readExcludeCratePackages;
    }

    public void setReadExcludeCratePackages(String[] readExcludeCratePackages) {
        this.readExcludeCratePackages = readExcludeCratePackages;
    }
}
