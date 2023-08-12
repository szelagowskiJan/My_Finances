package com.resources.dto;

import com.resources.entity.Category;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

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
