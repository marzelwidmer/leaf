package ch.keepcalm.web.repositories;

import ch.keepcalm.web.domain.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by marcelwidmer on 21/03/16.
 * The Spring Data JPA CRUD Repository
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
