package com.interfaces;

import com.model.Payment;
import java.util.List;

public interface PaymentDAO {
    boolean addPayment(Payment payment);
    Payment getPaymentById(int paymentId);
    List<Payment> getPaymentsByBookingId(int bookingId);
    boolean updatePaymentStatus(int paymentId, String status);
    boolean deletePayment(int paymentId);
    List<Payment> getAllPayments();
}