package lk.ijse.backend.service;

import lk.ijse.backend.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {
    void savePayment(PaymentDTO paymentDTO);
    List<PaymentDTO> getPayment();
}
