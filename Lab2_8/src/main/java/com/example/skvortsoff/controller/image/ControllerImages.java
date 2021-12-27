package com.example.skvortsoff.controller.image;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ControllerImages {
    @RequestMapping(value = "/getImage/{uuid}")
    @ResponseBody
    public byte[] getImage(@PathVariable String uuid, HttpServletRequest request) throws IOException {
        String rpath= request.getRealPath("/");
        rpath=rpath+"/" + uuid; // whatever path you used for storing the file
        Path path = Paths.get(rpath);
        byte[] data = Files.readAllBytes(path);
        return data;
    }
}
