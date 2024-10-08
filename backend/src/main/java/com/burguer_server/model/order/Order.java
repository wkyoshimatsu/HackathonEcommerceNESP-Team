package com.burguer_server.model.order;

import com.burguer_server.model.user.Buyer;
import com.burguer_server.model.payment.Payment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders_tb")
@Getter
@Setter
@EqualsAndHashCode(of = "orderId")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(nullable = false)
    private double orderDeliveryTax;

    @Column(nullable = false)
    private double orderTotal;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id", referencedColumnName = "idPayment")
    private Payment orderPayment;

    private String currentOrderStatus;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderStatus> statusHistory;

    private float calculateOrderTotal(){
        return 0.0f;
    }
    private void addOrderItem(OrderItem item){

    }
    private void updateOrderItem(OrderItem item){

    }
    private void removeOrderItem(OrderItem item){

    }

}
