package by.bysend.contractor.dto.page;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class PageDTO<T> {
    private List<T> content;
    private int page;
    private int size;
    private int totalElements;
    private int totalPages;
}
