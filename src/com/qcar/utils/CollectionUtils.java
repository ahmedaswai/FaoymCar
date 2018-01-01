package com.qcar.utils;

import com.fasterxml.jackson.core.type.TypeReference;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

/**
 * Created by ahmedissawi on 12/23/17.
 */
public final class CollectionUtils {
    public static <T> Boolean isEmpty(Collection<T> col) {
        return col == null || col.isEmpty();
    }
    public static final TypeReference<List<Long>>LONG_LIST_TYPE=new TypeReference<List<Long>>() {
        @Override
        public Type getType() {
            return super.getType();
        }
    };
}
