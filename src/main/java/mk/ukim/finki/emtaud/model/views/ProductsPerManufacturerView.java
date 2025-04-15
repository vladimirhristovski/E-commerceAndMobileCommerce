package mk.ukim.finki.emtaud.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Data
@Entity
@Subselect("SELECT * FROM public.products_per_manufacturers")
@Immutable
public class ProductsPerManufacturerView {

    @Id
    @Column(name = "manufacturer_id")
    private Long manufacturerId;

    @Column(name = "num_products")
    private Integer numProducts;

}
