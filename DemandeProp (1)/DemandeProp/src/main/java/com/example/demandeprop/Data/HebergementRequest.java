package com.example.demandeprop.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HebergementRequest {
    private Hebergement hebergement;
    private List<Image> images;

}
