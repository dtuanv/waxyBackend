package com.waxy.database.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductDto {
    private int id;
    private String name;
    private String decription;
    private String price;
    private String imageUrl;
    private String category;
    private Set<SubFoodDto> subFoodDtos = new LinkedHashSet<>();
}
