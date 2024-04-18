package fr.ldnr.web;

import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.entities.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.ui.Model;

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
        HashMap<Long, Article> cart = business.displayCart();
        double total = business.getTotal();
        model.addAttribute("total", total);
        model.addAttribute("listArticle", cart);
        return "cart";
    }

    @GetMapping("/addToCart")
    public String addToCart(Long articleId) {
        business.addToCart(articleId);
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
