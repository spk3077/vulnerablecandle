package csec.vulnerable.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import csec.vulnerable.dto.ProductReviewDTO;
import csec.vulnerable.http.Response;
import csec.vulnerable.service.ProductService;

@RestController()
@RequestMapping("/products")
public class ProductController {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable String id) {
        ProductDTO productDTO = null;
        try (Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement()) {
            String sql = "SELECT p.*, pr.id as pr_id, pr.grade, pr.comment, pr.review_date, t.*, u.username, pr.title " +
                    "FROM ecom_product p " +
                    "LEFT JOIN ecom_product_review pr ON p.id = pr.product_id " +
                    "LEFT JOIN product_tag pt ON p.id = pt.product_id " +
                    "LEFT JOIN ecom_tag t ON pt.tag_id = t.id " +
                    "LEFT JOIN ecom_user u ON pr.user_id = u.id " +
                    "WHERE p.id = " + id;
            ResultSet rs = stmt.executeQuery(sql);
            Map<Integer, ProductDTO> productMap = new HashMap<>();
            Set<Integer> prIdSet = new HashSet<>();
            Set<Integer> tagIdSet = new HashSet<>();
            while (rs.next()) {
                int productId = rs.getInt("id");
                productDTO = productMap.get(productId);
                if (productDTO == null) {
                    productDTO = new ProductDTO();
                    productDTO.setId(productId);
                    productDTO.setName(rs.getString("name"));
                    productDTO.setBrand(rs.getString("brand"));
                    productDTO.setPrice(rs.getDouble("price"));
                    productDTO.setStock(rs.getInt("stock"));
                    productDTO.setImageURL(rs.getString("image"));
                    productDTO.setDescription(rs.getString("description"));

                    List<String> tagNames = new ArrayList<>();
                    List<ProductReviewDTO> productReviews = new ArrayList<>();

                    productDTO.setTagNames(tagNames);
                    productDTO.setProductReviews(productReviews);
                    productMap.put(productId, productDTO);
                }
                
                int prId = rs.getInt("pr_id");
                if (prId != 0 && !prIdSet.add(prId)) {
                    ProductReviewDTO reviewDTO = new ProductReviewDTO();
                    reviewDTO.setId(prId);
                    reviewDTO.setUsername(rs.getString("username"));
                    reviewDTO.setTitle(rs.getString("title"));
                    reviewDTO.setGrade(rs.getInt("grade"));
                    reviewDTO.setComment(rs.getString("comment"));
                    reviewDTO.setReview_date(rs.getDate("review_date"));
                    reviewDTO.setProductId(productId);
                    productDTO.getProductReviews().add(reviewDTO);
                }

                int tagId = rs.getInt("t.id");
                if (tagId != 0 && tagIdSet.add(tagId)) {
                    String tagName = rs.getString("t.name");
                    productDTO.getTagNames().add(tagName);
                }
            }

            // calculate average review grade
            productMap.values().forEach(p -> {
                double avgGrade = calculateAverageReviewGrade(p.getProductReviews());
                p.setAverageReviewGrade(avgGrade);
            });

            return productDTO;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private double calculateAverageReviewGrade(List<ProductReviewDTO> productReviews) {
        if (productReviews == null || productReviews.isEmpty()) {
            return 0;
        }

        double sum = 0;
        for (ProductReviewDTO reviewDTO : productReviews) {
            sum += reviewDTO.getGrade();
        }
        return sum / productReviews.size();
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
