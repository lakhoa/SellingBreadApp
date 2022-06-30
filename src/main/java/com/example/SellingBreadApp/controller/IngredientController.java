package com.example.SellingBreadApp.controller;
import com.example.SellingBreadApp.dto.ProductDto;
import com.example.SellingBreadApp.dto.ResponseDTO;
import com.example.SellingBreadApp.dto.ToppingDto;
import com.example.SellingBreadApp.entity.Product;
import com.example.SellingBreadApp.exception.CustomException;
import com.example.SellingBreadApp.service.ProductService;
import com.example.SellingBreadApp.service.ToppingService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class IngredientController {
	private final ToppingService toppingService;

	private final ProductService productService;

	public IngredientController(ToppingService toppingService, ProductService productService) {
		this.toppingService = toppingService;
		this.productService = productService;
	}

	@PostMapping("/createTopping")
	public void create(@RequestBody @Valid ToppingDto toppingDto){
		toppingService.create (toppingDto);
	}

	@PostMapping("/createProduct")
	public void create(@RequestBody @Valid ProductDto productDto) throws CustomException{
		productService.create(productDto);
	}

	@GetMapping("/getAllIngredient")
	public ResponseEntity<ResponseDTO<List<Product>>> getAll(){
		ResponseDTO <List<Product>>  rs =  productService.getAllProduct();
		return new ResponseEntity<>(rs, HttpStatus.OK);
	}
}
