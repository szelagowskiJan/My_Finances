package com.resources.service.impl;

import com.resources.dto.CategoryDto;
import com.resources.entity.*;
import com.resources.repository.*;
import com.resources.service.CategoryService;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Praca
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    final static int CATEGORY_NONE = 0;
    final static int CATEGORY_ALL = 1;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<CategoryDto> findAllCategory() {
        List<Category> listCategory = categoryRepository.findAll();
        List<Product> listProduct = productRepository.findAll();
        return listCategory.stream()
                .map((category) -> mapToCategoryDto(category, listProduct))
                .filter(category -> category.getCategoryId() != CATEGORY_ALL && category.getCategoryId() != CATEGORY_NONE)
                .toList();
    }

    private CategoryDto mapToCategoryDto(Category category, List<Product> productList) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryName(category.getCategoryName());
        categoryDto.setProduct(getProductListForEachCategory(category, productList));
        return categoryDto;
    }

    private List<Product> getProductListForEachCategory(Category category, List<Product> productList) {
        List<Product> listProductForEachCategory = new LinkedList();
        for (Product product : productList) {
            if (product.getCategory().getCategoryId() == category.getCategoryId()) {
                listProductForEachCategory.add(product);
            }
        }
        return listProductForEachCategory;
    }
}
