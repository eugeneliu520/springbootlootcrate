package com.eugene.springboot.lootcrate.registry;

import com.eugene.springboot.lootcrate.container.LifecycleContainer;
import com.eugene.springboot.lootcrate.deploy.ExecuteByClassLoader;

import java.lang.reflect.Method;
import java.util.function.Consumer;

/**
 * @author eugeneliu
 * @ClassName ServiceInvoke
 * @Description TODO
 * @Date 2024/4/18 10:49 AM
 **/
public class ServiceInvoke {

    //TODO
    public static String invoke(String type, String serviceName, String methodName, Object[] params, Consumer c){
        c.accept(type);
        return null;
    }

    public static String invoke(String type,String serviceName,String methodName,Object[] params) {
        LifecycleContainer context = null;
        try {
            context = RuntimeContainerRegistry.getContainer(type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (context == null) {
            throw new RuntimeException();
        }

        // 从容器中按照名称获取 bean
        final Object bean = context.getServiceByName(serviceName);
        ExecuteByClassLoader execute = () -> {
            // 使用反射获取并执行第一个方法
            Method[] methods = bean.getClass().getMethods();
            if (methods.length <= 0) {
                throw new RuntimeException();
            }

            for (Method method : methods) {
                if (!methodName.equals(method.getName())) {
                    continue;
                }
                try {
                    // 如果第一个方法是无参数的，则直接调用
                    if (method.getParameterCount() == 0) {
                        return method.invoke(bean);
                    } else {
                        return method.invoke(bean, params);
                    }
                } catch (Exception e) {
                    // 处理异常
                    e.printStackTrace();
                }
            }
            throw new RuntimeException();
        };
        Object result = ExecuteByClassLoader.CHANGE_CLASSLOADER.apply(context.getClassLoader(),execute);
        return result.toString();
    }
}
