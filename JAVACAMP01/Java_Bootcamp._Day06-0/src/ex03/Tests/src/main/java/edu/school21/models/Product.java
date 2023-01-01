package edu.school21.models;

import java.util.Objects;

public class Product {
    private Long identifier;
    private String name;
    private Integer price;

    public Product(Long identifier, String name, Integer price) {
        this.identifier = identifier;
        this.name = name;
        this.price = price;
    }

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return getIdentifier().equals(product.getIdentifier()) && getName().equals(product.getName()) && Objects.equals(getPrice(), product.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdentifier(), getName(), getPrice());
    }

    @Override
    public String toString() {
        return "Product{" +
                "identifier=" + identifier +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
