package br.com.youtube.Project.Customer.persistence.repository;


import br.com.youtube.Project.Customer.persistence.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CostumerRepository extends JpaRepository<Customer, Long> {

}
