package be.pxl.api.data;

import be.pxl.domain.BookCategory;

import java.util.List;

public record BookDto(String title, BookCategory category, List<String> authors) {
}
