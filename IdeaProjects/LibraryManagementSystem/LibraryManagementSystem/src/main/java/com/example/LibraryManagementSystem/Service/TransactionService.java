package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.DTOs.IssueBookRequestDto;
import com.example.LibraryManagementSystem.Enums.CardStatus;
import com.example.LibraryManagementSystem.Enums.TransactionStatus;
import com.example.LibraryManagementSystem.Models.Book;
import com.example.LibraryManagementSystem.Models.Card;
import com.example.LibraryManagementSystem.Models.Transactions;
import com.example.LibraryManagementSystem.Repositories.AuthorRepository;
import com.example.LibraryManagementSystem.Repositories.BookRepository;
import com.example.LibraryManagementSystem.Repositories.CardRepository;
import com.example.LibraryManagementSystem.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;

    public String issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception{
        int bookId = issueBookRequestDto.getBookId();
        int cardId = issueBookRequestDto.getCardId();


        Book book = bookRepository.findById(bookId).get();
        Card card = cardRepository.findById(cardId).get();

        Transactions transaction = new Transactions();

        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setIssueOperation(true);
        transaction.setTransactionStatus(TransactionStatus.PENDING);


        if(book == null || book.isIssued() == true){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Book is not available");
        }
        if(card == null || card.getCardStatus() != CardStatus.ACTIVATED){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Card is not Active");
        }

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        book.setIssued(true);
        List<Transactions> listOfTransactions = book.getListOfTransactions();
        listOfTransactions.add(transaction);
        book.setListOfTransactions(listOfTransactions);

        List<Book> issuedBooksForCard = card.getBooksIssued();
        issuedBooksForCard.add(book);
        card.setBooksIssued(issuedBooksForCard);

        for(Book b : issuedBooksForCard)
            System.out.println(b.getName());

        List<Transactions> transactionsListForCard = card.getTransactionsList();
        transactionsListForCard.add(transaction);
        card.setTransactionsList(transactionsListForCard);

        cardRepository.save(card);

        return "Book Issued successfully";
    }

    public String getTransactionEntry(Integer bookId, Integer cardId) {
        List<Transactions> transactionsList = transactionRepository.getTransactionsForBookAndCard(bookId, cardId);

        String txnId = transactionsList.get(0).getTransactionId();

        return txnId;
    }
}
