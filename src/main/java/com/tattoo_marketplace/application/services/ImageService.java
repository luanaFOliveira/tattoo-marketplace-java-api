package com.tattoo_marketplace.application.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    public String saveImage(MultipartFile image);

    public List<String> saveImages(List<MultipartFile> images);

    public abstract void saveImageToDatabase(String imageUrl, Long entityId);
}
