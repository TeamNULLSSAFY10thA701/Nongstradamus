package com.a701.nongstradamus.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeTitleDto {

    private String title;

    private String image;

    private Long idx;

}
