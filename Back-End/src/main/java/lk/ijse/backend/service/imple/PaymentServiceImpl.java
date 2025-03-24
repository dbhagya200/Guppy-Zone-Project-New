package lk.ijse.backend.service.imple;

import lk.ijse.backend.dto.PaymentDTO;
import lk.ijse.backend.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public void savePayment(PaymentDTO paymentDTO) {

    }

    @Override
    public List<PaymentDTO> getPayment() {
        return null;
    }
}
