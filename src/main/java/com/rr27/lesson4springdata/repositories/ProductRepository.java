package com.rr27.lesson4springdata.repositories;

import com.rr27.lesson4springdata.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    //поиск записи по id
//    Product findOneById(Long id);

    //вывести все записи БД
    List<Product> findAll();

//    List<Product> findAllByOrderByCostAsc();
//
//    List<Product> findAllByOrderByCostDesc();
//
//    Product getFirstAllByOrderByCostAsc();
//
//    Product getFirstAllByOrderByCostDesc();

    //поиск от мин до макс значения
//    List<Product> findAllByCostBetween(int min, int max);

    //пагинация
    Page<Product> findAll(Pageable pageable);

    //удалить из БД по id
    void deleteById(Long id);

    //создание в БД
    Product save(Product product);

    //очистка таблицы в БД
    void deleteAll();

//    Product findByTitleIs(String title);

//    List<Product> findByCostLessThan(int cost);

}
