package com.tattoo_marketplace.application.services;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


public interface ImageService {

    String saveImage(MultipartFile image) throws IOException;

}
