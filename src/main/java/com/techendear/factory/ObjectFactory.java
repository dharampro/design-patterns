package com.techendear.factory;

import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ObjectFactory {
    private final Map<String, Object> objectFactory = new HashMap<>();

    public ObjectFactory(String rootPackage) {
        register(rootPackage);
    }

    private void register(String rootPackage) {
        Reflections reflections = new Reflections(rootPackage == null ? this.getClass().getPackage() : rootPackage);
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(Component.class);
        for (Class<?> annotatedClass : annotatedClasses) {
            try {
                Class<?> clazz = Class.forName(annotatedClass.getName());
                Constructor<?> cons = clazz.getConstructor();
                Object object = cons.newInstance();
                objectFactory.put(clazz.getName(), object);
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    public Object getInstance(@NotNull Class<?> clazz) {
        return objectFactory.get(clazz.getName());
    }

}
