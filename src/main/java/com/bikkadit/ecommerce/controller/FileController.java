package com.bikkadit.ecommerce.controller;

import com.bikkadit.ecommerce.entity.BaseEntity;
import com.bikkadit.ecommerce.helper.ImageResponse;
import com.bikkadit.ecommerce.payload.UserDto;
import com.bikkadit.ecommerce.service.FileService;
import com.bikkadit.ecommerce.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    private static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Value("${user.profile.image.path}")
    private String imageUploadPath;

    @PostMapping("/image/{userId}")
    public ResponseEntity<ImageResponse> uploadUserImage(@RequestParam("imageName")
                                                             MultipartFile image, @PathVariable String userId) throws IOException {
        logger.info("Request Created For Uploading user Image {} :  +userId");
        String images= fileService.uploadFile(image,imageUploadPath);
         UserDto user=userService.getUser(userId);
         user.setImageName(images);
         UserDto userdto =userService.updateUser(user,userId);
         ImageResponse response = ImageResponse
             .builder()
             .imageName(images)
             .success(true)
             .status(HttpStatus.CREATED)
             .build();
        logger.info("Request Completed For Uploading user Image {} :  +userId");
     return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("/images/{userId}")
    public void serveUserImage(@PathVariable String userId, HttpServletResponse response) throws IOException {
        UserDto users= userService.getUser(userId);
    logger.info("user image name :{} ",users.getImageName());
   InputStream resource= fileService.getResource(imageUploadPath,users.getImageName());
   response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
