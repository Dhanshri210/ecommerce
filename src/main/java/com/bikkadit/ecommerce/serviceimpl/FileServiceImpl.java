package com.bikkadit.ecommerce.serviceimpl;

import com.bikkadit.ecommerce.exception.BadApiRequest;
import com.bikkadit.ecommerce.service.FileService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    // Image Uploading
    @Override
    public String uploadFile(MultipartFile file, String path) throws IOException {
        String origionalFileName = file.getOriginalFilename();
        logger.info("FileName:  {}", origionalFileName);
        String fileName = UUID.randomUUID().toString();
        String extension = origionalFileName.substring(origionalFileName.lastIndexOf("."));
        String fileNameWithExtension = fileName + extension;
        String fullPathWithFileName = path + File.separator + fileNameWithExtension;
        logger.info("Full Image Path:  {}", fullPathWithFileName);
            if (extension.equalsIgnoreCase(".jpg") ||
                extension.equalsIgnoreCase(".jpeg") ||
                extension.equalsIgnoreCase(".png") ||
                extension.equalsIgnoreCase(".pdf")) {

                //save File
            logger.info("File Extension is {}" ,extension);
            File folder = new File(path);
            if(!folder.exists()) {
                //Create Folders
                folder.mkdirs();
            }
            //upload image
                Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
                return fileNameWithExtension;
            }
         else {
            throw new BadApiRequest("File With This " + extension + " not Allowed !!");
        }
    }
    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {
        String fullPath= path + File.separator + name;
        InputStream inputStream = new FileInputStream(fullPath) ;
        return inputStream;
    }
}
