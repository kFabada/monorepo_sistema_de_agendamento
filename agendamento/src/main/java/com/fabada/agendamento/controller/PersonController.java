package com.fabada.agendamento.controller;


import com.fabada.agendamento.cloud.S3ManagerImpl;
import com.fabada.agendamento.service.PersonService;
import com.fabada.agendamento.utils.CreateZipFile;
import com.fabada.agendamento.utils.FileNameValidation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;

@RestController()
@RequestMapping("/person")
public class PersonController{
    private final S3ManagerImpl s3ManagerImpl;
    private final PersonService personService;
    private final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);


    @Value("${PATH_RESOURCES}")
    private String resourcePath;

    public PersonController(S3ManagerImpl s3ManagerImpl, PersonService personService) {
        this.s3ManagerImpl = s3ManagerImpl;
        this.personService = personService;
    }

    @GetMapping("/photo")
    public ResponseEntity<?> uploadPhoto(List<MultipartFile> file) {
        LOGGER.debug("file multipart null `{}`", file);
        LOGGER.debug("teste qualquer");
        //if(file.isEmpty()) throw new IllegalArgumentException();

        //s3ManagerImpl.upload(file);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/download")
    public ResponseEntity<?> download(@RequestParam("fileName") String fileName){
        List<String> file = new FileNameValidation(fileName).toList();


        s3ManagerImpl.download(file);
        CreateZipFile zip = new CreateZipFile(file);
        zip.toZip();

        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment; filename=" + zip.getName() )
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .cacheControl(CacheControl.maxAge(Duration.of(5, ChronoUnit.MINUTES)))
                .body(zip.getByte());
    }

    @GetMapping("/generator-url/{fileName}")
    public ResponseEntity<?> FileUrl(@PathVariable String fileName){
        List<String> file = new FileNameValidation(fileName).toList();

        HashMap<String, URL> urls = s3ManagerImpl.generatorImageURL(file);
        return ResponseEntity.ok().body(urls);
    }

}
