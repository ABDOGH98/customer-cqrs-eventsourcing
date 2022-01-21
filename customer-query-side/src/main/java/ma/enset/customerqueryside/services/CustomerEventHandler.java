package ma.enset.customerqueryside.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.coreapi.events.CustomerCreatedEvent;
import ma.enset.customerqueryside.entities.Customer;
import ma.enset.customerqueryside.repositories.CustomerRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @AllArgsConstructor
@Transactional
@Slf4j
public class CustomerEventHandler {
    private CustomerRepository customerRepository;

    @EventHandler
    public void on(CustomerCreatedEvent event){
        log.info("******************");
        log.info("CustomerCreatedEvent received");
        Customer customer = new Customer();
        customer.setId(event.getId());
        customer.setName(event.getName());
        customer.setEmail(event.getEmail());
        customerRepository.save(customer);
    }

}
