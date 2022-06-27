package com.example.SellingBreadApp.service.implement;

import com.example.SellingBreadApp.dto.*;
import com.example.SellingBreadApp.entity.*;
import com.example.SellingBreadApp.exception.InvalidSumToppingQuantityException;
import com.example.SellingBreadApp.exception.NotFoundOrderException;
import com.example.SellingBreadApp.mapper.OrderMapper;
import com.example.SellingBreadApp.repository.*;
import com.example.SellingBreadApp.service.OrdersService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ToppingRepository toppingRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderItemDetailRepository orderItemDetailRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public ResponseDTO<OrderResponseDTO> createOrder(OrderRequestDTO orderRequestDTO) {

        List<OrderItemRequestDTO> orderItemRequestDTOList = orderRequestDTO.getOrderItemRequestDTOList();
        double totalPriceOrder = 0.0;

        // create an order entity object
        Orders orders = new Orders();
        ordersRepository.save(orders);

        //create to save data
        List<OrderItem> orderItemList = new ArrayList<>();

        //resolve every item
        for (OrderItemRequestDTO orderItemRequestDTO : orderItemRequestDTOList) {

            //get product if item
            Long productId = orderItemRequestDTO.getProductId();
            Product product = getProduct(productId);

            //resolve topping list
            List<OrderItemDetailRequestDTO> itemDetailRequestDTOList = orderItemRequestDTO.getItemRequestDTOList();
            //get sum of quantity topping
            Integer sumToppingQuantity = getSumToppingQuantity(itemDetailRequestDTOList);
            //check invalid of sum topping quantity
            checkInvalidToppingQuantity(product, sumToppingQuantity);
            //get list topping of item
            List<Topping> toppingList = getToppingList(itemDetailRequestDTOList);
            //calculate price of item
            Double priceItem = calculatePriceOfItem(itemDetailRequestDTOList, product, toppingList);
            // add to total price of order
            totalPriceOrder += priceItem*orderItemRequestDTO.getQuantityItem();
            //save  data to order item table
            OrderItem orderItem = new OrderItem();
            orderItem.setOrders(orders);
            orderItem.setPriceItem(priceItem);
            orderItem.setQuantity(orderItemRequestDTO.getQuantityItem());
            orderItem.setProductName(product.getName());
            orderItem.setProductPriceUnit(product.getPrice());
            orderItemRepository.save(orderItem);

            //save data to order item detail table
            // map to save data
            Map<Long, Integer> map = orderItemRequestDTO.getItemRequestDTOList()
                    .stream()
                    .collect(Collectors.toMap(OrderItemDetailRequestDTO::getToppingId, OrderItemDetailRequestDTO::getQuantityTopping));

            // save data to order item detail table
            List<OrderItemDetail> orderItemDetailList = new ArrayList<>();
            for (Topping S : toppingList){
                OrderItemDetail orderItemDetail = new OrderItemDetail();
                orderItemDetail.setToppingName(S.getName());
                orderItemDetail.setToppingPriceUnit(S.getPrice());
                orderItemDetail.setOrderItems(orderItem);
                orderItemDetail.setQuantityTopping(map.get(S.getId()));
                orderItemDetailRepository.save(orderItemDetail);
                orderItemDetailList.add(orderItemDetail);
            }
            orderItem.setOrderItemDetails(orderItemDetailList);
            orderItemRepository.save(orderItem);
            orderItemList.add(orderItem);
        }
        orders.setOrderItems(orderItemList);
        orders.setTotalPrice(totalPriceOrder);
        ordersRepository.save(orders);

        // convert entity to DTO
        OrderResponseDTO orderResponseDTO = orderMapper.convertToOrderResponseDTO(orders);

        return new ResponseDTO<>(orderResponseDTO, HttpStatus.OK, "The order is added");
    }

    @Override
    public ResponseDTO<List<HistoryOrderResponseDTO>> getOrder(Pageable pageable) {
        Page<Orders> ordersList = ordersRepository.findAll(pageable);
        List<HistoryOrderResponseDTO> historyOrderResponseDTOList = new ArrayList<>();
        for (Orders orders : ordersList){
            historyOrderResponseDTOList.add(orderMapper.convertToHistoryOrderResponseDTO(orders));
        }
        return new ResponseDTO<>(historyOrderResponseDTOList, HttpStatus.OK, "The orders get all");
    }

    @Override
    public ResponseDTO<OrderResponseDTO> getOrderDetail(Long orderId) {
        Orders orders = ordersRepository.findById(orderId).orElse(null);
        if  (orders == null){
            throw  new NotFoundOrderException("Cannot find order");
        }
        OrderResponseDTO orderResponseDTO = orderMapper.convertToOrderResponseDTO(orders);
        return new ResponseDTO<>(orderResponseDTO, HttpStatus.OK, "The order detail is get");
    }

    @Override
    public ResponseDTO<List<HistoryOrderResponseDTO>> getOrderByDate(Date date,Pageable pageable) {
        Page<Orders> ordersList = ordersRepository.findAllByCreateDate(date, pageable);
        List<HistoryOrderResponseDTO> historyOrderResponseDTOList = new ArrayList<>();
        for (Orders orders : ordersList){
            historyOrderResponseDTOList.add(orderMapper.convertToHistoryOrderResponseDTO(orders));
        }
        return new ResponseDTO<>(historyOrderResponseDTOList, HttpStatus.OK, "The orders get all");
    }

    @Override
    public ResponseDTO<List<HistoryOrderResponseDTO>> getOrderByDateBetween(Date dateStart, Date dateEnd, Pageable pageable) {
        Page<Orders> ordersList = ordersRepository.findByCreateDateBetween(dateStart, dateEnd, pageable);
        List<HistoryOrderResponseDTO> historyOrderResponseDTOList = new ArrayList<>();
        for (Orders orders : ordersList){
            historyOrderResponseDTOList.add(orderMapper.convertToHistoryOrderResponseDTO(orders));
        }
        return new ResponseDTO<>(historyOrderResponseDTOList, HttpStatus.OK, "The orders get all");
    }

    // method to do business logic
    private List<Topping> getToppingList(List<OrderItemDetailRequestDTO> orderItemDetailRequestDTOList) {
        List<Long> idToppingList = new ArrayList<>();
        for (OrderItemDetailRequestDTO toppingDTO : orderItemDetailRequestDTOList) {
            idToppingList.add(toppingDTO.getToppingId());
        }
        return toppingRepository.findAllById(idToppingList);
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    private Integer getSumToppingQuantity(List<OrderItemDetailRequestDTO> orderItemDetailRequestDTOList) {
        Integer sumToppingQuantity = 0;
        for (OrderItemDetailRequestDTO toppingDTO : orderItemDetailRequestDTOList) {
            sumToppingQuantity += toppingDTO.getQuantityTopping();
        }
        return sumToppingQuantity;
    }

    private void checkInvalidToppingQuantity(Product product, Integer sumToppingQuantity){
        if (product.getMaxTopping() < sumToppingQuantity){
            throw  new InvalidSumToppingQuantityException("Invalid: " +
                product.getName() + " only have " +product.getMaxTopping() + " toppings");
        }
    }
    private Double calculatePriceOfItem(List<OrderItemDetailRequestDTO> orderItemDetailRequestDTOList, Product product, List<Topping> toppingList){
        Map<Long, Integer> map = orderItemDetailRequestDTOList
                .stream()
                .collect(Collectors.toMap(OrderItemDetailRequestDTO::getToppingId, OrderItemDetailRequestDTO::getQuantityTopping));

        double priceOfToppings = 0.0;
        for (Topping S : toppingList) {
            priceOfToppings += S.getPrice() * map.get(S.getId());
        }
        return priceOfToppings + product.getPrice();
    }
}

