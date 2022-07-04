package com.example.SellingBreadApp.service;

import com.example.SellingBreadApp.dto.PageResponseDTO;
import com.example.SellingBreadApp.dto.ProductDto;
import com.example.SellingBreadApp.entity.Product;
import com.example.SellingBreadApp.exception.CustomException;
import java.util.List;
import org.springframework.data.domain.Pageable;


public interface ProductService {

  void create(ProductDto productDto) throws CustomException;

  PageResponseDTO<List<Product>> getAllProduct(Pageable pageable);

}
