package mk.ukim.finki.emtaud.repository;

import mk.ukim.finki.emtaud.model.views.ProductsPerCategoryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsPerCategoryViewRepository extends JpaRepository<ProductsPerCategoryView, Long> {

    ProductsPerCategoryView findByCategoryId(Long categoryId);

}
