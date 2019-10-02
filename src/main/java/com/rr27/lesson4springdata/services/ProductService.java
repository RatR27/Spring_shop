package com.rr27.lesson4springdata.services;

import com.rr27.lesson4springdata.entities.Product;
import com.rr27.lesson4springdata.repositories.ProductRepository;
import com.rr27.lesson4springdata.repositories.specifications.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> findAllByPagingAndFiltering(Specification<Product> specification, Pageable pageable){
        return productRepository.findAll(specification, pageable);
    }

    public void save(Product product){
        productRepository.save(product);
    }


    public Product findById(Long id){
        return productRepository.findById(id).get();
    }
//
//
//    public List<Product> findAll(){
//        return productRepository.findAll();
//    }
//
//    public List<Product> findAllOrderByASC(){
//        return productRepository.findAllByOrderByCostAsc();
//    }
//
//    public List<Product> findAllOrderByDESC(){
//        return productRepository.findAllByOrderByCostDesc();
//    }
//
//    public Product minCostProduct(){
//        return productRepository.getFirstAllByOrderByCostAsc();
//    }
//
//    public Product maxCostProduct(){
//        return productRepository.getFirstAllByOrderByCostDesc();
//    }
//
//    public Product findByTitle(String title){
//        return productRepository.findByTitleIs(title);
//    }
//
//    public List<Product> findAllLessThan(int cost){
//        return productRepository.findByCostLessThan(cost);
//    }
//
//    //Фильтрация товаров по цене
//    public List<Product> findAllProductBetweenCost(int min, int max){
//        return productRepository.findAllByCostBetween(min, max);
//    }
//
//    //пейджированный вывод
//    public Page<Product> findAllByPage(int numPage, int sizePage){
//        return productRepository.findAll(PageRequest.of(numPage, sizePage));
//    }
//
//    public void removeById(Long id){
//        productRepository.deleteById(id);
//    }

}
