package fr.ldnr.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.ldnr.entities.Article;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Page<Article> findByDescriptionContains(String description, Pageable pageable);
    Page<Article> findByCategoryId(Long id, Pageable pageable);
}
