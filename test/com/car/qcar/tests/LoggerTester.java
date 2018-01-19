package com.car.qcar.tests;

import com.qcar.service.VertxLauncher;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.util.stream.LongStream;

public class LoggerTester {
    private static final org.slf4j.Logger logger= LoggerFactory.getLogger(LoggerTester.class);

    @Test
    public void testRolling(){
        LongStream.range(0,1000000 ).forEach(i->logger.debug("Stream "+i));
    }


}
