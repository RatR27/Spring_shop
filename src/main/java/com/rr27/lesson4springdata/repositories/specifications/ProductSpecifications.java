package com.rr27.lesson4springdata.repositories.specifications;

import com.rr27.lesson4springdata.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {

    public static Specification<Product> titleContains(String word) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + word + "%");
    }

    public static Specification<Product> costGreaterThanOrEq(double value) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), value);
        };
    }

    public static Specification<Product> costLesserThanOrEq(double value) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("cost"), value);
        };
    }
}
