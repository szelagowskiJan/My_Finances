package com.resources.service;

import com.resources.dto.CategoryDto;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Praca
 */
@Service
public interface CategoryService {

    List<CategoryDto> findAllCategory();
}
