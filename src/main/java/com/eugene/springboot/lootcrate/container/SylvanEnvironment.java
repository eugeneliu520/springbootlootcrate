package com.eugene.springboot.lootcrate.container;

import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.PropertySource;

/**
 * @author eugeneliu
 * @ClassName sylvanEnvironment
 * @Description TODO
 * @Date 2023/1/29 12:04 PM
 **/
public class SylvanEnvironment extends AbstractEnvironment {
    public void addFirst ( PropertySource<?> propertySource ) {
        this.getPropertySources().addFirst(propertySource);
    }

    /***@ oaram propertySources 按照优先级降序的 PropertySource 集合**/
    public SylvanEnvironment ( PropertySource <?>... propertySources ){
            for ( PropertySource <?> propertySource:propertySources ){
                this.getPropertySources().addLast(propertySource );
            }
    }
}
