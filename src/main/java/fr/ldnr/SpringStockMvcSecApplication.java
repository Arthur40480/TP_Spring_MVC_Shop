package fr.ldnr;

import fr.ldnr.dao.*;
import fr.ldnr.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.ldnr.dao.ArticleRepository;
import fr.ldnr.entities.Article;

@SpringBootApplication
public class SpringStockMvcSecApplication implements CommandLineRunner {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderArticleRepository orderItemRepository;

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringStockMvcSecApplication.class, args);
	}
	
	@Override
	public void run(String...args) throws Exception {
		createCategoryAndArticle();
	}

	public void createCategoryAndArticle() {
		Category phone = categoryRepository.save(new Category("Smartphone" , "telephono dernier cris"));
		Category tv = categoryRepository.save(new Category("TV" , "tv ecrans plat"));
		Category pc = categoryRepository.save(new Category("PC" , "laptop ecran oled"));
		Category tablet = categoryRepository.save(new Category("tablette" , "tablette 16"));
		Category imprimante = categoryRepository.save(new Category("imprimante" , "imprimante lazer"));
		Category camera = categoryRepository.save(new Category("camera" , "zoom optique x50"));
		Category hifi = categoryRepository.save(new Category("HI-FI" , "son , audio enceinte "));
		Category JV = categoryRepository.save(new Category("jeux-video" , "ps5/xbox/nintendo/pc"));

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
		articleRepository.save(new Article("Huawai", "one7", 499 , camera));
		articleRepository.save(new Article("Apple", "appTV", 150 , camera));
		articleRepository.save(new Article("Sony", "vayo", 959 , pc));
		articleRepository.save(new Article("Samsung", "S10", 599 , tablet));
		articleRepository.save(new Article("Sony", "bravia", 2099 , tablet));
		articleRepository.save(new Article("Acer", "predator", 3059 , pc));
		articleRepository.save(new Article("Sony", "xperia1", 1599 , tablet));
		articleRepository.save(new Article("Samsung", "uhdoled", 5099 , tv));
		articleRepository.save(new Article("Msi", "dominator", 2859 , pc));
		articleRepository.save(new Article("Tlou1", "ps5", 2099 , JV));
		articleRepository.save(new Article("Forza", "xbox", 3059 , JV));
		articleRepository.save(new Article("Zelda", "nintendo", 1599 , JV));
		articleRepository.save(new Article("call of", "all", 5099 , JV));
		articleRepository.save(new Article("the crew", "all", 2859 , imprimante));
		articleRepository.save(new Article("Samsung", "S10", 599 , imprimante));
		articleRepository.save(new Article("Apple", "15Pro", 1099 , imprimante));
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
		articleRepository.save(new Article("Sony", "vayo", 959 , hifi));
		articleRepository.save(new Article("Samsung", "S10", 599 , hifi));
		articleRepository.save(new Article("Sony", "bravia", 2099 , tv));
		articleRepository.save(new Article("Acer", "predator", 3059 , pc));
		articleRepository.save(new Article("Sony", "xperia1", 1599 , phone));
		articleRepository.save(new Article("Samsung", "uhdoled", 5099 , tv));
		articleRepository.save(new Article("Msi", "dominator", 2859 , pc));
	}
}