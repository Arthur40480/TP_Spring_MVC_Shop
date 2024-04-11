package fr.ldnr.business;

import fr.ldnr.entities.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IBusiness {
    // ARTICLES
    public Optional<Article> findArticleById(Long id);
    public Page<Article> findArticleByDescriptionContains(String keyword, Pageable pageable);
    public boolean createArticle(Article article);
    public boolean deleteArticleById(Long id);
}
