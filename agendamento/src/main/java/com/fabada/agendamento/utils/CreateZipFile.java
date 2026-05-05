package com.fabada.agendamento.utils;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Getter
@Setter
public class CreateZipFile {
    private final List<String> path;
    private File data;
    private String name;

    public CreateZipFile(List<String>  path) {
        this.path = path;
    }

    public File toZip(){
        String zipFileName = UUID.randomUUID() + ".zip";

        try(ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName))){
          for(String file : path){
              File data = new File(file);
              out.putNextEntry(new ZipEntry(data.getName()));
              Files.copy(Paths.get(file), out);
          }
        } catch (Exception e) {
          throw new RuntimeException(e);
        }

        data = new File(zipFileName);
        setName(data.getName());
        return data;
    }

    public byte[] getByte(){
        if(data == null) throw new NullPointerException("file data is null");

        try {
            return new FileInputStream(data).readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
