package com.example.SellingBreadApp.service.implement;
import com.example.SellingBreadApp.dto.ProductDto;
import com.example.SellingBreadApp.dto.ResponseDTO;
import com.example.SellingBreadApp.entity.Product;
import com.example.SellingBreadApp.entity.Topping;
import com.example.SellingBreadApp.exception.CustomException;
import com.example.SellingBreadApp.repository.ProductRepository;
import com.example.SellingBreadApp.repository.ToppingRepository;
import com.example.SellingBreadApp.service.ProductService;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;
	private final ToppingRepository toppingRepository;

	//Create constructor
	public ProductServiceImpl(ProductRepository productRepository, ToppingRepository toppingRepository) {
		this.productRepository = productRepository;
		this.toppingRepository = toppingRepository;
	}

	//Override method create
	@Override
	public void create(ProductDto productDto) throws CustomException {
		List<Topping> getItem = toppingRepository.findAll ();
		Product createProduct = new Product ();
		List<Long> toppingIds = getItem
				.stream ()
				.map (Topping::getId)
				.collect (Collectors.toList ());
		List<Topping> existingToppings = toppingRepository.findAllById (toppingIds);
		List<Topping> selectTopping = new ArrayList<>();
		createProduct.setName (productDto.getName ());
		createProduct.setPrice (productDto.getPrice ());
		createProduct.setMaxTopping (productDto.getMaxTopping ());
		for (Topping existingTopping : existingToppings) {
			for (int j = 0; j < productDto.getToppingItem().size(); j++) {
				if (existingTopping.getId()
						.equals(productDto.getToppingItem().get(j).getToppingId())) {
					selectTopping.add(existingTopping);
				}
			}
		}
		createProduct.setToppings(selectTopping);
		productRepository.save (createProduct);
	}
	// Override method
	@Override
	public ResponseDTO<List<Product>> getAllProduct() {
		return new ResponseDTO<>(productRepository.findAll(), HttpStatus.OK, "The ingredient get all");
	}
}
