package com.eugene.springboot.lootcrate.container;

import org.springframework.context.ApplicationContext;

public interface Container {
    String getBizVersion();

    String getBizCode();

    ClassLoader getClassLoader ();

    ApplicationContext getParentApplicationContext ();

    ApplicationContext getApplicationContext ();

    String getActiveEnvironment();

    public Object getServiceByName(String serviceName);


}
