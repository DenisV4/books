package com.example.books.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class BookUpsertRequest {

    private String title;

    private String author;

    private String categoryName;
}
