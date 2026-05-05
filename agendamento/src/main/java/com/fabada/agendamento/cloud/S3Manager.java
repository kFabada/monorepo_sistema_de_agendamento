package com.fabada.agendamento.cloud;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public interface S3Manager {
    void upload(List<MultipartFile> files);
    void delete(List<String> keys);
    List<File> download(List<String> keys);
    HashMap<String, URL> generatorImageURL(List<String> keys);
}
