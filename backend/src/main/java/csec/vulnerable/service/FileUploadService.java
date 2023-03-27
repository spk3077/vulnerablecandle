package csec.vulnerable.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

    @Value("${product.image.upload.dir}")
    private String productImageUploadDir;

    @Value("${user.image.upload.dir}")
    private String userImageUploadDir;

    public void saveProductImage(MultipartFile file) throws IOException {
        Path filePath = Paths.get(productImageUploadDir + File.separator + file.getOriginalFilename());
        Files.write(filePath, file.getBytes());
    }

    public void saveUserImage(MultipartFile file) throws IOException {
        if (file != null) {
            String fileName = file.getOriginalFilename();
            if (fileName != null) {
                String fileExtension = fileName.substring(fileName.lastIndexOf("."));
                String newFileName = UUID.randomUUID().toString() + fileExtension;
                Path filePath = Paths.get(userImageUploadDir + File.separator + newFileName);
                Files.write(filePath, file.getBytes());
            } else {
                throw new IOException("File name is null");
            }
        } else {
            throw new IOException("File is null");
        }
    }

    public String getProductImageUploadDir() {
        return productImageUploadDir;
    }

    public void setProductImageUploadDir(String productImageUploadDir) {
        this.productImageUploadDir = productImageUploadDir;
    }

    public String getUserImageUploadDir() {
        return userImageUploadDir;
    }

    public void setUserImageUploadDir(String userImageUploadDir) {
        this.userImageUploadDir = userImageUploadDir;
    }

    
}
