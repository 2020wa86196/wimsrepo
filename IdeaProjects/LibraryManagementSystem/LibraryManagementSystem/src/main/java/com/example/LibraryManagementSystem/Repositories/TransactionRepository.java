package com.example.LibraryManagementSystem.Repositories;

import com.example.LibraryManagementSystem.Models.Transactions;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions, Integer> {

    @Query(value = "select * from transactions where book_id=:bookId and card_id=:cardId and is_issue_operation=true", nativeQuery = true)
    List<Transactions> getTransactionsForBookAndCard(int bookId, int cardId);
}
