package fr.ldnr.web;


import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.entities.Article;
import fr.ldnr.entities.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class OrderController
{
    private final IBusinessImpl business;

    public OrderController(IBusinessImpl business) {
        this.business = business;
    }

    @GetMapping("/validateOrder")
    public String validate(Model model)
    {
        HashMap<Long , Article> cart = business.displayCart();
        model.addAttribute("customer" , business.displayCustomer());
        model.addAttribute("listArticle", cart);
        return "validateOrder";
    }

    @GetMapping("/annulationOrder")
    public String annulationOrder()
    {
        return "redirect:/cart";
    }

    @GetMapping("/confirmationOrder")
    public String confirmationOrder(Model model)
    {
        boolean isUserAuthenticated = business.isUserAuthenticated();
        model.addAttribute("isUserAuthenticated", isUserAuthenticated);
        return "confirmationOrder";
    }

    @GetMapping("/returnIndex")
    public String returnIndex()
    {
        return "redirect:/index";
    }

}