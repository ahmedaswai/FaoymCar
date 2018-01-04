package com.qcar.utils;

import com.qcar.model.service.exception.ErrorCodes;
import com.qcar.model.service.exception.QCarException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.BitSet;
import java.util.Date;

public final class GeneralUtils {
    public static BitSet bitsSet(long num) {
        BitSet bitSet = new BitSet();
        for (int i = 0; i < 32; i++)
            if (((num >>> i) & 1) != 0)
                bitSet.set(i);
        return bitSet;
    }
    public static Date getDateAfterMinutes(Long minutes){
        LocalDateTime now = LocalDateTime.now().plusMinutes(minutes);
        Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
        Date dateFromOld = Date.from(instant);
        return dateFromOld;
    }
    public static String getExtension(String fileName){
        if(fileName==null||fileName.isEmpty()) throw new QCarException(ErrorCodes.UN_DEFINED_EXCEPTION);
        String[]fileParts=fileName.split("\\.");
        return fileParts[fileParts.length-1];
    }


}
