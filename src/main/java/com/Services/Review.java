package com.Services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private  long reviewID;
    private long bookID;
    private double ratings;
    private String comments;

}
