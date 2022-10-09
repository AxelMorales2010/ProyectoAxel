package dev.c20.ecommerce.customer;
import dev.c20.ecommerce.customer.dto.CustomerDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {
    @Autowired
   private CustomerRepository repo;
    @Autowired
    private ModelMapper modelMapper;
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable ("id") Integer id){
        return repo.findById(id)
                .map(customerToDelete -> {
                    repo.delete(customerToDelete);
                    return "Cliente Eliminado";
                })
                .map(ResponseEntity::ok)
                .orElse( ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
     /*   try {
            Customer c1 = repo.findById(id).get();
            repo.delete(c1);
            return ResponseEntity.ok(c1);
        }catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }

      */
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(
            @PathVariable ("id") Integer id,
            @RequestBody CustomerDto customerDto){
        customerDto.setId(id);
      //  repo.save(customerDto);
        return ResponseEntity.ok(customerDto);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> addNewCustomer(@RequestBody CustomerDto customerDto){
        Customer customer=repo.save(CustomerDto.convertToEntity(customerDto));
        return ResponseEntity.ok(CustomerDto.convertToDto(customer));
    }

    @GetMapping ("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Integer id) {
       return repo.findById(id)
               .map(CustomerDto::convertToDto)
                .map(ResponseEntity::ok)
                .orElse( ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
      /*
        try {

            Customer c1 = repo.findById(id).get();
           return ResponseEntity.ok(c1);
        }catch (Exception e) {

          return   ResponseEntity.status(404).body(null);
        }

        */
    }
   @GetMapping
    public Iterable<Customer> getAllCustomer(
           @RequestParam(name = "_page", defaultValue = "1") Integer pageNum ,
           @RequestParam(name = "_limit", defaultValue = "10") Integer pageSize) {
       Pageable p = PageRequest.of(pageNum -1,pageSize);
        return repo.findAll(p).getContent();
    }
}
