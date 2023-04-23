package com.paperized.productstore.util;

import java.util.function.Function;

public class MapperUtil {
    public static <T, R> R mapTo(Function<T, R> mapFn, T arg) {
        return mapFn.apply(arg);
    }
}
