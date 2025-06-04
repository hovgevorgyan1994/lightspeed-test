package com.test.copier;

import com.test.copier.strategy.DeepCopyStrategyPredicate;
import com.test.copier.strategy.DeepCopyStrategyResolver;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("unchecked")
public abstract class DeepCopier {

    protected static final Map<Object, Object> CACHE = new ConcurrentHashMap<>();

    public abstract <T> T copy(T obj);

    public abstract DeepCopyStrategyPredicate getPredicate();

    public static <T> T deepCopy(T obj) {
        if (obj == null) {
            return null;
        }
        Object existingCopy = CACHE.get(obj);
        if (existingCopy != null) {
            return (T) existingCopy;
        }
        return DeepCopyStrategyResolver.resolve(obj);
    }
}
