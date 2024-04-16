package fr.ldnr.web;

import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.entities.Article;
import fr.ldnr.entities.Category;
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
    public String update(Long id, @Valid Article articleToUpdate, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            System.out.println("Erreur !");
            return "updateArticle";
        }else {
            Optional<Article> optionalArticleFromDB = business.findArticleById(id);
            Optional<Category> optionalCategory= business.findCategoryById(articleToUpdate.getCategory().getId());
            optionalCategory.ifPresent(System.out::println);
            if(optionalArticleFromDB.isPresent()) {
                Article articleFromDB = optionalArticleFromDB.get();
                articleFromDB.setId(articleFromDB.getId());
                articleFromDB.setBrand(articleToUpdate.getBrand());
                articleFromDB.setDescription(articleToUpdate.getDescription());
                articleFromDB.setPrice(articleToUpdate.getPrice());
                articleFromDB.setCategory(articleToUpdate.getCategory());
                System.out.println("Catégorie séléctionner:" + articleToUpdate.getCategory());
                business.createArticle(articleFromDB);
            }
            return "redirect:/index";
        }
    }

    @GetMapping("/article")
    public String article(Model model) {
        model.addAttribute("article", new Article());
        return "createArticle";
    }

    @PostMapping("/save")
    public String save(Model model, @Valid Article article, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "article";
        business.createArticle(article);
        return "redirect:/index";
    }

    @GetMapping("/loggin")
    public String loggin(){
        return "loggin";
    }

}
