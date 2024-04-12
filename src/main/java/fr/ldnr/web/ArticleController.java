package fr.ldnr.web;


import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.dao.ArticleRepository;
import fr.ldnr.entities.Article;

import fr.ldnr.dao.CategoryRepository;

import fr.ldnr.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;

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
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CategoryRepository categoryRepository;

    //@RequestMapping(value="/index", method=RequestMethod.GET)
    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name="categoryId", required = false)Long categoryId,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "keyword", defaultValue = "") String kw) {
        Page<Article> articles;
        if(categoryId != null) {
            model.addAttribute("categoryId", categoryId);
            articles = articleRepository.findByCategoryId(categoryId, (Pageable) PageRequest.of(page, 5));
        }else {
            articles = business.findArticleByDescriptionContains(kw, (Pageable) PageRequest.of(page, 5));
        }

        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("keyword", kw);
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
        Optional<Article> optionalArticleToUpdate = business.findArticleById(id);
        if(optionalArticleToUpdate.isPresent()) {
            Article articleToUpdate = optionalArticleToUpdate.get();
            model.addAttribute("article", articleToUpdate);
        }
        return "updateArticle";
    }

    @PostMapping("/update")
    public String update(Long id, @Valid Article articleToUpdate, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "updateArticle";
        }else {
            Optional<Article> optionalArticleFromDB = business.findArticleById(id);
            if(optionalArticleFromDB.isPresent()) {
                Article articleFromDB = optionalArticleFromDB.get();
                articleFromDB.setId(articleFromDB.getId());
                articleFromDB.setBrand(articleToUpdate.getBrand());
                articleFromDB.setDescription(articleToUpdate.getDescription());
                articleFromDB.setPrice(articleToUpdate.getPrice());
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


    //page de connexion
    @GetMapping("/loggin")
    public String loggin(){
        return "loggin";
    }

    @GetMapping("/catArticles")
    public String catArticles(Model model, @RequestParam(name="categoryId") Long id, @RequestParam(name = "page", defaultValue = "0") int page)
    {
        Page<Article> articles = categoryRepository.findArticlesByCategoryId(id ,  (Pageable) PageRequest.of(page, 5));
        List<Category> categories = categoryRepository.findAll();

        model.addAttribute("categoryId", id);
        model.addAttribute("listArticle", articles.getContent());
        model.addAttribute("listCategories" , categories);
        model.addAttribute("pages", new int[articles.getTotalPages()]);
        model.addAttribute("currentPage", page);

        return "articles";
    }

}
