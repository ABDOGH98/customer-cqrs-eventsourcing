package ma.enset.customerqueryside.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.coreapi.queries.GetAllCustomersQuery;
import ma.enset.coreapi.queries.GetCustomerByIdQuery;
import ma.enset.customerqueryside.entities.Customer;
import ma.enset.customerqueryside.repositories.CustomerRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerQueryHandler {
    private CustomerRepository customerRepository;

    @QueryHandler
    public List<Customer> customerList(GetAllCustomersQuery query){
        return customerRepository.findAll();
    }

    @QueryHandler

    public Customer getCustomer(GetCustomerByIdQuery query){
        return customerRepository.findById(query.getId()).get() ;
    }
}
