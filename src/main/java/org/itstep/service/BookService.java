package org.itstep.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.itstep.entity.Book;


import java.util.List;

public class BookService {

    protected EntityManager entityManager;

    public BookService() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("books");
        entityManager = entityManagerFactory.createEntityManager();
    }

    //    public Genre findGenreById(int id) {
//        return entityManager.find(Genre.class, id);
//    }
//
//    public Genre findGenreByName(String name) {
//        return entityManager.find(Genre.class, name);
//    }
//
//    public Author findAuthorById(int id) {
//        return entityManager.find(Author.class, id);
//    }
//
//    public Author findAuthorByName(String name) {
//        return entityManager.find(Author.class, name);
//    }
    public Book findBookById(int id) {
        return entityManager.find(Book.class, id);
    }

    public List<Book> getAllBooks() {
        TypedQuery<Book> query = entityManager.createQuery("select с from Book с", Book.class);
        List<Book> books = query.getResultList();
        if (books != null) {
            books.forEach(System.out::println);
        }
        return books;
    }

//    public List<Author> getAllAuthors() {
//        TypedQuery<Author> query = entityManager.createQuery("select с from Author с", Author.class);
//        List<Author> authors = query.getResultList();
//        if (authors != null) {
//            authors.forEach(System.out::println);
//        }
//        return authors;
//    }
//
//    public List<Genre> getAllGenres() {
//        TypedQuery<Genre> query = entityManager.createQuery("select с from Genre с", Genre.class);
//        List<Genre> genres = query.getResultList();
//        if (genres != null) {
//            genres.forEach(System.out::println);
//        }
//        return genres;
//    }
//
//    public void addAuthor(String authorName) {
//        Author author = new Author(authorName);
//        entityManager.persist(author);
//    }
//
//    public void addGenre(String genreName) {
//        Genre genre = new Genre(genreName);
//        entityManager.persist(genre);
//    }

    public void addBook(String name, String authorDb, String releaseYearDb, String genreDb, String amountOfPageDb, String description) {
        int releaseYear = Integer.parseInt(releaseYearDb);
        int amountOfPage = Integer.parseInt(amountOfPageDb);

        try {
            Book book = new Book(name, authorDb, releaseYear, genreDb, amountOfPage, description);
            if (book != null) {
                System.out.println(" addBook " + book.toString());
            } else {
                System.out.println(" addBook - null ");
            }
            entityManager.getTransaction().begin();
            entityManager.persist(book);
            entityManager.getTransaction().commit();
        } catch (Throwable ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(String idDb) {
        int id = Integer.parseInt(idDb);
        try {
            Book book = findBookById(id);
            if (book != null) {
                System.out.println(" removeBook " + book.toString());
            } else {
                System.out.println(" addBook - null ");
            }
            entityManager.getTransaction().begin();
            entityManager.remove(book);
            entityManager.getTransaction().commit();
        } catch (Throwable ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }


    public void updateBook(String editType, String editNewData, String editId) {
        int id = Integer.parseInt(editId);
        Book book = findBookById(id);
        switch (editType) {
            case "name" -> book.setName(editNewData);
            case "author" -> book.setAuthor(editNewData);
            case "year" -> {
                if (isDigit(editNewData)) {
                    int releaseYear = Integer.parseInt(editNewData);
                    book.setReleaseYear(releaseYear);
                }
            }
            case "genre" -> book.setGenre(editNewData);

            case "amount" -> {
                if (isDigit(editNewData)) {
                    int amountOfPage = Integer.parseInt(editNewData);
                    book.setAmountOfPage(amountOfPage);
                }
            }
            case "description" -> book.setDescription(editNewData);
            default -> System.out.println("unknown type");
        }

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(book);		// внесли изменения в таблицу БД
            entityManager.getTransaction().commit();
        } catch (Throwable ex){
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }

    }

    private boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Book findBy(String findType, String data) {
        List <Book> books = getAllBooks();
        int id = 0;
        for (Book b:books){
            switch (findType) {
                case "name" -> {
                    if (b.getName().equals(data)){
                        id = b.getId();
                        break;
                    }
                }
                case "author" -> {
                    if (b.getAuthor().equals(data)){
                        id = b.getId();
                        break;
                    }
                }
                case "year" -> {
                    if (isDigit(data)) {
                        int releaseYear = Integer.parseInt(data);
                        if (b.getReleaseYear() == releaseYear){
                            id = b.getId();
                            break;
                        }
                    }
                }
                case "genre" -> {
                    if (b.getGenre().equals(data)){
                        id = b.getId();
                        break;
                    }
                }
                case "amount" -> {
                    if (isDigit(data)) {
                        int amountOfPage = Integer.parseInt(data);
                        if (b.getAmountOfPage() == amountOfPage){
                            id = b.getId();
                            break;
                        }
                    }
                }
                case "description" -> {
                    if (b.getDescription().equals(data)){
                        id = b.getId();
                        break;
                    }
                }
                default -> System.out.println("unknown type");
            }
        }
        return findBookById(id);
    }

}
