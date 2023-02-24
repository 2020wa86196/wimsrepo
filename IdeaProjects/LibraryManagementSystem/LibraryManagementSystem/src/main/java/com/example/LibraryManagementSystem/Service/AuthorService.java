package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.DTOs.AuthorEntryDto;
import com.example.LibraryManagementSystem.Models.Author;
import com.example.LibraryManagementSystem.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    public String createAuthor(AuthorEntryDto authorEntryDto) {

        Author author = new Author();

        author.setName(authorEntryDto.getName());
        author.setAge(authorEntryDto.getAge());
        author.setCountry(authorEntryDto.getCountry());
        author.setRating(authorEntryDto.getRating());

        authorRepository.save(author);
        return "Author added successfully";

    }


}
