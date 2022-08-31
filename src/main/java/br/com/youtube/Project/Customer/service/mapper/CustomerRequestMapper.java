package br.com.youtube.Project.Customer.service.mapper;

import br.com.youtube.Project.Customer.model.request.CustomerRequest;
import br.com.youtube.Project.Customer.persistence.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerRequestMapper implements Mapper<CustomerRequest, Customer>{
    @Override
    public Customer map(CustomerRequest input) {
        if(input == null){
            //se nao tiver valor, interrompe o programa
            return null;
        }
        //se tiver valor a gente inicia um espaço na memoria pra fala que é o "Customer"
        Customer customer = new Customer();
        customer.setName(input.getName());
        customer.setDocument(input.getDocument());

        return customer;
    }
    //A -> Customer Request
    //b -> Customer

}
