package br.com.youtube.Project.Customer.v1;

import br.com.youtube.Project.Customer.model.request.CustomerRequest;
import br.com.youtube.Project.Customer.model.response.CustomerResponse;
import br.com.youtube.Project.Customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class CustomerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    //END POINTS
    @Autowired
    private CustomerService customerService;

    //Para ser restfull PostMapping
    @PostMapping
    public ResponseEntity<CustomerResponse> create(@RequestBody CustomerRequest customerRequest) {
        LOGGER.info("Requisição recebida");
        return ResponseEntity.ok(customerService.create(customerRequest));
    }

    //Para ser restfull GetMapping
    @GetMapping
    public ResponseEntity<Page<CustomerResponse>> getAll(Pageable pageable) {
        LOGGER.info("Buscando todos os registros");
        Page<CustomerResponse> customerResponses = customerService.getAll(pageable);
        return ResponseEntity.ok(customerResponses);
    }

    //Para ser restfull GetMapping
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(@PathVariable("id") Long id, @RequestBody CustomerRequest customerRequest) {
        LOGGER.info("Iniciando a atualização");
        Optional<CustomerResponse> update = customerService.update(id, customerRequest);
        if (!update.isPresent()) {
            //se nao esta presente
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(update.get());
    }

    //Para ser restfull GetMapping
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> get(@PathVariable("id") Long id) {
        LOGGER.info("Iniciando a busca pelo registro");
        Optional<CustomerResponse> customerResponse = customerService.get(id);
        if (!customerResponse.isPresent()) {
            //se nao esta presente
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerResponse.get());
    }

    //Para ser restfull DeleteMapping
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        LOGGER.info("Iniciando a remoção do registro");
        if (customerService.delete(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
