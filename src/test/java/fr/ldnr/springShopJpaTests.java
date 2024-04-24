package fr.ldnr;

import fr.ldnr.dao.ArticleRepository;
import fr.ldnr.dao.CategoryRepository;
import fr.ldnr.entities.Article;
import fr.ldnr.entities.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
    public void test_add_article() {
        Category anonymous = categoryRepository.save(new Category("Smartphone" , "telephono dernier cris"));
        articleRepository.save(new Article("zigzag" , "s9" , 250 , anonymous));

        Article article = articleRepository.findByBrandContains("s9").get(0);
        assertEquals("zigzag" , article.getDescription());
    }

    @Test
    public void test_delete_article() {
        Article article = new Article("Samsungg", "one7", 500 , null);
        articleRepository.save(article);
        articleRepository.deleteById(article.getId());
        assertFalse(articleRepository.findById(article.getId()).isPresent());
    }

}

