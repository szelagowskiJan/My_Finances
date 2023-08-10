package com.resources.dto;

import com.resources.entity.Category;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Praca
 */
@Getter
@Setter
@RequiredArgsConstructor
public class ProductDto {
    
    @NotEmpty
    private int productId;
    @NotEmpty
    private String productName;
    @NotEmpty
    private Category Category;
}
