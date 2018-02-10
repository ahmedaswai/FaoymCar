package com.qcar.utils;

import com.qcar.model.mongo.embedded.Location;
import com.qcar.model.service.exception.ErrorCodes;
import com.qcar.model.service.exception.QCarException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.BitSet;
import java.util.Date;

public final class GeneralUtils {
    private final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;

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
    public static Date getDateBeforeMonths(int months){
        LocalDateTime now = LocalDateTime.now().minusMonths(months);
        Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
        Date dateFromOld = Date.from(instant);
        return dateFromOld;
    }
    public static String getExtension(String fileName){
        if(fileName==null||fileName.isEmpty()) throw new QCarException(ErrorCodes.UN_DEFINED_EXCEPTION);
        String[]fileParts=fileName.split("\\.");
        return fileParts[fileParts.length-1];
    }

    // calculate distance based on Hiversine algorithm
    public static int calculateDistanceInKilometer(Location start,Location end) {


        double latDistance = Math.toRadians(start.getLat() - end.getLat());
        double lngDistance = Math.toRadians(start.getLng() - end.getLng());

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(start.getLat())) * Math.cos(Math.toRadians(end.getLat()))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH_KM * c));
    }



}
