package com.example.LibraryManagementSystem.Controllers;

import com.example.LibraryManagementSystem.DTOs.IssueBookRequestDto;
import com.example.LibraryManagementSystem.Service.TransactionService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/issueBook")
    public String issueBook(@RequestBody IssueBookRequestDto issueBookRequestDto){
        try {
            return transactionService.issueBook(issueBookRequestDto);
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @GetMapping("getTxnInfo")
    public String getTransactionEntry(@RequestParam("bookId")Integer bookId, @RequestParam("cardId") Integer cardId){
        return transactionService.getTransactionEntry(bookId, cardId);
    }
}
