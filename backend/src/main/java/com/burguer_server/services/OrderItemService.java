package com.burguer_server.services;

import com.burguer_server.model.order.OrderItem;
import com.burguer_server.repositories.OrderItemRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrderItemService {
    
    @Autowired
    private OrderItemRepository repository;

    public OrderItem save(OrderItem orderItem) {

        return repository.save(orderItem);
    }

    public List<OrderItem> saveAll(List<OrderItem> orderItem) {

        return repository.saveAll(orderItem);
    }

    public OrderItem findById(Long id) {
        var orderItem = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("OrderItem não encontrado"));
        return orderItem;
    }

    public List<OrderItem> findAll() {
        var list = repository.findAll();
        return list;
    }

    public void deleteById(Long id) {
        var orderItem = findById(id);

        repository.deleteById(id);
    }

    public OrderItem update(Long id, OrderItem payload) {
        var orderItem = findById(id);

        orderItem.setOrder(payload.getOrder());
        orderItem.setNotes(payload.getNotes());
        orderItem.setProduct(payload.getProduct());
        orderItem.setQuantity(payload.getQuantity());

        return repository.save(orderItem);
    }

}
