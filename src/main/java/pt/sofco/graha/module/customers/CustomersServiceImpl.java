package pt.sofco.graha.module.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pt.sofco.graha.common.Models;
import pt.sofco.graha.module.validation.ValidationService;

import java.util.List;
import java.util.Map;

@Service
public class CustomersServiceImpl implements CustomersService {

    @Autowired
    CustomersRepository repo;

    @Autowired
    ValidationService validation;

    @Transactional(readOnly = true)
    public Page<DTO.respCustomers> list(Map<String, Object> filter) {

        Models<CustomersEntity> models = new Models<>();
        Page<CustomersEntity> customersPage = repo.findAll(models.where(filter), models.pageableSort(filter));
        List<DTO.respCustomers> respCustomers = customersPage.getContent().stream().map(DTO::toRespCustomers).toList();
        if (respCustomers.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "list customers not found!");
        }

        return new PageImpl<>(respCustomers, customersPage.getPageable(), customersPage.getTotalElements());
    }

    @Transactional
    public DTO.respCustomers create(DTO.reqCustomers request) {
        validation.validate(request);
        CustomersEntity customers = new CustomersEntity();
        customers.setName(request.getName());
        customers.setAddress(request.getAddress());
        repo.save(customers);

        return DTO.toRespCustomers(customers);
    }

    @Transactional
    public void remove(DTO.reqrmvCustomers request) {
        validation.validate(request);
        CustomersEntity customers = repo.findFirstById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "customers not found!"));

        repo.delete(customers);
    }
}
