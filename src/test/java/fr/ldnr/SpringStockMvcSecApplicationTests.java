package fr.ldnr;

import fr.ldnr.business.IBusinessImpl;

import fr.ldnr.entities.Article;
import fr.ldnr.exceptions.ArticleException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class SpringStockMvcSecApplicationTests {

	@Autowired
	IBusinessImpl buisness;

	private static Instant startedAt;

	@Test
	void contextLoads() {
		assertFalse(1==2);
	}

	@BeforeEach
	public void clear_cart() {
		System.out.println("Je vide le panier");
		buisness.cart.clear();
	}

	@Test
	public void testTotalAmontCart() throws ArticleException {
		Article article1 = new Article("Motorollla", "one8", 500 , null);
		Article article2 = new Article("Nokiia", "one8", 500 , null);

		buisness.createArticle(article1);
		buisness.createArticle(article2);
		buisness.addToCart(article1.getId());
		buisness.addToCart(article2.getId());

		Assertions.assertEquals(buisness.getTotal() , 1000);
	}

	@Test
	public void testAddArticleToCart() throws ArticleException {
		Article article3 = new Article("SonyEricsson", "one8", 500 , null);

		buisness.createArticle(article3);
		buisness.addToCart(article3.getId());

		assertTrue(buisness.displayCart().containsKey(article3.getId()));
	}

	@Test
	public void testModifyQuantityArticle() throws ArticleException {
		Article article4 = new Article("Nokia", "one7", 500 , null);

		buisness.createArticle(article4);
		buisness.addToCart(article4.getId());
		buisness.addToCart(article4.getId());
		buisness.removeToCart(article4.getId());
		buisness.addToCart(article4.getId());

		assertEquals(buisness.getQuantityInCart(article4.getId()), 2);
	}

	@Test
	public void testRemoveArticleToCart() throws ArticleException {
		Article article5 = new Article("Motorolla", "one7", 500 , null);

		buisness.createArticle(article5);
		buisness.addToCart(article5.getId());
		buisness.removeToCart(article5.getId());

		assertFalse(buisness.displayCart().containsKey(article5.getId()));
	}
}
