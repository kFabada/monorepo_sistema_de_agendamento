package com.fabada.agendamento.cloud;

import io.awspring.cloud.s3.ObjectMetadata;
import io.awspring.cloud.s3.S3Resource;
import io.awspring.cloud.s3.S3Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class S3ManagerImpl implements S3Manager {
    @Autowired
    private S3Template s3Template;
    private final String BUCKET = "agendamento-956723945610-us-east-1-an";
    private final String PATH = "photo/";

    @Override
    public void upload(List<MultipartFile> files) {
        files.forEach((value) -> {

            if(!value.isEmpty()){
                String name = value.getOriginalFilename();
                String type = value.getContentType();
                Long size = value.getSize();

               ObjectMetadata metadata = ObjectMetadata.builder()
                        .contentType(type)
                        .contentLength(size)
                        .build();

                try{
                    s3Template.upload(BUCKET, PATH + name, value.getInputStream(), metadata);
                } catch (IOException e) {
                    throw new RuntimeException("");
                }
            }
        });
    }

    @Override
    public void delete(List<String> keys) {
        keys.forEach((value) -> s3Template.deleteObject(BUCKET, PATH + value));
    }

    @Override
    public List<File> download(List<String> keys) {
        List<File> files = new ArrayList<>();

        keys.forEach((value) -> {
            S3Resource object = s3Template.download(BUCKET, PATH + value);

            if(object.exists()){
                File file = new File(value);

                try {
                    FileOutputStream out = new FileOutputStream(file);
                    out.write(object.getInputStream().readAllBytes());
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                files.add(file);
            }
        });

        return files;
    }

    @Override
    public HashMap<String, URL> generatorImageURL(List<String> keys) {
        HashMap<String, URL> map = new HashMap<>();

        keys.forEach((value) -> {
           URL path = s3Template.createSignedGetURL(BUCKET, PATH + value, Duration.ofMinutes(15));
           map.put(value, path);
        });

        return map;
    }
}
