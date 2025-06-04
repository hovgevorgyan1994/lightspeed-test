package com.test.copier.impl;

import com.test.copier.DeepCopier;
import com.test.copier.strategy.DeepCopyStrategyPredicate;

@SuppressWarnings("unchecked")
public class ImmutableDeepCopier extends DeepCopier {

    @Override
    public <T> T copy(T obj) {
        return switch (obj) {
            case null -> null;
            case String str -> (T) cache(obj, str);
            case Number number -> switch (number) {
                case Integer i -> (T) cache(obj, i);
                case Long l -> (T) cache(obj, l);
                case Double d -> (T) cache(obj, d);
                case Float f -> (T) cache(obj, f);
                case Short s -> (T) cache(obj, s);
                case Byte b -> (T) cache(obj, b);
                default -> (T) number;
            };
            case Boolean bool -> (T) cache(obj, bool);
            case Character ch -> (T) cache(obj, ch);
            case Enum<?> e -> (T) e;
            default -> obj;
        };
    }

    private <T> T cache(Object key, T value) {
        CACHE.put(key, value);
        return value;
    }

    @Override
    public DeepCopyStrategyPredicate getPredicate() {
        return DeepCopyStrategyPredicate.IMMUTABLES;
    }
}
