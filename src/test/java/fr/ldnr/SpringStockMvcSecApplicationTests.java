package fr.ldnr;

import fr.ldnr.business.IBusinessImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class SpringStockMvcSecApplicationTests {

	@Autowired
	IBusinessImpl buisness;

	private static Instant startedAt;

	@Test
	void contextLoads()
	{
		assertFalse(1==2);
	}

	@Timeout(1)
	@Test
	public void orderShouldWork()
	{
		buisness.displayCart();
	}

	@BeforeEach
	public void beforeEachTest()
	{
		System.out.println("avant chaque test");
	}

	@AfterEach
	public void afterEachTest()
	{
		System.out.println("apres chaque test");
	}

	@BeforeAll
	public static void initStartingTime()
	{
		System.out.println("appel avant tout test");
		startedAt = Instant.now();
	}

	@AfterAll
	public static void showTestDuration()
	{
		System.out.println("appel apres tout les test");
		final Instant endedAt = Instant.now();
		final long duration = Duration.between(startedAt , endedAt).toMillis();
		System.out.println(MessageFormat.format("duree des test : {0} ms" , duration));
	}

	@ParameterizedTest(name = "{0} x 0 doit etre egal a 0")
	@ValueSource(ints = {1 , 2 , 42 , 1011 , 5089})
	public void multiply_shouldReturnZero(int arg)
	{
		assertEquals(0 , arg*0);
	}

	@Test
	public void testTotalAmontCart() {

		buisness.addToCart(64L);

		Assertions.assertEquals(buisness.getTotal() , 250);
	}
}
