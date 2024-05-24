package org.example.demo1.rest.contracts;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class BookDto {
    private String title;

    private BigDecimal price;
}
