package com.resources.dto.dtoMapper;

import com.resources.dto.ProductDto;
import com.resources.entity.Product;
import java.util.List;

/**
 *
 * @author szela
 */
public class ProductDtoMapper {
   
    private ProductDtoMapper() {
    }

    public static List<ProductDto> mapToProductDtos(List<Product> products) {
        return products.stream()
                .map((product) -> mapToProductDto(product))
                .toList();
    }

    private static ProductDto mapToProductDto(Product product) {
        return ProductDto.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .Category(product.getCategory())
                .build();
    }
}
