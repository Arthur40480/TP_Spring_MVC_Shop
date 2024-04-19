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
        Customer customer = (Customer) model.getAttribute("customer");
        HashMap<Long , Article> cart = business.displayCart();

        model.addAttribute("customer" , customer);
        model.addAttribute("listArticle", cart);
        return "validateOrder";
    }

    @GetMapping("/annulationOrder")
    public String annulationOrder() {
        business.displayCart().clear();
        return "redirect:/index";
    }

    @GetMapping("/confirmationOrder")
    public String confirmationOrder(Model model , @RequestParam(name = "customId") Long id) {
        boolean isUserAuthenticated = business.isUserAuthenticated();
        model.addAttribute("isUserAuthenticated", isUserAuthenticated);
        Optional<Customer> optionalCustomer = business.findCustomerById(id);
        if(optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            Commande commande = new Commande(business.getTotal(), customer);
            business.createOrder(commande);
            business.createOrderArticleFromCart(business.displayCart(), commande);
        }
        business.displayCart().clear();
        return "confirmationOrder";
    }
}

