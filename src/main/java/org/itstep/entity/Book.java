package org.itstep.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false, unique = true)
    private String name;
    @Column(length = 100, nullable = false)
    private String author;
    @Column(nullable = false, name = "release_year")
    private int releaseYear;
    @Column(length = 100, nullable = false)
    private String genre;
    @Column(nullable = false, name = "amount_of_page")
    private int amountOfPage;
    @Column(columnDefinition = "text", nullable = false)
    private String description;

    public Book() {
    }

    public Book(String name, String author, int releaseYear, String genre, int amountOfPage, String description) {
        this.name = name;
        this.author = author;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.amountOfPage = amountOfPage;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAmountOfPage() {
        return amountOfPage;
    }

    public void setAmountOfPage(int amountOfPage) {
        this.amountOfPage = amountOfPage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", releaseYear=" + releaseYear +
                ", genre='" + genre + '\'' +
                ", amountOfPage=" + amountOfPage +
                ", description='" + description + '\'' +
                '}';
    }
}
