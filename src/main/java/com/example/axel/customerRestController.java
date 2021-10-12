package com.example.axel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/customers")
public class customerRestController {
    @Autowired
   private CustomerRepository repo;

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable ("id") Integer id){

        try {
            Customer c1 = repo.findById(id).get();
            repo.delete(c1);
            return ResponseEntity.ok(c1);
        }catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable ("id") Integer id,
            @RequestBody Customer customer){
        customer.setId(id);
        repo.save(customer);
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<?> addNewCustomer(@RequestBody Customer customer){
        repo.save(customer);
        return ResponseEntity.ok(customer);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Integer id) {

        try {
            Customer c1 = repo.findById(id).get();
           return ResponseEntity.ok(c1);
        }catch (Exception e) {

          return   ResponseEntity.status(404).body(null);
        }
    }

   @GetMapping
    public Iterable<Customer> getAllCustomer(
           @RequestParam(name = "_page", defaultValue = "1") Integer pageNum ,
           @RequestParam(name = "_limit", defaultValue = "10") Integer pageSize) {
       Pageable p = PageRequest.of(pageNum -1,pageSize);
        return repo.findAll(p).getContent();
    }
}
