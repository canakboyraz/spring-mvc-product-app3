package com.works.services;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    final ProductRepository productRepository;
    final HttpServletRequest req;

    public Page<Product> allProduct(int page){
        Long cid = (Long) req.getSession().getAttribute("cid");
        Pageable pageable = PageRequest.of(page,5);
        Page<Product> productPage = productRepository.findByCidEquals(cid,pageable);
        // productPage.getContent(); // List<Product>
        // productPage.getTotalElements(); // kaç tane element olduğunu gösterir
        // productPage.getTotalPages(); // toplam sayfa sayısı
        // productPage.getNumberOfElements() // kaçıncı sayfa olduğumuzu görürüz
        return productPage;
    }

    public Page<Product> allProductHome(int page){
        Long cid = (Long) req.getSession().getAttribute("cid");
        Pageable pageable = PageRequest.of(page,10);
        Page<Product> productPage = productRepository.findByCidEqualsOrderByPidDesc(cid,pageable);
        // productPage.getContent(); // List<Product>
        // productPage.getTotalElements(); // kaç tane element olduğunu gösterir
        // productPage.getTotalPages(); // toplam sayfa sayısı
        // productPage.getNumberOfElements() // kaçıncı sayfa olduğumuzu görürüz
        return productPage;
    }

    public List<Product> allProductDetail(){
        return productRepository.findAll();
    }

    public Product save( Product product ) {
        Long cid =  (Long) req.getSession().getAttribute("cid");
        product.setCid( cid );
        return productRepository.save(product);
    }

    public void productDelete(Long pid){
        productRepository.deleteById(pid);
    }

    public Product getSingleProduct(Long pid){
        Optional<Product> optionalProduct = productRepository.findById(pid);
        if (optionalProduct.isPresent()){
            return optionalProduct.get();
        }
        return null;
    }
}
