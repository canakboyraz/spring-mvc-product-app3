package com.works.repositories;

import com.works.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCidEquals(Long cid, Pageable pageable);

    List<Product> findByTitleContainsOrDetailContains(String title, String detail);

    Page<Product> findByCidEqualsOrderByPidDesc(Long cid, Pageable pageable);

















}