package com.luv2code.springdemo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model theModel) {
        List<Customer> theCustomers = customerService.getCustomers();
        theModel.addAttribute("customers", theCustomers);
        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        // create model attribute to bind form data
        Customer theCustomer = new Customer();

        theModel.addAttribute("customer", theCustomer);
        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
        customerService.saveCustomer(theCustomer);
        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
        // get the customer from the database
        Customer theCustomer = customerService.getCustomer(theId);

        //set customer as a model attribute to pre-populate the form
        theModel.addAttribute("customer", theCustomer);

        //sned over to our form
        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int theId) {
        customerService.deleteCustomer(theId);
        return "redirect:/customer/list";
    }

    @GetMapping("/search")
    public String deleteCustomer(@RequestParam("theSearchName") String theSearchName, Model theModel) {
        List<Customer> theCustomers = customerService.getCustomersByName(theSearchName);
        theModel.addAttribute("customers", theCustomers);
        return "list-customers";
    }
}
