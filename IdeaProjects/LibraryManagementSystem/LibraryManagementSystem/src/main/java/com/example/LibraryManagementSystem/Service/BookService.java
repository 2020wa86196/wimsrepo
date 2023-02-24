package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.DTOs.BookRequestDto;
import com.example.LibraryManagementSystem.Models.Author;
import com.example.LibraryManagementSystem.Models.Book;
import com.example.LibraryManagementSystem.Repositories.AuthorRepository;
import com.example.LibraryManagementSystem.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;
    public String addBook(BookRequestDto bookRequestDto) {

        int authorId = bookRequestDto.getAuthorId();

        Author author = authorRepository.findById(authorId).get();

        Book book = new Book();

        book.setGenre(bookRequestDto.getGenre());
        book.setPages(bookRequestDto.getPages());
        book.setIssued(false);
        book.setName(bookRequestDto.getName());

        book.setAuthor(author);

        List<Book> currentBooksWritten = author.getBooksWritten();

        currentBooksWritten.add(book);
        author.setBooksWritten(currentBooksWritten);
        authorRepository.save(author);

        return "Book added successfully";

    }
}
