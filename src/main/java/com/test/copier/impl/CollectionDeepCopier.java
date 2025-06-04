package com.test.copier.impl;

import com.test.copier.DeepCopier;
import com.test.copier.strategy.DeepCopyStrategyPredicate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

@SuppressWarnings("unchecked")
public class CollectionDeepCopier extends DeepCopier {

    @Override
    public <T> T copy(T obj) {
        Collection<?> original = (Collection<?>) obj;
        Collection<Object> copy = switch (obj) {
            case Set<?> ignored -> new HashSet<>(original.size());
            case Queue<?> ignored -> new LinkedList<>();
            case List<?> ignored -> new ArrayList<>(original.size());
            default -> new ArrayList<>();
        };
        CACHE.put(obj, copy);
        for (Object element : original) {
            copy.add(deepCopy(element));
        }
        return (T) copy;
    }

    @Override
    public DeepCopyStrategyPredicate getPredicate() {
        return DeepCopyStrategyPredicate.COLLECTIONS;
    }
}
