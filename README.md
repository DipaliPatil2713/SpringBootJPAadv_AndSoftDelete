# SpringBootJPAadv_AndSoftDelete
Data JPA with JQL and Soft Delete Function 

      Q. What is Soft deleted?
----> Soft delete is a technique where a record is not actually deleted from the database, but instead, it is marked as deleted using a special column (e.g., deleted or is_active). This allows the data to remain 
      in the database but hidden from users.
      

//Code for fetching data, Soft deleted data will not be fetched but saved in Database as it is.
//Service layer 
@Override
public Optional<Customer> findById(int custId) {
return Optional.ofNullable(customerRepository.findById(custId).map(customer -> customer.isDeleted() ? null : customer).orElse(null));}

//Here it will only find the non deleted data , Soft deleted data will not be seen here.
 @Override
    public List<Customer> findAll() {
     return customerRepository.findAll().stream().filter(customer -> !customer.isDeleted()).collect(Collectors.toList()); //Returns only non deleted customer
    }


//Data is only deleted from user end but saved in Database
// Controller Layer
@DeleteMapping("/deletebyid/{custId}")
    public ResponseEntity<String> deleteById(@PathVariable int custId) {

        Optional<Customer>customer =customerService.findById(custId);
        if (customer.isPresent()){
            Customer existingCustomer = customer.get();
            existingCustomer.setDeleted(true); // here we marked deleted
            customerService.save(existingCustomer);
            return ResponseEntity.ok("Customer Data Deleted Sucessfully");
        }else
        return ResponseEntity.notFound().build();
    }


//Repository Layer coding( Custom Method )
      // Fetch only non-deleted customers and for finding partial similar names and also by lowercase and uppercase data.
    @Query("SELECT c FROM Customer c WHERE c.deleted = false AND LOWER(c.custName) LIKE LOWER(CONCAT('%', :custName, '%'))")
    List<Customer> findByCustName(String custName);

