package com.interfaces;

import com.model.Payment;

public interface PaymentDAO {
    boolean addPayment(Payment payment);
}