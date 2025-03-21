package com.fullstack.repository;

import com.fullstack.model.Customer;
import org.springframework.boot.autoconfigure.mustache.MustacheResourceTemplateLoader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // Custom methods

    Customer findByCustEmailId(String custEmailId);

    // Fetch only non-deleted customers
    @Query("SELECT c FROM Customer c WHERE c.deleted = false AND LOWER(c.custName) LIKE LOWER(CONCAT('%', :custName, '%'))")
    // @Query("SELECT c FROM Customer c WHERE LOWER(c.custName) LIKE LOWER(CONCAT('%', :custName, '%'))") //Only for the partial words and lowercase and uppercase
    List<Customer> findByCustName(String custName);

    Customer findByCustEmailIdAndCustPassword(String custEmailId, String custPassword);


}
