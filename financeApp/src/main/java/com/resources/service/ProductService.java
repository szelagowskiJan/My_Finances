package com.resources.service;

import com.resources.dto.ProductDto;
import com.resources.entity.Product;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Praca
 */
@Service
public interface ProductService {

    Product findProductByProductId(int productId);

    List<ProductDto> findAllProducts();
}
