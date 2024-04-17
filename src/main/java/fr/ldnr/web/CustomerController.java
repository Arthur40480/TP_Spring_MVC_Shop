package fr.ldnr.web;

import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.entities.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CustomerController
{
    private final IBusinessImpl business;

    public CustomerController(IBusinessImpl business) {
        this.business = business;
    }

    @GetMapping("/CustomerForm")
    public String toOrder( Model model)
    {
        model.addAttribute("customer", new Customer());
        return "CustomerForm";
    }

    @PostMapping("/saveCustomer")
    public String save(Model model, @Valid Customer customer , BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "customerForm";
        business.createCustomer(new Customer(customer.getName(), customer.getFirstName(),
                customer.getAddress(), customer.getEmail(), customer.getPhone()));
        return "validateOrder";
    }
}
