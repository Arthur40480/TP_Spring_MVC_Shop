package fr.ldnr.web;

import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.entities.Article;
import fr.ldnr.entities.Category;
import fr.ldnr.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class ArticleController {

    private final IBusinessImpl business;

    public ArticleController(IBusinessImpl business) {
        this.business = business;
    }

    //@RequestMapping(value="/index", method=RequestMethod.GET)
    @GetMapping("/index")
    public String index(Model model, Long id ,@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "keyword", defaultValue = "") String kw) {
        List<Category> categories = business.findAllCategories();
        Page<Article> articles;
        if(id == null) {
            articles = business.findArticleByDescriptionContains(kw,(Pageable) PageRequest.of(page, 5));
            model.addAttribute("keyword", kw);
        }else {
            articles = business.findArticlesByCategoryId(id,(Pageable) PageRequest.of(page, 5));
            model.addAttribute("idCat", id);
        }
        model.addAttribute("listArticle", articles.getContent());
        model.addAttribute("listCategories", categories);
        model.addAttribute("pages", new int[articles.getTotalPages()]);
        model.addAttribute("currentPage", page);
        return "articles";
    }

    @GetMapping("/delete")
    public String delete(Long id, int page, String keyword) {
        business.deleteArticleById(id);
        return "redirect:/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/updateForm")
    public String updateForm(Model model, @RequestParam(name = "idArticle") Long id) {
        List<Category> categories = business.findAllCategories();
        Optional<Article> optionalArticleToUpdate = business.findArticleById(id);
        if(optionalArticleToUpdate.isPresent()) {
            Article articleToUpdate = optionalArticleToUpdate.get();
            model.addAttribute("article", articleToUpdate);
        }
        model.addAttribute("listCategories", categories);
        return "updateArticle";
    }

    @PostMapping("/update")
    public String update(Model model, Long id, @Valid Article articleToUpdate, BindingResult bindingResult) {
        List<Category> categories = business.findAllCategories();
        model.addAttribute("listCategories", categories);
        if(bindingResult.hasErrors()) {
            return "updateArticle";
        }else {
            System.out.println("nouvel article:" + articleToUpdate);
            business.updateArticle(articleToUpdate);
            return "redirect:/index";
        }
    }

    @GetMapping("/article")
    public String article(Model model) {
        List<Category> categories = business.findAllCategories();
        model.addAttribute("listCategories", categories);
        model.addAttribute("article", new Article());
        return "createArticle";
    }

    @PostMapping("/save")
    public String save(Model model, @Valid Article article, BindingResult bindingResult) {
        List<Category> categories = business.findAllCategories();
        model.addAttribute("listCategories", categories);
        if(bindingResult.hasErrors()) return "createArticle";
        business.createArticle(article);
        return "redirect:/index";
    }

    @GetMapping("/")
    public String log(){
        return "redirect:/index";
    }

    @GetMapping("/403")
    public String error() {
        return "403";
    }


}
