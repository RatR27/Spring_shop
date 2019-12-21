package com.rr27.lesson4springdata.utils;

import com.rr27.lesson4springdata.entities.Product;
import com.rr27.lesson4springdata.repositories.specifications.ProductSpecifications;
import org.springframework.data.jpa.domain.Specification;

import javax.servlet.http.HttpServletRequest;

/**
 * Класс обертка над Criteria API для более удобного использования фильтра и возможности сохранять параметры запроса при фильтрации
 */
public class ProductFilter {

    private Specification<Product> specification;
    private StringBuilder filtersBuilder;

    public Specification<Product> getSpecification() {
        return specification;
    }

    public StringBuilder getFiltersBuilder() {
        return filtersBuilder;
    }

    public ProductFilter(HttpServletRequest request) {
        filtersBuilder = new StringBuilder();
        specification = Specification.where(null);

        if (request.getParameter("word") != null && !request.getParameter("word").isEmpty()){
            specification = specification.and(ProductSpecifications.titleContains(request.getParameter("word")));
            filtersBuilder.append("&word="+request.getParameter("word"));
        }

        if (request.getParameter("min") != null && !request.getParameter("min").isEmpty()){
            specification = specification.and(ProductSpecifications.priceGreaterThanOrEq(Integer.parseInt(request.getParameter("min"))));
            filtersBuilder.append("&min="+request.getParameter("min"));
        }

        if (request.getParameter("max") != null && !request.getParameter("max").isEmpty()){
            specification = specification.and(ProductSpecifications.priceLesserThanOrEq(Integer.parseInt(request.getParameter("max"))));
            filtersBuilder.append("&max="+request.getParameter("max"));
        }
    }


}
