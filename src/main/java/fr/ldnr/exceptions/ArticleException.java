package fr.ldnr.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;
import fr.ldnr.entities.Article;

@Service
@NoArgsConstructor
public class ArticleException extends Exception {

    public ArticleException(String message) {
        super(message);
    }
}
