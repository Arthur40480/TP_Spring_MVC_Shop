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
@SessionAttributes("customer")
public class OrderController {
    private final IBusinessImpl business;

    public OrderController(IBusinessImpl business) {
        this.business = business;
    }

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
        if(customer == null) {
            model.addAttribute("customer", new Customer());
            model.addAttribute("errorMessage", "Veuillez remplir les informations avant de valider votre commande");
            return "CustomerForm";
        }
        model.addAttribute("customer" , customer);
        model.addAttribute("listArticle", cart);
        return "validateOrder";
    }

    @GetMapping("/annulationOrder")
    public String annulationOrder(Model model) {
        business.displayCart().clear();
        model.addAttribute("customer" , null);
        return "redirect:/index";
    }

    @GetMapping("/confirmationOrder")
    public String confirmationOrder(@ModelAttribute("customer") Customer customer, Model model) {
        boolean isUserAuthenticated = business.isUserAuthenticated();
        model.addAttribute("isUserAuthenticated", isUserAuthenticated);

        System.out.println(customer);
        if(business.displayCart().isEmpty()) {
            System.out.println("Le panier est vide !");
            model.addAttribute("listArticle", business.displayCart());
            model.addAttribute("errorMessage", "Veuillez remplir votre panier avant de valider votre commande");
            return "cart";
        }
        if(customer == null) {
            System.out.println("Le customer est null");
            model.addAttribute("customer", new Customer());
            model.addAttribute("errorMessage", "Veuillez remplir les informations avant de valider votre commande");
            return "CustomerForm";
        }
        Commande commande = new Commande(business.getTotal(), customer);
        business.createOrder(commande);
        business.createOrderArticleFromCart(business.displayCart(), commande);
        business.displayCart().clear();
        model.addAttribute("customer" , null);
        return "confirmationOrder";
    }

}

