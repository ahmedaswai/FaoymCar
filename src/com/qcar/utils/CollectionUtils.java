package com.qcar.utils;

import java.util.Collection;

/**
 * Created by ahmedissawi on 12/23/17.
 */
public class CollectionUtils {
    public static <T> Boolean isEmpty(Collection<T> col) {
        return col == null || col.isEmpty();
    }
}
