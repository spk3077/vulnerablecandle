package csec.vulnerable.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import csec.vulnerable.beans.ProductReview;
import csec.vulnerable.dto.ProductReviewDTO;
import csec.vulnerable.http.Response;
import csec.vulnerable.service.ProductReviewService;

@RestController
@RequestMapping("/productreviews")
public class ProductReviewController {
    @Autowired
    ProductReviewService productReviewService;

    @GetMapping("/{id}")
    public ProductReviewDTO getProductReview(@PathVariable int id) {
        return productReviewService.getProductReview(id);
    }

    @GetMapping
    public List<ProductReviewDTO> getProductReviews() {
        return productReviewService.getProductReviews();
    }

    @PostMapping
    public Response addProductReview(@RequestBody @Valid ProductReview productReview, Authentication authentication) {
        return productReviewService.addProductReview(productReview, authentication);
    }
}
