package com.example.socks.repository;

import com.example.socks.model.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    Optional<Warehouse> findBySocksId(Integer id);

    List<Warehouse> findBySocksIdIn(List<Integer> socksIds);
}
