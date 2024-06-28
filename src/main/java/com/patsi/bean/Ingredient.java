package com.patsi.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Ingredient implements Serializable {
    @NotBlank
    @Size(min = 1, max = 50)
    private String ingredientName;
    @NotBlank
    @Size(max = 50)
    private String ingredientAmount;
}
