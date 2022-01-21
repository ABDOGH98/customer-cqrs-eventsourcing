package ma.enset.customercommandside.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.coreapi.commands.CreateCustomerCommand;
import ma.enset.coreapi.dtos.CustomerRequestDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/customers/commands")
public class CustomerCommandController {
    private CommandGateway commandGateway;
    private EventStore eventStore;

    @PostMapping("/create")
    public CompletableFuture<String> newCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        CompletableFuture<String> response = commandGateway.send(new CreateCustomerCommand(
                UUID.randomUUID().toString(),
                customerRequestDto.getName(),
                customerRequestDto.getEmail()
        ));
        return response;
    }

    @GetMapping("/eventStore/{customerId}")
    public Stream eventStore(@PathVariable String customerId){
        return eventStore.readEvents(customerId).asStream();
    }
}
