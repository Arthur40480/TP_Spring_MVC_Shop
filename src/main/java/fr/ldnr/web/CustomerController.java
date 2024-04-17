package fr.ldnr.web;

import ch.qos.logback.core.net.SyslogOutputStream;
import fr.ldnr.entities.Article;
import fr.ldnr.entities.Category;
import fr.ldnr.entities.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CustomerController
{
    @GetMapping("/CustomerForm")
    public String toOrder( Model model)
    {
        model.addAttribute("customer", new Customer());
        return "CustomerForm";
    }

    @PostMapping("/saveCustomer")
    public String save(Model model, @Valid Customer customer , BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "customerForm";
        System.out.println(new Customer(customer.getName(), customer.getFirstName(), customer.getAddress(), customer.getEmail(), customer.getPhone()));
        return "validateOrder";
    }
}
