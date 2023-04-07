package csec.vulnerable.service;

import csec.vulnerable.beans.Product;
import csec.vulnerable.dto.ProductDTO;
import csec.vulnerable.http.Response;

import java.util.List;

public interface ProductServiceInterface {
    ProductDTO findById(int id);
    List<ProductDTO> findAll();
    Response addProduct(Product product);
    Response updateProduct(Product product);
    Response deleteProduct(int id);
}
