package fr.ldnr;

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

	
	public static void main(String[] args) {
		SpringApplication.run(SpringStockMvcSecApplication.class, args);
	}
	
	@Override
	public void run(String...args) throws Exception {
		articleRepository.save(new Article("Samsung", "S10", 599));
		articleRepository.save(new Article("Apple", "15Pro", 1099));
		articleRepository.save(new Article("Apple", "XS", 859));
/*		articleRepository.save(new Article("Iphone XS", 350));
		articleRepository.save(new Article("Iphone 15Pro", 900));
		articleRepository.save(new Article("Xiaomi", 80));
		articleRepository.save(new Article("LG", 250));index

		articleRepository.save(new Article("Alcatel", 800));
		articleRepository.save(new Article("Xiaomi", 80));*/
	}
}