package br.com.youtube.Project.Customer.service;

import br.com.youtube.Project.Customer.model.request.CustomerRequest;
import br.com.youtube.Project.Customer.model.response.CustomerResponse;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.Optional;

public interface CustomerService{
    CustomerResponse create(CustomerRequest customerRequest);
    Optional<CustomerResponse> get(Long id);
    Page<CustomerResponse> getAll(Pageable pageable);
    Optional<CustomerResponse> update(Long id, CustomerRequest customerRequest);
    boolean delete (Long id);

}
