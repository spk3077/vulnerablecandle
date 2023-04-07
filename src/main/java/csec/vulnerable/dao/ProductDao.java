package csec.vulnerable.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import csec.vulnerable.beans.Product;


@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

    List<Product> findByCollectionsId(int collectionId);

}
