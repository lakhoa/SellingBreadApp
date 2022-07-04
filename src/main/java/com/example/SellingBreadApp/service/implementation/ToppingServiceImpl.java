package com.example.SellingBreadApp.service.implementation;

import com.example.SellingBreadApp.dto.ToppingDto;
import com.example.SellingBreadApp.entity.Topping;
import com.example.SellingBreadApp.repository.ToppingRepository;
import com.example.SellingBreadApp.service.ToppingService;
import org.springframework.stereotype.Service;

@Service
public class ToppingServiceImpl implements ToppingService {

  private final ToppingRepository toppingRepository;

  public ToppingServiceImpl(ToppingRepository toppingRepository) {
    this.toppingRepository = toppingRepository;
  }

  @Override
  public void create(ToppingDto topping) {
    Topping toppings = new Topping();
    toppings.setName(topping.getName());
    toppings.setPrice(topping.getPrice());
    toppingRepository.save(toppings);
  }

}
