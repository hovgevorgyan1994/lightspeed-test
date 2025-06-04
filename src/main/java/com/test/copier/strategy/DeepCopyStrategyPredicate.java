package com.test.copier.strategy;

import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;

public enum DeepCopyStrategyPredicate {

    DEFAULT(null, null),
    MAPS(Map.class::isAssignableFrom, null),
    COLLECTIONS(Collection.class::isAssignableFrom, null),
    ARRAYS(Class::isArray, null),
    PRIMITIVES(Class::isPrimitive, null),
    IMMUTABLES(null,
            obj -> obj instanceof String ||
                    obj instanceof Number ||
                    obj instanceof Boolean ||
                    obj instanceof Character ||
                    obj instanceof Enum);

    private final Predicate<Class<?>> classPredicate;
    private final Predicate<Object> objectPredicate;

    DeepCopyStrategyPredicate(Predicate<Class<?>> classPredicate, Predicate<Object> objectPredicate) {
        this.classPredicate = classPredicate;
        this.objectPredicate = objectPredicate;
    }

    public boolean test(Class<?> clazz, Object object) {
        if (classPredicate != null) {
            return classPredicate.test(clazz);
        }
        return objectPredicate.test(object);
    }
}
