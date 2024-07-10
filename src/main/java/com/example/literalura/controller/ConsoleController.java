package com.example.literalura.controller;

import com.example.literalura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Scanner;

@Component
public class ConsoleController {
    @Autowired
    private BookService bookService;

    @PostConstruct
    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Elija una opción:");
            System.out.println("1. Buscar libro por título");
            System.out.println("2. Listar todos los libros");
            System.out.println("3. Listar todos los autores");
            System.out.println("4. Listar autores vivos en un año determinado");
            System.out.println("5. Listar libros por idioma");
            System.out.println("6. Salir");

            int option = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea

            switch (option) {
                case 1:
                    System.out.println("Ingrese el título del libro:");
                    String title = scanner.nextLine();
                    if (!bookService.bookExists(title)) {
                        bookService.searchAndRegisterBook(title);
                    } else {
                        System.out.println("El libro ya está registrado.");
                    }
                    break;
                case 2:
                    System.out.println("Libros registrados:");
                    bookService.listAllBooks().forEach(book -> {
                        System.out.println("ID: " + book.getId());
                        System.out.println("Título: " + book.getTitle());
                        System.out.println("Idioma: " + book.getLanguage());
                        System.out.println("Descargas: " + book.getDownloads());
                        System.out.println("Autor: " + book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName());
                        System.out.println("----");
                    });
                    break;
                case 3:
                    System.out.println("Autores registrados:");
                    bookService.listAllAuthors().forEach(author -> {
                        System.out.println("ID: " + author.getId());
                        System.out.println("Nombre: " + author.getFirstName() + " " + author.getLastName());
                        System.out.println("Vivo: " + (author.getIsAlive() ? "Sí" : "No"));
                        System.out.println("Año de nacimiento: " + author.getBirthYear());
                        System.out.println("Año de fallecimiento: " + author.getDeathYear());
                        System.out.println("----");
                    });
                    break;
                case 4:
                    System.out.println("Ingrese el año:");
                    int year = scanner.nextInt();
                    scanner.nextLine();  // Consumir el salto de línea
                    System.out.println("Autores vivos en el año " + year + ":");
                    bookService.listAuthorsAliveInYear(year).forEach(author -> {
                        System.out.println("ID: " + author.getId());
                        System.out.println("Nombre: " + author.getFirstName() + " " + author.getLastName());
                        System.out.println("Año de nacimiento: " + author.getBirthYear());
                        System.out.println("Año de fallecimiento: " + author.getDeathYear());
                        System.out.println("----");
                    });
                    break;
                case 5:
                    System.out.println("Ingrese el idioma (ES, EN, FR, PT):");
                    String language = scanner.nextLine();
                    System.out.println("Libros en idioma " + language + ":");
                    bookService.listBooksByLanguage(language).forEach(book -> {
                        System.out.println("ID: " + book.getId());
                        System.out.println("Título: " + book.getTitle());
                        System.out.println("Idioma: " + book.getLanguage());
                        System.out.println("Descargas: " + book.getDownloads());
                        System.out.println("Autor: " + book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName());
                        System.out.println("----");
                    });
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
