package fr.ldnr;

import fr.ldnr.dao.CategoryRepository;
import fr.ldnr.dao.OrderItemRepository;
import fr.ldnr.entities.Category;
import fr.ldnr.entities.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.ldnr.dao.ArticleRepository;
import fr.ldnr.entities.Article;

@SpringBootApplication
public class SpringStockMvcSecApplication implements CommandLineRunner {

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringStockMvcSecApplication.class, args);
	}
	
	@Override
	public void run(String...args) throws Exception {
		Category phone = categoryRepository.save(new Category("Smartphone" , "telephono dernier cris"));
		Category tv = categoryRepository.save(new Category("TV" , "tv ecrans plat"));
		Category pc = categoryRepository.save(new Category("PC" , "laptop ecran oled"));
		Category tablet = categoryRepository.save(new Category("tablette" , "tablette 16"));
		Category imprimante = categoryRepository.save(new Category("imprimante" , "imprimante lazer"));
		Category camera = categoryRepository.save(new Category("camera" , "zoom optique x50"));
		Category HIFI = categoryRepository.save(new Category("HI-FI" , "son , audio enceinte "));
		Category JV = categoryRepository.save(new Category("jeux-video" , "ps5/xbox/nintendo/pc"));

		Article samsung = articleRepository.save(new Article("Samsung", "S10", 599 , phone));
		OrderItem orderItem = orderItemRepository.save(new OrderItem(samsung.getId(), samsung.getQuantity(), 599));
		articleRepository.save(new Article("Apple", "15Pro", 1099 , tv));
		articleRepository.save(new Article("Apple", "XS", 859 , pc));

		articleRepository.save(new Article("Huawai", "one7", 499 , phone));
		articleRepository.save(new Article("Apple", "appTV", 150 , tv));
		articleRepository.save(new Article("Sony", "vayo", 959 , pc));
		articleRepository.save(new Article("Samsung", "S10", 599 , phone));
		articleRepository.save(new Article("Sony", "bravia", 2099 , tv));
		articleRepository.save(new Article("Acer", "predator", 3059 , pc));
		articleRepository.save(new Article("Sony", "xperia1", 1599 , phone));
		articleRepository.save(new Article("Samsung", "uhdoled", 5099 , tv));
		articleRepository.save(new Article("Msi", "dominator", 2859 , pc));
		articleRepository.save(new Article("Tlou1", "ps5", 2099 , JV));
		articleRepository.save(new Article("Forza", "xbox", 3059 , JV));
		articleRepository.save(new Article("Zelda", "nintendo", 1599 , JV));
		articleRepository.save(new Article("call of", "all", 5099 , JV));
		articleRepository.save(new Article("the crew", "all", 2859 , JV));
		articleRepository.save(new Article("Samsung", "S10", 599 , phone));
		articleRepository.save(new Article("Apple", "15Pro", 1099 , tv));
		articleRepository.save(new Article("Apple", "XS", 859 , pc));
		articleRepository.save(new Article("Huawai", "one7", 499 , phone));
		articleRepository.save(new Article("Apple", "appTV", 150 , tv));
		articleRepository.save(new Article("Sony", "vayo", 959 , pc));
		articleRepository.save(new Article("Samsung", "S10", 599 , phone));
		articleRepository.save(new Article("Sony", "bravia", 2099 , tv));
		articleRepository.save(new Article("Acer", "predator", 3059 , pc));
		articleRepository.save(new Article("Sony", "xperia1", 1599 , phone));
		articleRepository.save(new Article("Samsung", "uhdoled", 5099 , tv));
		articleRepository.save(new Article("Msi", "dominator", 2859 , pc));
		articleRepository.save(new Article("Tlou1", "ps5", 2099 , JV));
		articleRepository.save(new Article("Forza", "xbox", 3059 , JV));
		articleRepository.save(new Article("Zelda", "nintendo", 1599 , JV));
		articleRepository.save(new Article("call of", "all", 5099 , JV));
		articleRepository.save(new Article("the crew", "all", 2859 , JV));
		articleRepository.save(new Article("Samsung", "S10", 599 , phone));
		articleRepository.save(new Article("Apple", "15Pro", 1099 , tv));
		articleRepository.save(new Article("Apple", "XS", 859 , pc));
		articleRepository.save(new Article("Huawai", "one7", 499 , phone));
		articleRepository.save(new Article("Apple", "appTV", 150 , tv));
		articleRepository.save(new Article("Sony", "vayo", 959 , pc));
		articleRepository.save(new Article("Samsung", "S10", 599 , phone));
		articleRepository.save(new Article("Sony", "bravia", 2099 , tv));
		articleRepository.save(new Article("Acer", "predator", 3059 , pc));
		articleRepository.save(new Article("Sony", "xperia1", 1599 , phone));
		articleRepository.save(new Article("Samsung", "uhdoled", 5099 , tv));
		articleRepository.save(new Article("Msi", "dominator", 2859 , pc));
		articleRepository.save(new Article("Tlou1", "ps5", 2099 , JV));
		articleRepository.save(new Article("Forza", "xbox", 3059 , JV));
		articleRepository.save(new Article("Zelda", "nintendo", 1599 , JV));
		articleRepository.save(new Article("call of", "all", 5099 , JV));
		articleRepository.save(new Article("the crew", "all", 2859 , JV));
		articleRepository.save(new Article("Samsung", "S10", 599 , phone));
		articleRepository.save(new Article("Apple", "15Pro", 1099 , tv));
		articleRepository.save(new Article("Apple", "XS", 859 , pc));
		articleRepository.save(new Article("Huawai", "one7", 499 , phone));
		articleRepository.save(new Article("Apple", "appTV", 150 , tv));
		articleRepository.save(new Article("Sony", "vayo", 959 , pc));
		articleRepository.save(new Article("Samsung", "S10", 599 , phone));
		articleRepository.save(new Article("Sony", "bravia", 2099 , tv));
		articleRepository.save(new Article("Acer", "predator", 3059 , pc));
		articleRepository.save(new Article("Sony", "xperia1", 1599 , phone));
		articleRepository.save(new Article("Samsung", "uhdoled", 5099 , tv));
		articleRepository.save(new Article("Msi", "dominator", 2859 , pc));
		/*articleRepository.save(new Article("Iphone XS", 350));
		articleRepository.save(new Article("Iphone 15Pro", 900));
		articleRepository.save(new Article("Xiaomi", 80));
		articleRepository.save(new Article("LG", 250));index

		articleRepository.save(new Article("Alcatel", 800));
		articleRepository.save(new Article("Xiaomi", 80));*/

	}
}