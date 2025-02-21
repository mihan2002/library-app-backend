package com.mihan.libraryApp.libraryApp.dto;

public class ReturnBookDTO {

    private Long borrowingRecordID;

    public ReturnBookDTO() {
    }

    public ReturnBookDTO(Long borrowingRecordID) {
        this.borrowingRecordID = borrowingRecordID;
    }

    public Long getBorrowingRecordID() {
        return borrowingRecordID;
    }
}
