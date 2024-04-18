package fr.ldnr.dao;

import fr.ldnr.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Commande, Long > {
}
