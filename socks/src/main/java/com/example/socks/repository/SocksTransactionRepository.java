package com.example.socks.repository;

import com.example.socks.model.entity.SocksTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SocksTransactionRepository extends JpaRepository<SocksTransaction, Integer> {
    List<SocksTransaction> findAllByTransactionDateIsBetweenOrderById(LocalDateTime start, LocalDateTime end);
}
