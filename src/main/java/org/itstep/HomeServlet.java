package org.itstep;

import jakarta.persistence.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.itstep.entity.Book;
import org.itstep.service.BookService;

import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/")
public class HomeServlet extends HttpServlet {
    protected BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        List<Book> books = bookService.getAllBooks();
        if (books != null) {
            books.stream().forEach(System.out::println);
            session.setAttribute("books", books);
        }

        req.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // addBook
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        String releaseYear = req.getParameter("year");
        String genre = req.getParameter("genre");
        String amountOfPage = req.getParameter("amount");
        String description = req.getParameter("description");

        if (name != null && !name.isBlank() &&
                author != null && !author.isBlank() &&
                releaseYear != null && !releaseYear.isBlank() &&
                genre != null && !genre.isBlank() &&
                amountOfPage != null && !amountOfPage.isBlank() &&
                description != null && !description.isBlank()) {
//            bookService.addAuthor(author);
//            bookService.addGenre(genre);
            bookService.addBook(name, author, releaseYear, genre, amountOfPage, description);
        }

        // removeBook
        String id = req.getParameter("id");
        if (id != null && !id.isBlank() ){
            bookService.remove(id);
        }

        // editBook
        HttpSession session = req.getSession();
        String editIdTmp = req.getParameter("editId");
        if (editIdTmp != null){
            System.out.println("editIdTmp = " + editIdTmp);
            session.setAttribute("editId", editIdTmp);
        }

        String editTmp = req.getParameter("edit");
        String editDataTmp = req.getParameter("editdata");
        if (editTmp != null && !editTmp.isBlank() &&
                editDataTmp != null && !editDataTmp.isBlank()){
            System.out.println("editTmp = " + editTmp);
            System.out.println("editDataTmp = " + editDataTmp);
            session.setAttribute("edit", editTmp);
            session.setAttribute("editData", editDataTmp);
        }

        if (session.getAttribute("edit") != null &&
                session.getAttribute("editData") != null &&
                session.getAttribute("editId") != null){
            String editType = (String) session.getAttribute("edit");
            String editNewData = (String) session.getAttribute("editData");
            String editId = (String) session.getAttribute("editId");
            session.removeAttribute("edit");
            session.removeAttribute("editData");
            session.removeAttribute("editId");
            bookService.updateBook(editType, editNewData, editId);
        }

        // findBook
        String findType = req.getParameter("find");
        String findData = req.getParameter("finddata");

        if (findType != null && !findType.isBlank() &&
                findData != null && !findData.isBlank()){
            System.out.println("findType = " + findType);
            System.out.println("findData = " + findData);
            Book bookFind = bookService.findBy(findType, findData);
            session.setAttribute("bookFind", bookFind);
        }

        resp.sendRedirect(req.getServletContext().getContextPath() + "/");
    }

}
