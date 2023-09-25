package com.resources.service.impl;

import com.resources.dto.CategoryDto;
import com.resources.dto.dtoMapper.CategoryDtoMapper;
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
                .map((category) -> CategoryDtoMapper.mapToCategoryDto(category, listProduct))
                .filter(category -> category.getCategoryId() != CATEGORY_ALL && category.getCategoryId() != CATEGORY_NONE)
                .toList();
    }
}
