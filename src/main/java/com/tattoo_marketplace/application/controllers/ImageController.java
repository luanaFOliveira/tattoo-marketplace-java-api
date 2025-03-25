package com.tattoo_marketplace.application.controllers;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final String imagePath = "/app/images/";

    @GetMapping("/{filename}")
    public Resource getImage(@PathVariable String filename) throws Exception {
        Path path = Paths.get(imagePath + filename);
        return new UrlResource(path.toUri());
    }
}
