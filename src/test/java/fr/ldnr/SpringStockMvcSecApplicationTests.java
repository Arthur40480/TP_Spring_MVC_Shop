package fr.ldnr;

import fr.ldnr.business.IBusinessImpl;
import fr.ldnr.entities.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class SpringStockMvcSecApplicationTests {

	@Autowired
	IBusinessImpl business;

	@Test
	void contextLoads() {
		assertFalse(1==2);
	}

	@Test
	void testTotalAmountCart() {

		business.addToCart(new Article((long)234, "Samsung", "Samsung S9", (double) 350, 1, null).getId());

		assertEquals(business.getTotal(), 350);
	}
}
