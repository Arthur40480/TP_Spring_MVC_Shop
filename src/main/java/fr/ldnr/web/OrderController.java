package fr.ldnr.web;


import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.entities.Article;
import fr.ldnr.entities.Commande;
import fr.ldnr.entities.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Optional;

@Controller
public class OrderController
{
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
    public String confirmationOrder(Model model , @RequestParam(name = "customId") Long id)
    {
        boolean isUserAuthenticated = business.isUserAuthenticated();
        model.addAttribute("isUserAuthenticated", isUserAuthenticated);

        HashMap<Long , Article> order = business.displayCart();
        Optional<Customer> customerbdd = business.findCustomerById(id);
        Customer customer = customerbdd.get();
        System.out.println(customer);
        System.out.println(order);
        Commande commandevalivation = new Commande(customer , order);
        business.createOrder(commandevalivation);
        return "confirmationOrder";
    }
}
