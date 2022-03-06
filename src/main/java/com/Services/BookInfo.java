package com.Services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookInfo {
    private long bookID;
    private String title;
    private String author;
    private String ISBN;

}
