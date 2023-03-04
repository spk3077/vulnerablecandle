package csec.vulnerable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import csec.vulnerable.beans.Collection;
import csec.vulnerable.dao.CollectionDao;
import csec.vulnerable.dao.ProductDao;
import csec.vulnerable.dao.UserDao;
import csec.vulnerable.http.Response;

@Service
@Transactional
public class CollectionService {
	@Autowired
	ProductDao productDao;

    @Autowired
    UserDao userDao;
	
	@Autowired
	CollectionDao collectionDao;
	
	public Collection getCollection(int id) {
		return collectionDao.findById(id).get();
	}
	
	public List<Collection> getCollections() {
		return collectionDao.findAll();
	}

    //post
	public Response addCollection(Collection collection) {
		collectionDao.save(collection);
		return new Response(true);
	}
	//put
	public Response changeCollection(Collection collection) {
        Collection col = collectionDao.findById(collection.getId()).get();
        col.setDescription(collection.getDescription());
        col.setName(collection.getName());
        col.setProducts(collection.getProducts());
        collectionDao.save(col);
        return new Response(true);
		
	}
	
	//delete
	public Response deleteCollection(int id) {
		if(collectionDao.findById(id)!=null) {
            collectionDao.deleteById(id);
            return new Response(true);
		}else {
			return new Response(false,"Collection is not found");
		}
	}

}
