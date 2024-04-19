package fr.ldnr.web;


import com.fasterxml.jackson.core.JsonProcessingException;
import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.entities.Article;
import fr.ldnr.entities.Customer;
import fr.ldnr.entities.Commande;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Optional;

@Controller
public class OrderController
{
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final IBusinessImpl business;

    public OrderController(IBusinessImpl business) {
        this.business = business;
    }

    @GetMapping("/validateOrder")
    public String validate(Model model) {
        Customer customer = (Customer) model.getAttribute("customer");
        HashMap<Long , Article> cart = business.displayCart();

        model.addAttribute("customer" , customer);
        model.addAttribute("listArticle", cart);
        return "validateOrder";
    }

    @GetMapping("/annulationOrder")
    public String annulationOrder()
    {
        return "redirect:/cart";
    }

    @GetMapping("/confirmationOrder")
    public String confirmationOrder(Model model , @RequestParam(name = "customId") Long id) throws JsonProcessingException {
        boolean isUserAuthenticated = business.isUserAuthenticated();
        model.addAttribute("isUserAuthenticated", isUserAuthenticated);

        Optional<Customer> customerbdd = business.findCustomerById(id);
        Customer customer = customerbdd.get();

        Commande order = new Commande(business.getTotal() , customer.getId());
        business.createOrder(order);
        System.out.println(customer);
        return "confirmationOrder";
    }
}

