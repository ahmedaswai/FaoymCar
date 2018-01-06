package com.qcar.model.mongo.choicelist;

import com.qcar.utils.GeneralUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Permission {

    ADD_USER(0L), SELECT_USER(1L), REMOVE_USER(2L), UPDATE_USER(4L),
    ADD_ORDER(8L), SELECT_ORDER(16L), REMOVE_ORDER(32L), UPDATE_ORDER(64L),

    ADD_DRIVER(128L), SELECT_DRIVER(256l), REMOVE_DRIVER(512l), UPDATE_DRIVER(1024l),
    ADD_CUSTOMER(2048L), SELECT_CUSTOMER(4096l), REMOVE_CUSTOMER(8192l), UPDATE_CUSTOMER(16384l),
    ADD_TRIP(32768L), SELECT_TRIP(65536l), REMOVE_TRIP(131072l), UPDATE_TRIP(262144l),
    TRACK_TRIP(524288l);

    public static Map<Long, Permission> PERMISSION_MAP;

    static {
        Permission[] permissions = Permission.values();
        PERMISSION_MAP = Arrays.stream(permissions).collect(Collectors.toMap(Permission::getValue, Function.identity()));
    }

    private Long value;

    private Permission(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return this.value;
    }

    public static Long getSumPermissions(List<Permission> permissionList) {
        return permissionList.stream().mapToLong(Permission::getValue).reduce(0, (a, b) -> a + b);
    }

    public static List<Permission> getPermissions(final long permissionsValue) {
        long[] bitSet = GeneralUtils.bitsSet(permissionsValue).stream().mapToLong(i -> (long) Math.pow(2, i)).toArray();

        return Arrays.stream(bitSet).mapToObj(i -> PERMISSION_MAP.get(i)).collect(Collectors.toList());
    }

}
