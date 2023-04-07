package csec.vulnerable.service;

import csec.vulnerable.beans.Collection;
import csec.vulnerable.beans.Product;
import csec.vulnerable.dao.CollectionDao;
import csec.vulnerable.dao.ProductDao;
import csec.vulnerable.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollectionService {

    @Autowired
    private CollectionDao collectionDao;

    @Autowired
    private ProductDao productDao;

    public Collection getCollection(int id) {
        return collectionDao.findById(id).orElse(null);
    }

    public List<Collection> getCollections() {
        return collectionDao.findAll();
    }

    public Response addCollection(Collection collection) {
        collectionDao.save(collection);
        return new Response(true, "Collection added successfully.");
    }

    public Response updateCollection(Collection collection) {
        Collection existingCollection = collectionDao.findById(collection.getId()).orElse(null);

        if (existingCollection != null) {
            existingCollection.setName(collection.getName());
            existingCollection.setDescription(collection.getDescription());
            collectionDao.save(existingCollection);
            return new Response(true, "Collection updated successfully.");
        } else {
            return new Response(false, "Collection not found.");
        }
    }

    public Response deleteCollection(int id) {
        if (collectionDao.existsById(id)) {
            collectionDao.deleteById(id);
            return new Response(true, "Collection deleted successfully.");
        } else {
            return new Response(false, "Collection not found.");
        }
    }

	public List<Product> getProductsByCollectionId(int collectionId) {
		return null;//productDao.findByCollectionsId(collectionId);
	}

    public Response addProductToCollection(int collectionId, int productId) {
        Optional<Collection> collectionOpt = collectionDao.findById(collectionId);
        Optional<Product> productOpt = productDao.findById(productId);

        if (collectionOpt.isPresent() && productOpt.isPresent()) {
            Collection collection = collectionOpt.get();
            Product product = productOpt.get();
            collection.addProduct(product);
            collectionDao.save(collection);
            return new Response(true, "Product added to the collection successfully.");
        } else {
            return new Response(false, "Collection or product not found.");
        }
    }

    public Response removeProductFromCollection(int collectionId, int productId) {
        Optional<Collection> collectionOpt = collectionDao.findById(collectionId);
        Optional<Product> productOpt = productDao.findById(productId);

        if (collectionOpt.isPresent() && productOpt.isPresent()) {
            Collection collection = collectionOpt.get();
            Product product = productOpt.get();
            collection.removeProduct(product);
            collectionDao.save(collection);
            return new Response(true, "Product removed from the collection successfully.");
        } else {
            return new Response(false, "Collection or product not found.");
        }
    }
}
