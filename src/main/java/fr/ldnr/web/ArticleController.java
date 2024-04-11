package fr.ldnr.web;

import ch.qos.logback.core.CoreConstants;
import fr.ldnr.dao.ArticleRepository;
import fr.ldnr.entities.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class ArticleController {
    @Autowired
    ArticleRepository articleRepository;

    //@RequestMapping(value="/index", method=RequestMethod.GET)
    @GetMapping("/index")
    public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "keyword", defaultValue = "") String kw) {
        Page<Article> articles = articleRepository.findByDescriptionContains(kw, (Pageable) PageRequest.of(page, 5));
        model.addAttribute("keyword", kw);
        model.addAttribute("listArticle", articles.getContent());
        model.addAttribute("pages", new int[articles.getTotalPages()]);
        model.addAttribute("currentPage", page);

        return "articles";
    }

    @GetMapping("/delete")
    public String delete(Long id, int page, String keyword) {
        articleRepository.deleteById(id);
        return "redirect:/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/updateForm")
    public String updateForm(Model model, @RequestParam(name = "idArticle") Long id) {
        Optional<Article> optionalArticleToUpdate = articleRepository.findById(id);
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
            Optional<Article> optionalArticleFromDB = articleRepository.findById(id);
            if(optionalArticleFromDB.isPresent()) {
                Article articleFromDB = optionalArticleFromDB.get();
                articleFromDB.setId(articleFromDB.getId());
                articleFromDB.setBrand(articleToUpdate.getBrand());
                articleFromDB.setDescription(articleToUpdate.getDescription());
                articleFromDB.setPrice(articleToUpdate.getPrice());
                articleRepository.save(articleFromDB);
            }
            return "redirect:/index";
        }
    }

    @GetMapping("/article")
    public String article(Model model) {
        model.addAttribute("article", new Article());
        return "article";
    }

    @PostMapping("/save")
    public String save(Model model, @Valid Article article, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "article";
        articleRepository.save(article);
        return "redirect:/index";
    }
}
