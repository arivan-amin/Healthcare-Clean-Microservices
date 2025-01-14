package com.arivanamin.healthcare.backend.base.domain.pagination;

import lombok.Value;

import java.util.List;

@Value
public class PaginatedResponse<T> {
    
    PageData pageData;
    List<T> elements;
    
    public static <T> PaginatedResponse<T> of (PageData pageData, List<T> elements) {
        return new PaginatedResponse<>(pageData, elements);
    }
}
