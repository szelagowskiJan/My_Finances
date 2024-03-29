package com.resources.dto;

import com.resources.entity.Product;
import java.util.List;
import lombok.*;

/**
 *
 * @author Praca
 */
@Getter
@Setter
public class CategoryDto {
    private int categoryId;

    private String categoryName;

    private List<Product> product;
}
