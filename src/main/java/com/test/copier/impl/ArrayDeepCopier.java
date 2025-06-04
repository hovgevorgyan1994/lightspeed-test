package com.test.copier.impl;

import com.test.copier.DeepCopier;
import com.test.copier.strategy.DeepCopyStrategyPredicate;

import java.lang.reflect.Array;

@SuppressWarnings("unchecked")
public class ArrayDeepCopier extends DeepCopier {

    @Override
    public <T> T copy(T obj) {
        int length = Array.getLength(obj);
        Object copy = Array.newInstance(obj.getClass().getComponentType(), length);
        CACHE.put(obj, copy);
        for (int i = 0; i < length; i++) {
            Object element = Array.get(obj, i);
            Array.set(copy, i, deepCopy(element));
        }
        return (T) copy;
    }

    @Override
    public DeepCopyStrategyPredicate getPredicate() {
        return DeepCopyStrategyPredicate.ARRAYS;
    }
}
