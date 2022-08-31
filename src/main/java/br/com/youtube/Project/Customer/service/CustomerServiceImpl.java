package br.com.youtube.Project.Customer.service;


import br.com.youtube.Project.Customer.model.request.CustomerRequest;
import br.com.youtube.Project.Customer.model.response.CustomerResponse;
import br.com.youtube.Project.Customer.persistence.entity.Customer;
import br.com.youtube.Project.Customer.persistence.repository.CostumerRepository;
import br.com.youtube.Project.Customer.service.mapper.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.awt.print.Pageable;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService {

    //Para logar as açoes da nossa aplicação
    //para rastrear erros (log)
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    //para injetar dependencia
    @Autowired
    private CostumerRepository costumerRepository;

    @Autowired
    private Mapper<CustomerRequest, Customer> requestMapper;

    @Autowired
    private Mapper<Customer, CustomerResponse> responseMapper;


    @Override
    public CustomerResponse create(CustomerRequest customerRequest) {
        LOGGER.info("Criando um registro do cliente");
        Assert.notNull(customerRequest, "Request inválida");

        Customer customer = this.requestMapper.map(customerRequest);
        return costumerRepository.saveAndFlush(customer).map((Customer input) -> this.responseMapper.map(input));
    }

    @Override
    public Optional get(Long id) {
        LOGGER.info("Buscando registros");
        Assert.notNull(id, "ID inválido");
        return costumerRepository.findById(id).map(this.responseMapper::map);
    }

    @Override
    public Page<CustomerResponse> getAll(Pageable pageable) {
        LOGGER.info("Buscando todos os registros");
        Assert.notNull(pageable, "Página inválida");
        return costumerRepository.findAll((org.springframework.data.domain.Pageable) pageable).map((Customer customer) -> this.responseMapper.map(customer));
    }

    @Override
    public Optional<CustomerResponse> update(Long id, CustomerRequest customerRequest) {
        LOGGER.info("Atualizando o registro");
        Assert.notNull(id, "ID inválido");
        Customer customerUpdate = this.requestMapper.map(customerRequest);
        return costumerRepository.findById(id).map(customer -> {
            customer.setName(customerUpdate.getName());

            return this.responseMapper.map(costumerRepository.saveAndFlush(customer));
        });
    }

    @Override
    public boolean delete(Long id) {
        LOGGER.info("Removendo registro");
        Assert.notNull(id, "ID inválido");
        try {
            costumerRepository.deleteById(id);
            return true;
        } catch (Exception e){
            LOGGER.warn("Erro ao remover o registro {}", id);
        }
        return false;
    }
}

