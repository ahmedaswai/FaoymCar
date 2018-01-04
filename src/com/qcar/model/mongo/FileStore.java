package com.qcar.model.mongo;

import com.qcar.model.service.exception.ErrorCodes;
import com.qcar.model.service.exception.QCarException;
import com.qcar.service.ctrl.CtrlFactory;
import com.qcar.utils.GeneralUtils;
import io.vertx.ext.web.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class FileStore {
    private long size;
    private String contentType;
    private byte[]contents=new byte[0];
    private String fileName;
    private String extension;
    private String viewUrl;

    private static final Logger logger= LoggerFactory.getLogger(CtrlFactory.class);

    private FileStore(){

    }
    public static final FileStore instance(FileUpload upload){


            try {

                logger.debug("Starting Loading Pic For Driver filename {} size {}",
                        upload.fileName(),upload.size());
                byte[] data = Files.readAllBytes(Paths.get(upload.uploadedFileName()));

                FileStore fileStore = new FileStore().
                        fileName(upload.fileName()).
                        size(upload.size()).
                        contentType(upload.contentType()).
                        extension(GeneralUtils.getExtension(upload.fileName()))
                        .contents(data);

                logger.debug("Finalizing Loading Pic For Driver filename {} size {}",
                        upload.fileName(),upload.size());

                return fileStore;
            } catch (IOException e) {
                throw new QCarException(e, ErrorCodes.ERROR_UPLOAD);
            }



    }
    public long getSize() {
        return size;
    }

    public FileStore size(long size) {
        this.size = size;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public FileStore contentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public byte[] getContents() {
        return contents;
    }

    public FileStore contents(byte[] contents) {
        this.contents = contents;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public FileStore fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getExtension() {
        return extension;
    }

    public FileStore extension(String extension) {
        this.extension = extension;
        return this;
    }
    public FileStore viewUrl(String viewUrl) {
        this.viewUrl = viewUrl;
        return this;
    }

    public String getViewUrl() {
        return viewUrl;
    }
}
