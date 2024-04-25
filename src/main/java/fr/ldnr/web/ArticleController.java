package fr.ldnr.web;

import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.entities.Article;
import fr.ldnr.entities.Category;
import fr.ldnr.exceptions.ArticleException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ArticleController {

    private final IBusinessImpl business;
    private static final String VALIDATION_ERROR_MSG = "Erreur de validation du formulaire: {}";
    private static final String ARTICLE_LOAD_SUCCESS_MSG = "Chargement des articles réussi.";

    public ArticleController(IBusinessImpl business) {
        this.business = business;
    }

    @GetMapping("/index")
    public String index(Model model, Long id , @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "keyword", defaultValue = "") String kw, Authentication authentication) {
        boolean isUserAuthenticated = business.isUserAuthenticated();
        model.addAttribute("isUserAuthenticated", isUserAuthenticated);
        List<Category> categories = business.findAllCategories();
        Page<Article> articles;
        if(id == null) {
            articles = business.findArticleByDescriptionContains(kw,(Pageable) PageRequest.of(page, 5));
            model.addAttribute("keyword", kw);
            log.info(ARTICLE_LOAD_SUCCESS_MSG);
        }else {
            articles = business.findArticlesByCategoryId(id,(Pageable) PageRequest.of(page, 5));
            model.addAttribute("idCat", id);
            log.info(ARTICLE_LOAD_SUCCESS_MSG);
        }
        model.addAttribute("listArticle", articles.getContent());
        model.addAttribute("listCategories", categories);
        model.addAttribute("pages", new int[articles.getTotalPages()]);
        model.addAttribute("currentPage", page);
        return "articles";
    }

    @GetMapping("/delete")
    public String delete(Long id, int page, String keyword) {
        List<Category> categories = business.findAllCategories();
        boolean isUserAuthenticated = business.isUserAuthenticated();
        business.deleteArticleById(id);
        return "redirect:/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/updateForm")
    public String updateForm(Model model, @RequestParam(name = "idArticle") Long id) {
        boolean isUserAuthenticated = business.isUserAuthenticated();
        model.addAttribute("isUserAuthenticated", isUserAuthenticated);
        List<Category> categories = business.findAllCategories();
        Optional<Article> optionalArticleToUpdate = business.findArticleById(id);
        if(optionalArticleToUpdate.isPresent()) {
            Article articleToUpdate = optionalArticleToUpdate.get();
            model.addAttribute("article", articleToUpdate);
            log.info(ARTICLE_LOAD_SUCCESS_MSG);
        }
        model.addAttribute("listCategories", categories);
        return "updateArticle";
    }

    @PostMapping("/update")
    public String update(Model model, Long id, @Valid Article articleToUpdate, BindingResult bindingResult) {
        boolean isUserAuthenticated = business.isUserAuthenticated();
        model.addAttribute("isUserAuthenticated", isUserAuthenticated);
        List<Category> categories = business.findAllCategories();
        model.addAttribute("listCategories", categories);

        if(bindingResult.hasErrors()) {
            log.error(VALIDATION_ERROR_MSG, bindingResult.getAllErrors());
            return "updateArticle";
        }else {
            business.updateArticle(articleToUpdate);
            log.info("Article modifier avec succès: {}", articleToUpdate);
            return "redirect:/index";
        }
    }

    @GetMapping("/article")
    public String article(Model model) {
        boolean isUserAuthenticated = business.isUserAuthenticated();
        List<Category> categories = business.findAllCategories();
        model.addAttribute("isUserAuthenticated", isUserAuthenticated);
        model.addAttribute("listCategories", categories);
        model.addAttribute("article", new Article());
        return "createArticle";
    }

    @PostMapping("/save")
    public String save(Model model, @Valid Article article, BindingResult bindingResult) throws ArticleException {
        boolean isUserAuthenticated = business.isUserAuthenticated();
        List<Category> categories = business.findAllCategories();
        model.addAttribute("isUserAuthenticated", isUserAuthenticated);
        model.addAttribute("listCategories", categories);

        if(bindingResult.hasErrors()) {
            log.error(VALIDATION_ERROR_MSG, bindingResult.getAllErrors());
            return "createArticle";
        }

        try {
            business.createArticle(article);
        } catch (ArticleException e) {
            log.error("Erreur lors de la création de l'article: {}", e.getMessage(), e);
            model.addAttribute("errorMessage", e.getMessage());
            return "createArticle";
        }
        log.info("Article créé avec succès: {}", article);
        return "redirect:/index";
    }

    @GetMapping("/")
    public String log(){
        return "redirect:/index";
    }

    @GetMapping("/403")
    public String error(Model model) {
        boolean isUserAuthenticated = business.isUserAuthenticated();
        model.addAttribute("isUserAuthenticated", isUserAuthenticated);
        return "403";
    }
}
