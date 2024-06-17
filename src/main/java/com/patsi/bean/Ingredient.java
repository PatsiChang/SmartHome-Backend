package com.patsi.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonDeserialize(as = Ingredient.class)
@JsonSerialize(as = Ingredient.class)
//@Jacksonized
public class Ingredient {
    private String ingredientName;
    private String ingredientAmount;
}
