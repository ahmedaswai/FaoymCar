package com.car.fayoum.dao.tests;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneralUtils {
    @Test
    public void testGetExtension(){

        assertEquals (com.qcar.utils.GeneralUtils.getExtension("fi.le.png"),"png");
    }
}
