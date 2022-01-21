package ma.enset.customerqueryside.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.coreapi.queries.GetAllCustomersQuery;
import ma.enset.coreapi.queries.GetCustomerByIdQuery;
import ma.enset.customerqueryside.entities.Customer;
import org.apache.catalina.connector.Response;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/customers/query")
public class CustomerQueryController {
    private QueryGateway queryGateway;

    @GetMapping("/all")
    public CompletableFuture<List<Customer>> customers(){
    return queryGateway.query(new GetAllCustomersQuery(), ResponseTypes.multipleInstancesOf(Customer.class));
    }

    @GetMapping("/byId/{customerId}")
    public CompletableFuture<Customer> getCustomerById(@PathVariable String customerId){
        return queryGateway.query(new GetCustomerByIdQuery(customerId), ResponseTypes.instanceOf(Customer.class));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
