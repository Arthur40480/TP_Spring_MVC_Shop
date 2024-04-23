package fr.ldnr.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class ArticleException extends Exception {

    public ArticleException(String message) {
        super(message);
    }
}
