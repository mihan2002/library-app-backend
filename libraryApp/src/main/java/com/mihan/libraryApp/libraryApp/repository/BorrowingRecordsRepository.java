package com.mihan.libraryApp.libraryApp.repository;

import com.mihan.libraryApp.libraryApp.model.BorrowingRecords;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowingRecordsRepository extends JpaRepository<BorrowingRecords,Long> {

  Optional<BorrowingRecords> findById(Long id);

  @Transactional
  void deleteById(Long userId);
}
