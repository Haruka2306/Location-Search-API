package com.example.crudapi.validation;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import org.hibernate.validator.spi.nodenameprovider.JavaBeanProperty;
import org.hibernate.validator.spi.nodenameprovider.Property;
import org.hibernate.validator.spi.nodenameprovider.PropertyNodeNameProvider;

public class JacksonPropertyNodeNameProvider implements PropertyNodeNameProvider {
    private final ObjectMapper object_mapper = new ObjectMapper();

    @Override
    public String getName(Property property) {
        if (property instanceof JavaBeanProperty) {
            return getJavaBeanPropertyName((JavaBeanProperty) property);
        }
        return getDefaultName(property);
    }

    private String getJavaBeanPropertyName(JavaBeanProperty property) {
        JavaType type = object_mapper.constructType(property.getDeclaringClass());
        BeanDescription desc = object_mapper.getSerializationConfig().introspect(type);

        return desc.findProperties()
                .stream()
                .filter(prop -> prop.getInternalName().equals(property.getName()))
                .map(BeanPropertyDefinition::getName)
                .findFirst()
                .orElse(property.getName());
    }

    private String getDefaultName(Property property) {
        return property.getName();
    }
}
