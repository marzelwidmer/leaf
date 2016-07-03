package ch.keepcalm.web.repositories;

import ch.keepcalm.web.model.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by marcelwidmer on 21/03/16.
 * The Spring Data JPA CRUD Repository
 */
// tag::ProductRepository[]
public interface ProductRepository extends CrudRepository<Product, Integer> {
    Product findByProductId(String id);
// end::ProductRepository[]
}
