package fr.ldnr.dao;

import fr.ldnr.entities.Article;
import fr.ldnr.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category , Long> {
    @Query ("select a from Article a where a.category.id = :categoryId")
    Page<Article> findArticlesByCategoryId(@Param("categoryId") Long categoryId , Pageable pageable);
}
