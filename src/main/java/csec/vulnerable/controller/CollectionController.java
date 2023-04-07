package csec.vulnerable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import csec.vulnerable.beans.Collection;
import csec.vulnerable.http.Response;
import csec.vulnerable.service.CollectionService;

@RestController()
@RequestMapping("/collections")
public class CollectionController {
	@Autowired
    private CollectionService collectionService;

    @GetMapping("/{id}")
    public Collection getCollection(@PathVariable int id) {
        return collectionService.getCollection(id);
    }

    @GetMapping
    public List<Collection> getCollections() {
        return collectionService.getCollections();
    }

    @PostMapping
    public Response addCollection(@RequestBody Collection collection) {
        return collectionService.addCollection(collection);
    }

    @PutMapping
    public Response updateCollection(@RequestBody Collection collection) {
        return collectionService.updateCollection(collection);
    }

    @DeleteMapping("/{id}")
    public Response deleteCollection(@PathVariable int id) {
        return collectionService.deleteCollection(id);
    }

    @PostMapping("/{collectionId}/addProduct/{productId}")
    public Response addProductToCollection(@PathVariable int collectionId, @PathVariable int productId) {
        return collectionService.addProductToCollection(collectionId, productId);
    }

    @PostMapping("/{collectionId}/removeProduct/{productId}")
    public Response removeProductFromCollection(@PathVariable int collectionId, @PathVariable int productId) {
        return collectionService.removeProductFromCollection(collectionId, productId);
    }
}

