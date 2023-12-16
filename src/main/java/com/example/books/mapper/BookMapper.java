package com.example.books.mapper;

import com.example.books.model.Book;
import com.example.books.web.dto.BookResponse;
import com.example.books.web.dto.BookUpsertRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@DecoratedWith(BookMapperDelegate.class)
public interface BookMapper {

    Book requestToBook(BookUpsertRequest request);

    Book requestToBook(Long id, BookUpsertRequest request);

    @Mapping(target = "categoryName", expression = "java(book.getCategory().getName())")
    BookResponse bookToResponse(Book book);

    List<BookResponse> bookListToBookResponseList(List<Book> books);
}
