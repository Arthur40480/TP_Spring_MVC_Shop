package fr.ldnr.business;

import fr.ldnr.entities.*;
import fr.ldnr.exceptions.ArticleException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IBusiness {

    // ARTICLES
    public List<Article> findAllArticle();
    public Optional<Article> findArticleById(Long id);
    public Page<Article> findArticleByDescriptionContains(String keyword, Pageable pageable);
    public Page<Article> findArticlesByCategoryId(Long categoryId, Pageable pageable);
    public void createArticle(Article article) throws ArticleException;
    public void updateArticle(Article article);
    public void deleteArticleById(Long id);

    // CATEGORIES
    public Optional<Category> findCategoryById(Long id);
    public List<Category> findAllCategories();

    //CART
    public void addToCart(Long articleId);
    public void removeToCart(Long articleId);
    public double getTotal();
    public int getQuantityInCart(Long articleId);

    //USER
    public boolean isUserAuthenticated();

    //CUSTOMER
    public void createCustomer(Customer customer);
    public Optional<Customer> findCustomerById(Long customerId);

    //ORDER
    public void createOrder(Commande order);

    //ORDER_ITEM
    public void createOrderArticle(OrderArticle orderItem);
}
