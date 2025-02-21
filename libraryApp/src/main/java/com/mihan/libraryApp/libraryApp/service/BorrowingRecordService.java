package com.mihan.libraryApp.libraryApp.service;

import com.mihan.libraryApp.libraryApp.repository.BorrowingRecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowingRecordService {

    private final BorrowingRecordsRepository borrowingRecordsRepository;

    @Autowired
    public BorrowingRecordService(BorrowingRecordsRepository borrowingRecordsRepository) {
        this.borrowingRecordsRepository = borrowingRecordsRepository;
    }
}
