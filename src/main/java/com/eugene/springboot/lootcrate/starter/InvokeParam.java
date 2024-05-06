package com.eugene.springboot.lootcrate.starter;

/**
 * @author eugeneliu
 * @ClassName InvokeMethodParam
 * @Description TODO
 * @Date 2024/4/30 10:19 AM
 **/
public class InvokeParam {
    private Object[] params;
    private String serviceName;
    private String serviceMethod;
    private String bizId;

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceMethod() {
        return serviceMethod;
    }

    public void setServiceMethod(String serviceMethod) {
        this.serviceMethod = serviceMethod;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }
}
