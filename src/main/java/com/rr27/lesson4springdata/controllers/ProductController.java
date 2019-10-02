package com.rr27.lesson4springdata.controllers;

import com.rr27.lesson4springdata.entities.Product;
import com.rr27.lesson4springdata.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.rr27.lesson4springdata.utils.ProductFilter;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

//    /**
//     * required = false - если данного параметра в url не будет, то мы не схватим Exception
//     *
//     */
//    @RequestMapping()
//    public String showProducts(Model model, HttpServletRequest request,
//                               @RequestParam(name = "word", required = false) String word,
//                               @RequestParam(name = "min", required = false) Integer min,
//                               @RequestParam(name = "max", required = false)Integer max,
//                               @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
//                               @RequestParam(name = "pageSize", required = false) Integer pageSize
//    ){
//
//        //выводит url с которого совершался последний переход
////        System.out.println(request.getHeader("referer"));
//
//        ProductFilter productFilter = new ProductFilter(request);
//
//        if(pageNumber == null){
//            pageNumber = 1;
//        }
//        if(pageSize == null){
//            pageSize = 5;
//        }
//
//        //На странице products.html их напрямую обрабатываем, дергая из запроса
////        model.addAttribute("word", word);
////        model.addAttribute("min", min);
////        model.addAttribute("max", max);
//
//        model.addAttribute("pageNumber", pageNumber);
//        model.addAttribute("filters", productFilter.getFiltersBuilder());
//
//        Page<Product> page = productService.findAllByPagingAndFiltering(productFilter.getSpecification(), PageRequest.of(pageNumber-1, pageSize, Sort.Direction.ASC,"id"));
//        model.addAttribute("page", page);
//
//        return "products";
//    }

    @GetMapping("/edit")
    public String showEditFrom(Model model, @RequestParam(name = "id", required = false) Long id){
        Product product = null;
        if (id != null){
            product = productService.findById(id);
        }
        else {
            product = new Product();
        }
        model.addAttribute("product", product);
        return "edit_product";
    }

    @PostMapping("/edit")
    public String save(@ModelAttribute(name = "product") Product product){
        productService.save(product);
        return "redirect:/products";
    }


    /*

   //ПАГИНАЦИЯ!
   @RequestMapping("/showAllByPage/{id}")
   public String showAllProductsByPage(Model model, @PathVariable(name="id") int page){
        Page<Product> prodPage = productService.findAllByPage( page-1, 5);
        model.addAttribute("products", prodPage.getContent());
        return "product";
    }

    //Редирект просто прокидывает в showAll и выводит всю базу в рандомном порядке((
    //и никакой выборки от мин до макс
    @GetMapping("/filter_form")
    public String getFilterProduct(@RequestParam(name = "minCost") int minCost, @RequestParam(name = "maxCost") int maxCost){
        productService.findAllProductBetweenCost(minCost, maxCost);
        return "redirect:/products/showAll";
    }

    //Сортировка через кнопку
     @GetMapping("/result_form")
     public String resultForm(Model model, @RequestParam(name = "sortSelect") String filterSelect) {
        System.out.println(filterSelect);
        List<Product> productList;
        if (filterSelect.contains("убыванию")){
            productList = productService.findAllOrderByDESC();
        }
        else {
            productList = productService.findAllOrderByASC();
        }
        model.addAttribute("products", productList);
        return "product";
    }

    //Поиск через кнопку
    @GetMapping("/submit_form")
    @ResponseBody
    public String getMinOrMaxCostProduct(@RequestParam(name = "findSelector") String var) {
        Product product;
        if (var.contains("Минимальная")){
            product = productService.minCostProduct();
        }
        else {
            product = productService.maxCostProduct();
        }
        return product.toString();
    }

    //work
    @GetMapping("/delete/{id}")
    public String removeProductById(@PathVariable(name = "id") Long id){
        productService.removeById(id);
        return "redirect:/products/showAll";
    }

    //не доделал
    @GetMapping("/update/{id}")
    public String updateProductById(@PathVariable(name = "id") Long id){
        return "update";
    }
    */


    /**
     * МЕТОДЫ РАБОТАЮЩИЕ ЧЕРЕЗ URL
     */
    /*
    @GetMapping("/getByTitle")
    @ResponseBody
    public String getProductByTitle(@RequestParam(name = "title") String title) {
        return productService.findByTitle(title).toString();
    }

    @RequestMapping("/showAllLessThan")
    public String showAllProductsLessThan(Model model, @RequestParam(name = "cost") int cost){
        List<Product> productList = productService.findAllLessThan(cost);
        model.addAttribute("products", productList);
        return "product";
    }


   @RequestMapping("/showAllDESC")
   public String showAllProductsDESC(Model model){
       List<Product> productList = productService.findAllOrderByDESC();
       model.addAttribute("products", productList);
       return "product";
   }

    @RequestMapping("/showAllASC")
    public String showAllProductsASC(Model model){
        List<Product> productList = productService.findAllOrderByASC();
        model.addAttribute("products", productList);
        return "product";
    }

    @RequestMapping("/minCostProduct")
    @ResponseBody
    public String getMinCostProduct() {
        Product product = productService.minCostProduct();
        return product.toString();
    }

    @RequestMapping("/maxCostProduct")
    @ResponseBody
    public String getMaxCostProduct() {
        Product product = productService.maxCostProduct();
        return product.toString();
    }
    */

}
