package fr.ldnr.web;

import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.entities.Article;
import fr.ldnr.entities.Customer;
import fr.ldnr.entities.Commande;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.HashMap;

@Controller
@SessionAttributes("customer") //gère le client
public class OrderController {
    private final IBusinessImpl business;

    public OrderController(IBusinessImpl business) {
        this.business = business;
    }

    //code OK de lundi 22/4
    @GetMapping("/validateOrder")
    public String validate(Model model) {
        boolean isUserAuthenticated = business.isUserAuthenticated();
        model.addAttribute("isUserAuthenticated", isUserAuthenticated);

        Customer customer = (Customer) model.getAttribute("customer");
        HashMap<Long , Article> cart = business.displayCart();
        if(customer == null || business.displayCart().isEmpty()) {
            if(business.displayCart().isEmpty()) {
                model.addAttribute("listArticle", business.displayCart());
                model.addAttribute("errorMessage", "Veuillez remplir votre panier avant de valider votre commande");
                return "cart";
            }else {
                model.addAttribute("customer", new Customer());
                model.addAttribute("errorMessage", "Veuillez remplir les informations avant de valider votre commande");
                return "CustomerForm";
            }
        }else {
            model.addAttribute("customer" , customer);
            model.addAttribute("listArticle", cart);
            return "validateOrder";
        }
    }

    //Code qui pose souci
/*
    @GetMapping("/validateOrder")
    public String validate(@ModelAttribute("customer") Customer customer, Model model) {
        boolean isUserAuthenticated = business.isUserAuthenticated();
        model.addAttribute("isUserAuthenticated", isUserAuthenticated);

        HashMap<Long , Article> cart = business.displayCart();
        if(business.displayCart().isEmpty()) {
            model.addAttribute("listArticle", business.displayCart());
            model.addAttribute("errorMessage", "Veuillez remplir votre panier avant de valider votre commande");
            return "cart";
        }
        if(customer.getEmail() == null) {
            model.addAttribute("customer", new Customer());
            model.addAttribute("errorMessage", "Veuillez remplir les informations avant de valider votre commande");
            return "CustomerForm";
        }
        model.addAttribute("customer" , customer);
        model.addAttribute("listArticle", cart);
        return "validateOrder";
    }
*/

    @GetMapping("/annulationOrder")
    public String annulationOrder(Model model) {
        business.displayCart().clear();
        model.addAttribute("customer" , new Customer());
        return "redirect:/index";
    }

    //code de lundi 22/4
    @GetMapping("/confirmationOrder")
    public String confirmationOrder(Model model) {
        boolean isUserAuthenticated = business.isUserAuthenticated();
        model.addAttribute("isUserAuthenticated", isUserAuthenticated);

        Customer customer = (Customer) model.getAttribute("customer");
        if(customer == null || business.displayCart().isEmpty()) {
            if(business.displayCart().isEmpty()) {
                model.addAttribute("listArticle", business.displayCart());
                model.addAttribute("errorMessage", "Veuillez remplir votre panier avant de valider votre commande");
                return "cart";
            }else {
                model.addAttribute("customer", new Customer());
                model.addAttribute("errorMessage", "Veuillez remplir les informations avant de valider votre commande");
                return "CustomerForm";
            }
        }else {
            Commande commande = new Commande(business.getTotal(), customer);
            business.createOrder(commande);
            business.createOrderArticleFromCart(business.displayCart(), commande);
            business.displayCart().clear();
            return "confirmationOrder";
        }
    }
    //code qui pose souci
    /*
    @GetMapping("/confirmationOrder")
    public String confirmationOrder(@ModelAttribute("customer") Customer customer, Model model) {
        boolean isUserAuthenticated = business.isUserAuthenticated();
        model.addAttribute("isUserAuthenticated", isUserAuthenticated);

        if(business.displayCart().isEmpty()) {
            model.addAttribute("listArticle", business.displayCart());
            model.addAttribute("errorMessage", "Veuillez remplir votre panier avant de valider votre commande");
            return "cart";
        }
        if(customer.getEmail() == null) {
            model.addAttribute("customer", new Customer());
            model.addAttribute("errorMessage", "Veuillez remplir les informations avant de valider votre commande");
            return "CustomerForm";
        }
        Commande commande = new Commande(business.getTotal(), customer);
        business.createOrder(commande);
        business.createOrderArticleFromCart(business.displayCart(), commande);
        business.displayCart().clear();
        model.addAttribute("customer" , new Customer());
        return "confirmationOrder";
    }
     */

}

