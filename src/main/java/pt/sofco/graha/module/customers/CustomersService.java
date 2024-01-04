package pt.sofco.graha.module.customers;

import org.springframework.data.domain.Page;

import java.util.Map;

public interface CustomersService {

    Page<DTO.respCustomers> list(Map<String, Object> filter);
    DTO.respCustomers create(DTO.reqCustomers request);
    void remove(DTO.reqrmvCustomers request);

}
