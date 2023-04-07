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
import csec.vulnerable.beans.ProductReview;
import csec.vulnerable.beans.Tag;
import csec.vulnerable.dto.ProductDTO;
import csec.vulnerable.http.Response;
import csec.vulnerable.service.ProductService;

@RestController()
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id) {
        Product product = null;
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
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                product.setImage(rs.getString("image"));
                product.setDescription(rs.getString("description"));

                List<ProductReview> productReviews = new ArrayList<>();
                Set<Tag> tags = new HashSet<>();

                do {
                    int prId = rs.getInt("pr_id");
                    if (prId != 0) {
                        ProductReview review = new ProductReview();
                        review.setId(prId);
                        review.setGrade(rs.getInt("grade"));
                        review.setComment(rs.getString("comment"));
                        productReviews.add(review);
                    }

                    int tagId = rs.getInt("t.id");
                    if (tagId != 0) {
                        Tag tag = new Tag();
                        tag.setId(tagId);
                        tag.setName(rs.getString("t.name"));
                        tag.setType(rs.getString("t.type"));
                        tags.add(tag);
                    }
                } while (rs.next());

                product.setProductReviews(productReviews);
                product.setTags(tags);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
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
