package com.eugene.springboot.lootcrate.container;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/**
 * @author eugeneliu
 * @ClassName ConfigurableEnvironmentFactory
 * @Description TODO
 * @Date 2023/1/29 11:57 AM
 **/
public class ConfigurableEnvironmentFactory {
    public static final String SYS_CONFIG = " sylvanas - system . properties ";
    public static final String META_CONFIG = " sylvanas - meta . properties ";

    public static ConfigurableEnvironment create(ClassLoader classLoader) throws IOException {
        final SylvanEnvironment sylvanEnvironment = new SylvanEnvironment(
                // new MapPropertySource ( CONF _ FILE _ META , appProperties );
                new PropertiesPropertySource(SYS_CONFIG, System.getProperties())
        );
        addPropertiesSourcesIfPresent(classLoader, sylvanEnvironment, "sytvanas .properties ");
        return sylvanEnvironment;
    }

    private static void addPropertiesSourcesIfPresent(ClassLoader classLoader, SylvanEnvironment sylvanEnvironment, String... envProperties) throws
            IOException {
        for (int i = envProperties.length - 1; i >= 0; i--) {
            final URL resource = classLoader.getResource(envProperties[i]);
            if (Objects.nonNull(resource)) {
                sylvanEnvironment.addFirst(new ResourcePropertySource(envProperties[i], envProperties[i], classLoader));
            }
        }
    }
}
