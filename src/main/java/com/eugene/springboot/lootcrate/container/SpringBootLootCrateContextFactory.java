package com.eugene.springboot.lootcrate.container;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.function.Supplier;

/**
 * @author eugeneliu
 * @ClassName SpringBootLootCrateContextFactory
 * @Description TODO
 * @Date 2024/4/11 9:40 AM
 **/
public interface SpringBootLootCrateContextFactory {

    SpringBootLootCrateContextFactory DEFAULT = () -> {
        try {
            return new AnnotationConfigApplicationContext();
        }
        catch (Exception ex) {
            throw new IllegalStateException("Unable create a default ApplicationContext instance, "
                    + "you may need a custom ApplicationContextFactory", ex);
        }
    };


    ConfigurableApplicationContext create();

//    /**
//     * Creates an {@code ApplicationContextFactory} that will create contexts by
//     * instantiating the given {@code contextClass} via its primary constructor.
//     * @param contextClass the context class
//     * @return the factory that will instantiate the context class
//     * @see BeanUtils#instantiateClass(Class)
//     */
//    static ApplicationContextFactory ofContextClass(Class<? extends ConfigurableApplicationContext> contextClass) {
//        return of(() -> BeanUtils.instantiateClass(contextClass));
//    }
//
//    /**
//     * Creates an {@code ApplicationContextFactory} that will create contexts by calling
//     * the given {@link Supplier}.
//     * @param supplier the context supplier, for example
//     * {@code AnnotationConfigApplicationContext::new}
//     * @return the factory that will instantiate the context class
//     */
//    static SpringBootLootCrateContextFactory of(Supplier<ConfigurableApplicationContext> supplier) {
//        return (webApplicationType) -> supplier.get();
//    }
}
