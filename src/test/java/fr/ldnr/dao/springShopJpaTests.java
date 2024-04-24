package fr.ldnr.dao;

import fr.ldnr.entities.Article;
import fr.ldnr.entities.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class springShopJpaTests {

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void test_delete_article() {
        Article article = new Article("Samsungg", "one7", 500 , null);
        articleRepository.save(article);
        articleRepository.deleteById(article.getId());
        assertFalse(articleRepository.findById(article.getId()).isPresent());
    }

    @Test
    public void test_find_by_description_contains() {
        Article article1 = new Article("Telephone samsung", "Samsung", 500 , null);
        Article article2 = new Article("Telephone apple", "Apple", 500 , null);

        articleRepository.save(article1);
        articleRepository.save(article2);

        String description = "Telephone";
        Pageable pageable = (Pageable) PageRequest.of(0,5);
        Page<Article> articlePage = articleRepository.findByDescriptionContains(description, pageable);

        assertEquals(2, articlePage.getTotalElements());
        assertEquals("Telephone samsung", articlePage.getContent().get(0).getDescription());
        assertEquals("Apple", articlePage.getContent().get(1).getBrand());
    }

    @Test
    public void test_find_article_by_category_id() {
        Category phoneCategory = categoryRepository.save(new Category("Smartphone", "telephono dernier cris"));
        categoryRepository.save(phoneCategory);

        Article article3 = new Article("Telephone motorola", "Motorola", 500, phoneCategory);
        Article article4 = new Article("Telephone nokia", "Nokia", 500, phoneCategory);
        articleRepository.save(article3);
        articleRepository.save(article4);

        Pageable pageable = (Pageable) PageRequest.of(0, 5);
        Page<Article> articlePage = articleRepository.findByCategoryId(phoneCategory.getId(), pageable);

        assertEquals(2, articlePage.getTotalElements());
        assertEquals("Smartphone", articlePage.getContent().get(0).getCategory().getName());
        assertEquals(phoneCategory.getId(), articlePage.getContent().get(1).getCategory().getId());
    }

}

