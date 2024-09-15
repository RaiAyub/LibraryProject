package com.library.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(max = 50, message = "Title must be at most 100 characters")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Author is required")
    @Size(max = 20, message = "Author must be at most 100 characters")
    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private int publicationYear;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column
    private boolean available;


//    public Book(String title, String author, int publicationYear, String isbn) {
//        this.title=title;
//        this.author=author;
//        this.publicationYear=publicationYear;
//        this.isbn=isbn;
//    }

    // Constructors, getters, and setters (omitted for brevity)

    // Additional methods, relationships, and annotations can be added as per requirements
}
