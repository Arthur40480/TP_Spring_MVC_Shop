package fr.ldnr.business;

import fr.ldnr.dao.ArticleRepository;
import fr.ldnr.dao.CategoryRepository;
import fr.ldnr.entities.Article;
import fr.ldnr.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IBusinessImpl implements IBusiness {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CategoryRepository categoryRepository;

    /**
     * Récupère tous les articles
     * @return Liste contenant tous les articles
     */
    public List<Article> findAllArticle() {
        return articleRepository.findAll();
    }

    /**
     * Récupère un article par son identifiant
     * @param id Identifiant de l'article à récupérer
     * @return Optional -> Article sinon Null
     */
    public Optional<Article> findArticleById(Long id) {
        return articleRepository.findById(id);
    }

    /**
     * Récupère une page d'article
     * @param keyword Le mot clé rechercher
     * @param pageable l'objet Pageable(numéro de page, taille de page)
     * @return Page d'article
     */
    public Page<Article> findArticleByDescriptionContains(String keyword, Pageable pageable) {
        return articleRepository.findByDescriptionContains(keyword, pageable);
    }

    /**
     * Récupère une page d'article appartenant à une catégorie
     * @param categoryId l'id de la catégorie
     * @param pageable l'objet Pageable(numéro de page, taille de page)
     * @return Page d'article
     */
    public Page<Article> findArticlesByCategoryId(Long categoryId, Pageable pageable) {
        return articleRepository.findByCategoryId(categoryId, pageable);
    }

    /**
     * Crée un nouvel article
     * @param newArticle Article à créer
     * @return true si l'article à été crée avec succès, false sinon
     */
    public boolean createArticle(Article newArticle) {
        List<Article> articleList = articleRepository.findAll();
        for (Article article : articleList) {
            if (newArticle.getDescription().equals(article.getDescription()) && newArticle.getBrand().equals(article.getBrand())) {
                return false;
            }
        }
        articleRepository.save(newArticle);
        return true;
    }

    /**
     * Supprime un article par son id
     * @param id id de l'article à supprimer
     * @return true si l'article à été supprimé avec succès, false sinon
     */
    public boolean deleteArticleById(Long id) {
        Optional<Article> optional = articleRepository.findById(id);
        if(optional.isPresent()) {
            articleRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    /**
     * Récupère toute les catégories
     * @return List des catégories
     */
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
}
