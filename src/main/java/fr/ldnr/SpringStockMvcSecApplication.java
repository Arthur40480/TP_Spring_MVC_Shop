package fr.ldnr;

import fr.ldnr.dao.CategoryRepository;
import fr.ldnr.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.ldnr.dao.ArticleRepository;
import fr.ldnr.entities.Article;

@SpringBootApplication
public class SpringStockMvcSecApplication implements CommandLineRunner {
	
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
		Category tv = categoryRepository.save(new Category("TV" , "tv ecran plat"));
		Category pc = categoryRepository.save(new Category("PC" , "laptop ecran oled"));

		articleRepository.save(new Article("Samsung", "S10", 599 , phone));
		articleRepository.save(new Article("Apple", "15Pro", 1099 , tv));
		articleRepository.save(new Article("Apple", "XS", 859 , pc));
		articleRepository.save(new Article("Samsung", "S12Xr", 799 , phone));
		articleRepository.save(new Article("Apple", "12", 950 , phone));
		articleRepository.save(new Article("Apple", "XSSS", 859 , pc));

	}
}