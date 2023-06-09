package com.bikkadit.ecommerce.payload;

import lombok.*;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageableResponse <T>{

    private List<T> content;

    private Integer pageNumber;

    private Integer pageSize;

    private long totalElement;

    private Integer totalPages;

    private boolean lastPage;

}
