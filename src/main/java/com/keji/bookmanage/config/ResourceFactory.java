package com.keji.bookmanage.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.util.Properties;

/**
 * @auther tangguangchuan
 * @date 2021/1/13 下午2:21
 */
public class ResourceFactory extends DefaultPropertySourceFactory {
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        String resourceName =  (name == null) ? resource.getResource().getFilename() : name;
        assert resourceName != null;
        if(resourceName.endsWith(".yml") || resourceName.endsWith(".yaml")){
            YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
            factoryBean.setResources(resource.getResource());
            factoryBean.afterPropertiesSet();
            Properties properties = factoryBean.getObject();
            assert  properties != null;
            return new PropertiesPropertySource(resourceName,properties);

        }
        return super.createPropertySource(name, resource);
    }
}
