package com.test.copier.impl;

import com.test.copier.DeepCopier;
import com.test.copier.strategy.DeepCopyStrategyPredicate;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unchecked")
public class DefaultDeepCopier extends DeepCopier {

    @Override
    public <T> T copy(T obj) {
        try {
            Class<?> clazz = obj.getClass();
            T copy = (T) clazz.getDeclaredConstructor().newInstance();
            CACHE.put(obj, copy);
            for (Field field : getAllFields(clazz)) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    field.setAccessible(true);
                    Object value = field.get(obj);
                    field.set(copy, deepCopy(value));
                }
            }
            return copy;
        } catch (Exception e) {
            throw new RuntimeException("Failed to copy object: " + obj.getClass().getName(), e);
        }
    }

    @Override
    public DeepCopyStrategyPredicate getPredicate() {
        return DeepCopyStrategyPredicate.DEFAULT;
    }

    private static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        while (clazz != null && clazz != Object.class) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }
}
