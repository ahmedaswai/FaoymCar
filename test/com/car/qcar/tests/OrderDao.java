package com.car.qcar.tests;


import com.carrotsearch.junitbenchmarks.AbstractBenchmark;
import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qcar.model.mongo.choicelist.PaymentMethod;
import com.qcar.model.mongo.embedded.Location;
import com.qcar.model.mongo.entity.Customer;
import com.qcar.model.mongo.entity.Driver;
import com.qcar.model.mongo.entity.Order;
import com.qcar.model.mongo.entity.Trip;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.nustaq.serialization.FSTConfiguration;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Date;

public class OrderDao extends AbstractBenchmark {

    private String src;
    private String dest1;
    private String dest2;

    static FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();

    @Before
    public void init(){
        src="/Users/ahmedissawi/Desktop/Java SE 8 for the Really Impatient [Horstmann 2014-01-24].pdf";
        dest1="/Users/ahmedissawi/Desktop/output1.pdf";
        dest2="/Users/ahmedissawi/Desktop/output2.pdf";


    }

    @BenchmarkOptions(benchmarkRounds = 300, warmupRounds = 5)
    @Ignore
    @Test
    public void testChannelIO() throws IOException {


        final FileInputStream inputStream = new FileInputStream(src);
        final FileOutputStream outputStream = new FileOutputStream(dest1);
        final FileChannel inChannel = inputStream.getChannel();
        final FileChannel outChannel = outputStream.getChannel();
        inChannel.transferTo(0, inChannel.size(), outChannel);
        inChannel.close();
        outChannel.close();
        inputStream.close();
        outputStream.close();

    }

    @BenchmarkOptions(benchmarkRounds = 300, warmupRounds = 5)
    @Test
    @Ignore
    public void testNormalIO() throws IOException {


        File file = new File(src);
        FileInputStream fis = new FileInputStream(file);
        final FileOutputStream outputStream = new FileOutputStream(dest2);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);

        fis.close();
        outputStream.write(data);
        outputStream.close();

    }

    @Test
    public void testSerialization() throws IOException {

        Customer customer = Customer.
                instance().city(12).credit(40.5).
                currentLocation(new Location().

                        coordinates(new Double[]{44.33,44.33})).
                fullName("حامد").
                homeAddress("المسله").
                homePhoneNumber("+444").inTrip(false).
                mobileNumber1("+444").
                mobileNumber2("+444").
                notes("محمود السيد").
                status(true);
        Driver d=Driver.instance().setStatus(true).birthDate(new Date()).
                carLicenseCity(12).carLicenseNumber("3333").
                carLicenseExpiryDate(new Date()).credit(222d).homeAddress("fdfsdf").
                homeLocation(new Location().coordinates(new Double[]{44.3,33.3})).idNumber("333").
                licenseNumber("3333").mobileNumber1("33333").
                mobileNumber2("ddddd").
                homePhoneNumber("ddddd").
                online(true).rating(2.5).
                notes("sdsadasd").
                fullName("آحمد علي");

        Order order=Order.instance().orderNum(201500001L).customer(customer).fromLocation(new Location().

                coordinates(new Double[]{44.33,44.33})).toLocation(new Location().

                coordinates(new Double[]{44.33,44.33})).orderLocation(new Location().

                coordinates(new Double[]{44.33,44.33})).
                orderTime(new Date()).
                paymentMethod(PaymentMethod.Customer_Credit).notes("ss").
                status(true).tripStartTime(new Date()).tripEndTime(new Date());

        Trip trip=Trip.instance().tripNumber(200900004L)
                .actualCost(45d).actualDistance(33d).
                        actualEndTime(new Date()).actualStartTime(new Date()).driver(d).order(order).
                        endLoc(new Location().coordinates(new Double[]{44.3,33.3})).
                        startLoc(new Location().coordinates(new Double[]{44.3,33.3})).proposedCost(40d).
                        notes("ssss").proposedDistance(33d).proposedEndTime(new Date());

        String tripString=trip.toString();
        byte barray[] = conf.asByteArray(trip);
        Trip object = (Trip)conf.asObject(barray);
        System.out.println(trip);
        System.out.println(object.getStartLoc().getLat());

        assert (object.toString()==tripString);


    }
}
