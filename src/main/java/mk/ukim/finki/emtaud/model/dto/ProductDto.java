package mk.ukim.finki.emtaud.model.dto;

import lombok.Data;

@Data
public class ProductDto {

    private String name;

    private Double price;

    private Integer quantity;

    private Long category;

    private Long manufacturer;

    public ProductDto() {
    }

    public ProductDto(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = categoryId;
        this.manufacturer = manufacturerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public Long getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Long manufacturer) {
        this.manufacturer = manufacturer;
    }
}
