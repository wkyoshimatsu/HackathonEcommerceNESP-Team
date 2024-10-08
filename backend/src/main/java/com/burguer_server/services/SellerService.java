package com.burguer_server.services;

import com.burguer_server.model.product.Product;
import com.burguer_server.model.product.Stock;
import com.burguer_server.model.user.Seller;
import com.burguer_server.payloads.seller.SellerPayloadRequest;
import com.burguer_server.payloads.stock.StockPayloadRequest;
import com.burguer_server.repositories.SellerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class SellerService {

    @Autowired
    private SellerRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StockService stockService;

    @Autowired
    private ProductService productService;

    public Seller save(SellerPayloadRequest payload) {
        var sellerConvertido = new Seller(payload);
        sellerConvertido.setPassword(passwordEncoder.encode(sellerConvertido.getPassword())); // Transforma a senha em BCrypt

        if (payload.sellerStock() == null) {
            sellerConvertido.setSellerStock(null);
        }

        // Salva o seller
        return repository.save(sellerConvertido);
    }


    public Seller findById(Long id) {
        var seller = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Seller não encontrado"));
        return seller;
    }

    public List<Seller> findAll() {
        var list = repository.findAll();
        return list;
    }

    public Stock saveStockInSeller(Long idSeller, StockPayloadRequest stock) {
        var seller = findById(idSeller);
        var stockConvertido = new Stock(stock);
        productService.saveAll(stockConvertido.getStockProduct());
        stockService.save(stockConvertido);

        stockConvertido.getStockProduct().stream().forEach(p -> p.setStock(stockConvertido));
        productService.saveAll(stockConvertido.getStockProduct());

        seller.setSellerStock(stockConvertido);
        repository.save(seller);

        return stockService.save(stockConvertido);
    }

    public Stock findStockBySeller(Long idSeller) {
        var seller = findById(idSeller);
        var stockId = seller.getSellerStock().getStockId();

        return stockService.findById(stockId);
    }

    //Mostra a lista de produtos que existem no stock do seller
    public Set<Product> findStockProducts(Long idSeller) {
        var seller = findById(idSeller);
        var idStock = findById(idSeller).getSellerStock().getStockId();
        var list = stockService.stockProducts(idStock);
        return list;
    }

    public void deleteById(Long id) {
        var seller = findById(id);

        repository.deleteById(id);
    }

    public Seller update(Long id, SellerPayloadRequest payload) {
        var seller = findById(id);

        seller.setEmail(payload.email());
        seller.setPassword(passwordEncoder.encode(payload.password()));
        seller.setSellerStock(payload.sellerStock());

        return repository.save(seller);
    }


}
