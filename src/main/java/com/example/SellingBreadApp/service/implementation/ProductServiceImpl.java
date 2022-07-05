package com.example.SellingBreadApp.service.implementation;

import com.example.SellingBreadApp.dto.PageResponseDTO;
import com.example.SellingBreadApp.dto.ProductDto;
import com.example.SellingBreadApp.entity.Product;
import com.example.SellingBreadApp.entity.Topping;
import com.example.SellingBreadApp.repository.ProductRepository;
import com.example.SellingBreadApp.repository.ToppingRepository;
import com.example.SellingBreadApp.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ToppingRepository toppingRepository;

  public ProductServiceImpl(ProductRepository productRepository,
      ToppingRepository toppingRepository) {
    this.productRepository = productRepository;
    this.toppingRepository = toppingRepository;
  }

  @Override
  public void create(ProductDto productDto) {
    List<Topping> toppings = toppingRepository.findAll();
    Product product = new Product();
    // Take all topping into product
    List<Long> toppingIds = toppings
        .stream()
        .map(Topping::getId)
        .collect(Collectors.toList());
    List<Topping> existingToppings = toppingRepository.findAllById(toppingIds);
    List<Topping> toppingList = new ArrayList<>();
    product.setName(productDto.getName());
    product.setPrice(productDto.getPrice());
    product.setMaxTopping(productDto.getMaxTopping());
    // Select topping for product by id of topping
    for (Topping existingDataOfTopping : existingToppings) {
      for (int positionOfTopping = 0; positionOfTopping < productDto.getToppingItem().size();
          positionOfTopping++) {
        // Compare input ID with existing topping ID ( if it has in database) and add into  a list
        if (existingDataOfTopping.getId()
            .equals(productDto.getToppingItem().get(positionOfTopping).getToppingId())) {
          toppingList.add(existingDataOfTopping);
        }
      }
    }
    product.setToppings(toppingList);
    productRepository.save(product);
  }

  @Override
  public PageResponseDTO<List<Product>> getAllProduct(Pageable pageable) {
    Page<Product> products = productRepository.findAll(pageable);
    List<Product> productList = new ArrayList<>();
    for (Product product : products) {
      productList.add(product);
    }
    return new PageResponseDTO<>(pageable.getPageNumber(), pageable.getPageSize(),
        products.getTotalPages(), productList);
  }
}
