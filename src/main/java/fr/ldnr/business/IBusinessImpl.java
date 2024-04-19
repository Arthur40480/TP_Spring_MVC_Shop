package fr.ldnr.business;

import fr.ldnr.dao.*;
import fr.ldnr.entities.*;
import fr.ldnr.exceptions.ArticleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IBusinessImpl implements IBusiness {
    public HashMap<Long, Article> cart;

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderArticleRepository orderArticleRepository;

    public IBusinessImpl() { this.cart = new HashMap<Long, Article>(); }

    //          USERS
    /**
     * Vérifie si un utilisateur est connecter
     * @return true ou false si aucun utilisateur n'est connecter
     */
    public boolean isUserAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken);
    }

    //          CART
    /**
     * Retourne le panier
     * @return HashMap qui est le panier
     */
    public HashMap<Long, Article> displayCart() { return this.cart; }

    /**
     * Ajout d'un article au panier
     * @param articleId Article à ajouter
     */
    public void addToCart(Long articleId) {
        Article articleToAdd = cart.get(articleId);
        if (articleToAdd != null) {
            articleToAdd.setQuantity(articleToAdd.getQuantity() + 1);
        } else {
            Optional<Article> optionalToAdd = articleRepository.findById(articleId);
            if (optionalToAdd.isPresent()) {
                Article article = optionalToAdd.get();
                cart.put(articleId, article);
            }
        }
    }

    /**
     * Retire un article du panier
     * @param articleId id de l'article à retirer
     */
    public void removeToCart(Long articleId) {
        Article articleToRemove = cart.get(articleId);
        if(articleToRemove.getQuantity() > 1) {
            articleToRemove.setQuantity(articleToRemove.getQuantity() - 1);
        }else {
            cart.remove(articleId);
        }
    }

    /**
     * Récupère le total du panier
     * @return double total du panier
     */
    public double getTotal() {
        final double[] total = {0};
        cart.values().forEach((a) -> total[0] += a.getPrice()* a.getQuantity());
        return total[0];
    }

    //          ARTICLES
    /**
     * Récupère tous les articles
     * @return Liste contenant tous les articles
     */
    public List<Article> findAllArticle() { return articleRepository.findAll(); }

    /**
     * Récupère un article par son identifiant
     * @param id Identifiant de l'article à récupérer
     * @return Optional -> Article sinon Null
     */
    public Optional<Article> findArticleById(Long id) { return articleRepository.findById(id); }

    /**
     * Récupère une page d'article
     * @param keyword Le mot clé rechercher
     * @param pageable l'objet Pageable(numéro de page, taille de page)
     * @return Page d'article
     */
    public Page<Article> findArticleByDescriptionContains(String keyword, Pageable pageable) { return articleRepository.findByDescriptionContains(keyword, pageable); }

    /**
     * Récupère une page d'article appartenant à une catégorie
     * @param categoryId l'id de la catégorie
     * @param pageable l'objet Pageable(numéro de page, taille de page)
     * @return Page d'article
     */
    public Page<Article> findArticlesByCategoryId(Long categoryId, Pageable pageable) { return articleRepository.findByCategoryId(categoryId, pageable); }

    /**
     * Création d'un article dans la base de donnée
     * @param newArticle Article à créer
     */
    public void createArticle(Article newArticle) throws ArticleException {
        List<Article> articleList = articleRepository.findAll();
        for (Article article : articleList) {
            if (newArticle.getDescription().equals(article.getDescription()) && newArticle.getBrand().equals(article.getBrand())) {
                throw new ArticleException("Impossible de créer deux fois le même article");
            }
        }
        articleRepository.save(newArticle);
    }

    /**
     * Mise à jour d'un article
     * @param article à mettre à jour
     */
    public void updateArticle(Article article) {
        Optional<Article> optionalArticle = articleRepository.findById(article.getId());
        if(optionalArticle.isPresent()) {
          Article existingArticle = optionalArticle.get();
          existingArticle.setBrand(article.getBrand());
          existingArticle.setDescription(article.getDescription());
          existingArticle.setPrice(article.getPrice());
          existingArticle.setCategory(article.getCategory());
          articleRepository.save(existingArticle);
        }
    }

    /**
     * Supprime un article par son id
     * @param id id de l'article à supprimer
     */
    public void deleteArticleById(Long id) { articleRepository.deleteById(id); }

    //          CATEGORIES
    /**
     * Récupère une catégorie par son identifiant
     * @param id Identifiant de la catégorie à récupérer
     * @return Optional -> Categorie sinon Null
     */
    public Optional<Category> findCategoryById(Long id) { return categoryRepository.findById(id); }

    /**
     * Récupère toute les catégories
     * @return List des catégories
     */
    public List<Category> findAllCategories() { return categoryRepository.findAll(); }

    //          CUSTOMERS
    /**
     * Création d'un customer dans la base de donnée
     * @param customer customer à sauvegarder
     */
    public void createCustomer( Customer customer) { customerRepository.save(customer); }

    /**
     * Récupère un customer par son identifiant
     * @param customerId Identifiant du customer à récupérer
     * @return Optional -> Customer sinon Null
     */
    public Optional<Customer> findCustomerById(Long customerId ) { return customerRepository.findById(customerId); }

    //          ORDERS
    /**
     * Création d'une order dans la base de donnée
     * @param order order à sauvegarder
     */
    public void createOrder ( Commande order) { orderRepository.save(order); }

    /**
     * Création d'un orderItem dans la base de donnée
     * @param orderItem orderItem à sauvegarder
     */
    public void createOrderArticle(OrderArticle orderItem) { orderArticleRepository.save(orderItem); }

    /**
     * Création des orderArticles via les Articles contenue dans le panier
     * @param cart Panier
     */
    public void createOrderArticleFromCart(HashMap<Long, Article> cart, Commande commande) {
        for(HashMap.Entry<Long, Article> article : cart.entrySet()) {
            OrderArticle orderItem = new OrderArticle(article.getKey(), article.getValue().getQuantity(), article.getValue().getPrice(), commande);
            createOrderArticle(orderItem);
            orderArticleRepository.save(orderItem);
        }
    }
}
