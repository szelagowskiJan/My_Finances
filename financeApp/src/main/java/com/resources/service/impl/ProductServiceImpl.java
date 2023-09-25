package com.resources.service.impl;

import com.resources.dto.ProductDto;
import com.resources.dto.dtoMapper.ProductDtoMapper;
import com.resources.entity.Product;
import com.resources.repository.ProductRepository;
import com.resources.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Praca
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product findProductByProductId(int productId) {
        return productRepository.findByProductId(productId);
    }

    @Override
    public List<ProductDto> findAllProducts() {
        List<Product> products = productRepository.findAll();
        return ProductDtoMapper.mapToProductDtos(products);
    }
}
