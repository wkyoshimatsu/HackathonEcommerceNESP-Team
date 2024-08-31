package com.burguer_server.model;

import com.burguer_server.model.enums.PaymentMethod;
import com.burguer_server.model.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payment")
@Getter
@Setter
@EqualsAndHashCode(of = "idPayment")
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPayment;

    private Order order;
    private float total;
    private boolean isPaid;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private void processPayment(){

    }
    private void updatePaymentStatus(/*PaymentStatus paymentStatus*/){

    }
}
