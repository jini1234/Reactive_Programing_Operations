package com.Services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class BookINfoService {
    public Flux<BookInfo> getBooks(){
    var books= List.of(new BookInfo(1,"b1","A1","1"));
    return Flux.fromIterable(books);

    }



}
