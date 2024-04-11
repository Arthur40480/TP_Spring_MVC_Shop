package fr.ldnr.dao;

import fr.ldnr.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category , Long>
{

}
