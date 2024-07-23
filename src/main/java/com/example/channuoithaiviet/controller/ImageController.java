package com.example.channuoithaiviet.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.channuoithaiviet.entity.Image;
import com.example.channuoithaiviet.entity.User;
import com.example.channuoithaiviet.exception.BadRequestException;
import com.example.channuoithaiviet.exception.InternalServerException;
import com.example.channuoithaiviet.exception.NotFoundException;
import com.example.channuoithaiviet.model.response.MessageResponse;
import com.example.channuoithaiviet.repository.UserRepository;
import com.example.channuoithaiviet.service.ImageService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/image")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ImageController {
    private static String UPLOAD_DIR  = System.getProperty("user.dir") + "/src/main/resources/static/photos/";

    @Autowired
    private ImageService imageService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public ResponseEntity<?> getList(){
        List<Image> listImage = imageService.getListImage();

        return  ResponseEntity.ok(listImage);
    }

//    @GetMapping("/user")
//    @Operation(summary="Lấy ra danh sách hình ảnh của user bằng user_id")
//    public ResponseEntity<?> getListByUser(@PathVariable long userId){
//        List<Image> listImage = imageService.getListByUser(userId);
//
//        return ResponseEntity.ok(listImage);
//    }
    @GetMapping("/user")
    @Operation(summary="Lấy ra danh sách hình ảnh của user bằng user_id")
    public ResponseEntity<?> getListByUser(@RequestParam long userId){
        List<Image> listImage = imageService.getListByUser(userId);
        return ResponseEntity.ok(listImage);
    }


    @DeleteMapping("/delete/{id}")
    @Operation(summary="Xóa ảnh bằng Id")
    public ResponseEntity<?> delete(@PathVariable long id){
    	imageService.deleteImage(id);
        return ResponseEntity.ok(new MessageResponse("Delete success"));
    }

    @PostMapping("/upload-file")
    @Operation(summary="Upload file lên database")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("username") String username){
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);;
        if (originalFilename != null && originalFilename.length() > 0) {
            if (!extension.equals("png") && !extension.equals("jpg") && !extension.equals("gif") && !extension.equals("svg") && !extension.equals("jpeg")) {
                throw new BadRequestException("Không hỗ trợ định dạng file này");
            }
         // Lấy thông tin người dùng từ cơ sở dữ liệu            
            User user = userRepository.findByUsername(username).orElseThrow(()-> new NotFoundException("Not Found User"));
           
            try {
                Image img = new Image();
                img.setName(file.getName());
                img.setSize(file.getSize());
                img.setType(extension);
                img.setData(file.getBytes());
                img.setUploadedBy(user); // Lưu thông tin người upload

                String uid = UUID.randomUUID().toString();
                String link = UPLOAD_DIR + uid + "." + extension;
                // Create file
                File serverFile = new File(link);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(file.getBytes());
                stream.close();

                imageService.save(img);
                return ResponseEntity.ok(img);
            } catch (Exception e) {
                throw new InternalServerException("Lỗi khi upload file");
            }
        }

        throw new BadRequestException("File không hợp lệ");

    }
}
