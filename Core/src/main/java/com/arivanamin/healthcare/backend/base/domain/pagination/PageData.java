package com.arivanamin.healthcare.backend.base.domain.pagination;

import lombok.Value;

@Value
public class PageData {
    
    int sizeOfEachPage;
    long totalElements;
    int totalPages;
    int currentPage;
    
    public static PageData of (int pageSize, long totalElements, int totalPages, int pageNumber) {
        return new PageData(pageSize, totalElements, totalPages, pageNumber);
    }
}
