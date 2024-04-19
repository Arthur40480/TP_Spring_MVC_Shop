package fr.ldnr.dao;

import fr.ldnr.entities.OrderArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderArticleRepository extends JpaRepository<OrderArticle, Long> {
}
