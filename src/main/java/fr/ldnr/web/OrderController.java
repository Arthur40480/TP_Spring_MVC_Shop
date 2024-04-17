package fr.ldnr.web;


import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.entities.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController
{
    private final IBusinessImpl business;

    public OrderController(IBusinessImpl business) {
        this.business = business;
    }

    @GetMapping("/validateOrder")
    public String validate(Model mode , Customer customer)
    {
        mode.addAttribute("customer" , business.createCustomer(customer));
        return "redirect:/index";
    }
}
