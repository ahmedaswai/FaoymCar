package com.qcar.service.handlers.business;

import com.qcar.dao.DaoFactory;
import com.qcar.dao.DriverDao;
import com.qcar.dao.GenericDao;
import com.qcar.dao.UserDao;
import com.qcar.model.mongo.Driver;
import com.qcar.model.mongo.FileStore;
import com.qcar.model.mongo.Location;
import com.qcar.model.mongo.User;
import com.qcar.model.service.ServiceReturnList;
import com.qcar.model.service.ServiceReturnMap;
import com.qcar.model.service.ServiceReturnSingle;
import com.qcar.model.service.exception.ErrorCodes;
import com.qcar.model.service.exception.QCarException;
import com.qcar.utils.CollectionUtils;
import com.qcar.utils.MediaType;
import com.qcar.utils.SecurityUtils;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DriverHandler extends GenericHandler<Driver>{
    final DriverDao dao;

    public DriverHandler(){
        dao = DaoFactory.driverDao();
    }

    @Override
    public GenericDao<Driver> getDao() {
        return dao;
    }

    public void findInDistance(RoutingContext ctx){



        Double lng=Double.parseDouble(ctx.request().getParam("lng"));
        Double lat=Double.parseDouble(ctx.request().getParam("lat"));
        Double radius=Double.parseDouble(ctx.request().getParam("radius"));



        List<Driver> lst = dao.findInDistance(new Location(lng,lat),radius);

        Buffer rs = ServiceReturnList.response(lst);

        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);


    }
    public void findAllActiveOnline(RoutingContext ctx){
        List<Driver> lst = dao.findAllActiveOnline();

        Buffer rs = ServiceReturnList.response(lst);

        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);
    }
    public void findPic(RoutingContext ctx){
        Long id = Long.parseLong(ctx.request().getParam("id"));
        final FileStore pic=dao.findDriverPic(id);

        JsonObject config=(JsonObject) ctx.data().get("config");
        String filePath=String.join(File.separator,config.getString("web-root-dir"),pic.getFileName());
        Path path = Paths.get(filePath);
        try {
            Files.write(path, pic.getContents());
            ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                    .setStatusCode(200).end(ServiceReturnSingle.response(true));
        } catch (IOException e) {
            throw new QCarException(e, ErrorCodes.ERROR_DOWNLOAD);
        }


    }

    public void doAdd(RoutingContext ctx){


        doAdd(ctx,Driver.class);

    }
    public void doActivate(RoutingContext ctx){

        Long id = Long.parseLong(ctx.request().getParam("id"));

        Driver u = dao.changeStatus(id,true);
        Buffer rs = ServiceReturnSingle.response(u);
        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);
    }
    public void doDeActivate(RoutingContext ctx){

        Long id = Long.parseLong(ctx.request().getParam("id"));

        Driver u = dao.changeStatus(id,false);
        Buffer rs = ServiceReturnSingle.response(u);
        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);
    }
    public void doUploadPic(RoutingContext ctx){

        Long id = Long.parseLong(ctx.request().getParam("id"));
        Driver d=dao.findById(id).get();

        ctx.response().setChunked(true);


        FileUpload fileUpload=ctx.fileUploads().iterator().next();

        if(fileUpload.size()==0||!fileUpload.contentType().contains("image")){
            throw new QCarException("You can not Upload Images with Size 0 or " +
                    " with Invalid Content", ErrorCodes.ERROR_UPLOAD);
        }

        FileStore store=FileStore.instance(fileUpload);
        d.pic(store);

        dao.update(d);
        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(ServiceReturnSingle.response(true));
    }
}
