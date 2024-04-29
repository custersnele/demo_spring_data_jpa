package be.pxl.api.data;

import be.pxl.domain.BookCategory;

public record FilterDto(String authorName, BookCategory category, String titlePart) {
}
