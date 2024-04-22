package fr.ldnr.web;


import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.entities.Article;
import fr.ldnr.entities.Customer;
import fr.ldnr.entities.Commande;
import fr.ldnr.entities.OrderArticle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.HashMap;
import java.util.Optional;

@Controller
public class OrderController {
    private final IBusinessImpl business;

    public OrderController(IBusinessImpl business) {
        this.business = business;
    }

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

    @GetMapping("/annulationOrder")
    public String annulationOrder() {
        business.displayCart().clear();
        return "redirect:/index";
    }

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
}

