package com.example.SellingBreadApp.service;

import com.example.SellingBreadApp.dto.ProductDto;
import com.example.SellingBreadApp.dto.ResponseDTO;
import com.example.SellingBreadApp.entity.Product;

import com.example.SellingBreadApp.exception.CustomException;
import java.util.List;

public interface ProductService {

	void create(ProductDto productDto) throws CustomException;

	ResponseDTO<List<Product>> getAllProduct();

}
