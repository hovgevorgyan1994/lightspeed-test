package com.test.copier.impl;

import com.test.copier.DeepCopier;
import com.test.copier.strategy.DeepCopyStrategyPredicate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

@SuppressWarnings("unchecked")
public class MapDeepCopier extends DeepCopier {

    @Override
    public <T> T copy(T obj) {
        Map<?, ?> original = (Map<?, ?>) obj;
        Map<Object, Object> copy;
        if (obj instanceof TreeMap) {
            copy = new TreeMap<>();
        } else if (obj instanceof LinkedHashMap) {
            copy = new LinkedHashMap<>(original.size());
        } else {
            copy = new HashMap<>(original.size());
        }
        CACHE.put(obj, copy);
        for (Map.Entry<?, ?> entry : original.entrySet()) {
            Object key = deepCopy(entry.getKey());
            Object value = deepCopy(entry.getValue());
            copy.put(key, value);
        }
        return (T) copy;
    }


    @Override
    public DeepCopyStrategyPredicate getPredicate() {
        return DeepCopyStrategyPredicate.MAPS;
    }
}
