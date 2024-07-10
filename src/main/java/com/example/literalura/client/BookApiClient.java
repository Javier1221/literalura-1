package com.example.literalura.client;

import com.example.literalura.entity.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Optional;

@Service
public class BookApiClient {
    private final RestTemplate restTemplate = new RestTemplate();

    public Optional<Book> searchBook(String title) {
        // Aquí se haría la llamada a la API externa para buscar el libro
        // Por simplicidad, se omite la implementación específica
        return Optional.empty();
    }
}
