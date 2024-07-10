package com.example.literalura.service;

import com.example.literalura.entity.Author;
import com.example.literalura.entity.Book;
import com.example.literalura.repository.AuthorRepository;
import com.example.literalura.repository.BookRepository;
import com.example.literalura.client.BookApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookApiClient bookApiClient;

    public void searchAndRegisterBook(String title) {
        Optional<Book> bookOptional = bookApiClient.searchBook(title);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            if (!bookExists(book.getTitle())) {
                bookRepository.save(book);
            }
        } else {
            System.out.println("El libro no fue encontrado.");
        }
    }

    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    public List<Author> listAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Author> listAuthorsAliveInYear(Integer year) {
        return authorRepository.findByBirthYearLessThanEqualAndDeathYearGreaterThanEqual(year, year);
    }

    public List<Book> listBooksByLanguage(String language) {
        return bookRepository.findByLanguage(language);
    }

    public boolean bookExists(String title) {
        return !bookRepository.findByTitleContaining(title).isEmpty();
    }
}
