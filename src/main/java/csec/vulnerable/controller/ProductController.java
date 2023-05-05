package csec.vulnerable.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

import csec.vulnerable.beans.Product;
import csec.vulnerable.dto.ProductDTO;
import csec.vulnerable.http.Response;
import csec.vulnerable.service.ProductService;

@RestController()
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid product id");
        }
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

    @GetMapping("/stock")
    public String getProductStock(@RequestParam(name = "id", required = false) Integer id, @RequestParam(name = "url", required = false) String url) {
        if(id == null && url == null){
            return null;
        }
        String response = null;
        try {
            Integer stock = null;
            if(id != null){
                stock = 0;
                ProductDTO product = productService.findById(id);
                if (product != null) {
                    stock =  product.getStock();
                    if(url == null){
                        return "Stock: " + String.valueOf(stock);
                    }
            }
            }
            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            response = String.valueOf(stock) + " " + content.toString();
        } catch (MalformedURLException e) {
            // Handle malformed URL exception
            e.printStackTrace();
        } catch (IOException e) {
            // Handle IO exception
            e.printStackTrace();
        }
        return response;
    }
}
