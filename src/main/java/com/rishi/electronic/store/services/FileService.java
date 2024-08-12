package com.rishi.electronic.store.services;

import com.rishi.electronic.store.exceptions.BadApiRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public interface FileService {
    String uploadFile(MultipartFile file, String path) throws IOException, BadApiRequest;
    InputStream getResources(String path, String name) throws FileNotFoundException;

    InputStream getResource(String imageUploadPath, String coverImage) throws FileNotFoundException;
}
