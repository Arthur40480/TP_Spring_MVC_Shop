package fr.ldnr.business;

import fr.ldnr.entities.Article;
import fr.ldnr.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface IBusiness {

    // ARTICLES
    public List<Article> findAllArticle();
    public Optional<Article> findArticleById(Long id);
    public Page<Article> findArticleByDescriptionContains(String keyword, Pageable pageable);
    public Page<Article> findArticlesByCategoryId(Long categoryId, Pageable pageable);
    public boolean createArticle(Article article);
    public boolean deleteArticleById(Long id);

    // CATEGORIES
    public List<Category> findAllCategories();

    //CART
    public void addToCart(Long articleId);
}
