package csec.vulnerable.dto;

import java.util.List;

public class TagDTO {
    private int id;
    private String name;
    private List<ProductDTO> products;

    public TagDTO(int id, String name, List<ProductDTO> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
