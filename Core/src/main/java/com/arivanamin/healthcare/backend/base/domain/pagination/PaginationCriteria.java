package com.arivanamin.healthcare.backend.base.domain.pagination;

import lombok.Value;

@Value
public class PaginationCriteria {
    
    int page;
    int size;
    
    public static PaginationCriteria of (int page, int size) {
        return new PaginationCriteria(page, size);
    }
}
