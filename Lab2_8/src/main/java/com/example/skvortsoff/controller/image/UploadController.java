package com.example.skvortsoff.controller.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class UploadController {

    @Value("${uploadPath}")
    String uploadPath;
    String dirName = "img";

    @PostMapping("/upload") public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                File dir = new File(uploadPath + File.separator + dirName + File.separator);
                if (!dir.exists())
                    dir.mkdirs();

                String uuid = UUID.randomUUID().toString().replaceAll("-","");
                int positionPoint = file.getOriginalFilename().indexOf(".");

                if(positionPoint != -1){
                    Path path = Paths.get(uploadPath + dirName + File.separator, uuid + file.getOriginalFilename().substring(positionPoint));
                    Files.copy(file.getInputStream(), path);
                    return "Загрузка успешно завершена";
                }else {return "Загрузка не удалась";}
            } catch (IOException e) {
                return "Загрузка не удалась";
            }
        } else {
            return "Пустой файл";
        }
    }

}