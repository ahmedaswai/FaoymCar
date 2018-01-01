package com.qcar.utils;

import java.util.BitSet;

public final class GeneralUtils {
    public static BitSet bitsSet(long num) {
        BitSet bitSet = new BitSet();
        for (int i = 0; i < 32; i++)
            if (((num >>> i) & 1) != 0)
                bitSet.set(i);
        return bitSet;
    }
}
