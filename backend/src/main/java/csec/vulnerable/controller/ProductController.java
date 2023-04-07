package csec.vulnerable.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import csec.vulnerable.beans.Product;
import csec.vulnerable.dto.ProductDTO;
import csec.vulnerable.dto.ProductReviewDTO;
import csec.vulnerable.http.Response;
import csec.vulnerable.service.ProductService;

@RestController()
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable int id) {
        ProductDTO productDTO = null;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/csec", "root", "csec77499981");
            Statement stmt = conn.createStatement()) {
            String sql = "SELECT p.*, pr.id as pr_id, pr.grade, pr.comment, t.* " +
                    "FROM ecom_product p " +
                    "LEFT JOIN ecom_product_review pr ON p.id = pr.product_id " +
                    "LEFT JOIN product_tag pt ON p.id = pt.product_id " +
                    "LEFT JOIN ecom_tag t ON pt.tag_id = t.id " +
                    "WHERE p.id = " + id;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                productDTO = new ProductDTO();
                productDTO.setId(rs.getInt("id"));
                productDTO.setName(rs.getString("name"));
                productDTO.setBrand(rs.getString("brand"));
                productDTO.setPrice(rs.getDouble("price"));
                productDTO.setStock(rs.getInt("stock"));
                productDTO.setImageURL(rs.getString("image"));
                productDTO.setDescription(rs.getString("description"));

                List<ProductReviewDTO> productReviews = new ArrayList<>();
                Set<String> tagNames = new HashSet<>();

                do {
                    int prId = rs.getInt("pr_id");
                    if (prId != 0) {
                        ProductReviewDTO reviewDTO = new ProductReviewDTO();
                        reviewDTO.setId(prId);
                        //reviewDTO.setUsername(rs.getString("username"));
                        //reviewDTO.setTitle(rs.getString("title"));
                        reviewDTO.setGrade(rs.getInt("grade"));
                        reviewDTO.setComment(rs.getString("comment"));
                        //reviewDTO.setReview_date(rs.getDate("review_date"));
                        //reviewDTO.setProductId(rs.getInt("product_id"));
                        productReviews.add(reviewDTO);
                    }

                    int tagId = rs.getInt("t.id");
                    if (tagId != 0) {
                        String tagName = rs.getString("t.name");
                        tagNames.add(tagName);
                    }
                } while (rs.next());

                double averageReviewGrade = calculateAverageReviewGrade(productReviews);
                productDTO.setProductReviews(productReviews);
                productDTO.setAverageReviewGrade(averageReviewGrade);
                productDTO.setTagNames(new ArrayList<String>(tagNames));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productDTO;
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
}
