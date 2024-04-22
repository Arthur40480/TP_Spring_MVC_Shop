package fr.ldnr.web;

import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.entities.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.ui.Model;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CartController {

    private final IBusinessImpl business;

    public CartController(IBusinessImpl business) {
        this.business = business;
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        boolean isUserAuthenticated = business.isUserAuthenticated();
        model.addAttribute("isUserAuthenticated", isUserAuthenticated);
        String errorMsg = (String) model.getAttribute("errorMessage");
        System.out.println("Message d'erreur: " + errorMsg);
        model.addAttribute("errorMessage", model.getAttribute("errorMessage"));

        HashMap<Long, Article> cart = business.displayCart();
        double total = business.getTotal();
        model.addAttribute("total", total);
        model.addAttribute("listArticle", cart);
        return "cart";
    }

    @GetMapping("/addToCart")
    public String addToCart(Long articleId) {
        business.addToCart(articleId);
        log.info("Article ajouter au panier avec succès");
        return "redirect:/index";
    }

    @GetMapping("/removeToCart")
    public String removeToCart(Model model, Long articleId) {
        boolean isUserAuthenticated = business.isUserAuthenticated();
        model.addAttribute("isUserAuthenticated", isUserAuthenticated);
        business.removeToCart(articleId);
        HashMap<Long, Article> cart = business.displayCart();
        double total = business.getTotal();
        model.addAttribute("total", total);
        model.addAttribute("listArticle", cart);
        return "cart";
    }
}
