package guru.springframework.spring5webfluxrest.controller;
import guru.springframework.spring5webfluxrest.domain.Customer;
import guru.springframework.spring5webfluxrest.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/api/v1/customers";

    private CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getListOfCustomers(){
        return customerRepository.findAll();

    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Optional<Customer> getCustomerById(@PathVariable Long id){
        return customerRepository.findById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createNewCustomer(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
        customerRepository.findById(id);
        return customerRepository.save(customer);
    }

    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Customer patchCustomer(@PathVariable Long id, @RequestBody Customer customer){

        customerRepository.findById(id);

        customer.setFirstname(customer.getFirstname());

        return customerRepository.save(customer);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id){
        customerRepository.deleteById(id);
    }

}
