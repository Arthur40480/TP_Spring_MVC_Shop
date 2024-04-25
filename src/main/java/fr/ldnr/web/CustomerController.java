package fr.ldnr.web;

import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.entities.Article;
import fr.ldnr.entities.Customer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
public class CustomerController {

    private final IBusinessImpl business;

    public CustomerController(IBusinessImpl business) {
        this.business = business;
    }

    @GetMapping("/CustomerForm")
    public String toOrder( Model model) {
        boolean isUserAuthenticated = business.isUserAuthenticated();
        model.addAttribute("isUserAuthenticated", isUserAuthenticated);
        model.addAttribute("customer", new Customer());
        if(business.displayCart().isEmpty()) {
            model.addAttribute("listArticle", business.displayCart());
            model.addAttribute("errorMessage", "Veuillez remplir votre panier avant de valider votre commande");
            return "cart";
        }
        return "customerForm";
    }

    @PostMapping("/saveCustomer")
    public String save(Model model, @Valid Customer customer , BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        boolean isUserAuthenticated = business.isUserAuthenticated();
        model.addAttribute("isUserAuthenticated", isUserAuthenticated);
        if(bindingResult.hasErrors()) {
            log.error("Erreur de validation du formulaire: {}", bindingResult.getAllErrors());
            return "CustomerForm";
        }

        business.createCustomer(customer);
        HashMap<Long , Article> cart = business.displayCart();
        redirectAttributes.addFlashAttribute("customer", customer);
        redirectAttributes.addFlashAttribute("listArticle", cart);
        log.info("Customer crée avec succès: {}", customer);
        return "redirect:/validateOrder";
    }
}
