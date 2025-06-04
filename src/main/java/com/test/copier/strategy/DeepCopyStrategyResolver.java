package com.test.copier.strategy;

import com.test.copier.DeepCopier;
import com.test.copier.impl.ArrayDeepCopier;
import com.test.copier.impl.CollectionDeepCopier;
import com.test.copier.impl.DefaultDeepCopier;
import com.test.copier.impl.ImmutableDeepCopier;
import com.test.copier.impl.MapDeepCopier;

import java.util.ArrayList;
import java.util.List;

public class DeepCopyStrategyResolver {

    private static final List<DeepCopier> DEEP_COPIERS = new ArrayList<>();

    static {
        DEEP_COPIERS.add(new ArrayDeepCopier());
        DEEP_COPIERS.add(new CollectionDeepCopier());
        DEEP_COPIERS.add(new ImmutableDeepCopier());
        DEEP_COPIERS.add(new MapDeepCopier());
    }

    public static <T> T resolve(T obj) {
        return DEEP_COPIERS.stream()
                .filter(deepCopier -> deepCopier.getPredicate().test(obj.getClass(), obj))
                .findFirst()
                .orElse(new DefaultDeepCopier())
                .copy(obj);
    }
}
