package com.resources.dto.dtoMapper;

import com.resources.dto.CategoryDto;
import com.resources.entity.*;
import java.util.*;

/**
 *
 * @author szela
 */
public class CategoryDtoMapper {

    private CategoryDtoMapper() {
    }

    public static CategoryDto mapToCategoryDto(Category category, List<Product> productList) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryName(category.getCategoryName());
        categoryDto.setProduct(getProductListForEachCategory(category, productList));
        return categoryDto;
    }

    private static List<Product> getProductListForEachCategory(Category category, List<Product> productList) {
        List<Product> listProductForEachCategory = new LinkedList<>();
        for (Product product : productList) {
            if (product.getCategory().getCategoryId() == category.getCategoryId()) {
                listProductForEachCategory.add(product);
            }
        }
        return listProductForEachCategory;
    }
}
