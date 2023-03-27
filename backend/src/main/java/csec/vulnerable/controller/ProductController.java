package csec.vulnerable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import csec.vulnerable.beans.Product;
import csec.vulnerable.dao.ProductDao;
import csec.vulnerable.dto.ProductDTO;
import csec.vulnerable.http.Response;
import csec.vulnerable.service.FileUploadService;
import csec.vulnerable.service.ProductService;

@RestController()
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private FileUploadService fileUploadService;

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable int id) {
        return productService.findById(id);
    }

    @GetMapping
    public List<ProductDTO> getProducts() {
        return productService.findAll();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public Response addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }
    @PostMapping("/{id}/uploadimage")
    public Response uploadProductImage(@PathVariable int id, @RequestParam("file") MultipartFile file) {
        try {
            // Save the uploaded file to the server
            fileUploadService.saveProductImage(file);

            // Update the product's image field with the URL of the uploaded file
            Product product = productDao.findById(id).get();
            product.setImage("http://localhost:8080/images/" + file.getOriginalFilename());
            productService.updateProduct(product);

            return new Response(true,"Image uploaded successfully");
        } catch (Exception e) {
            return new Response(false,"Error uploading image: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping
    public Response updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public Response deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }
}
