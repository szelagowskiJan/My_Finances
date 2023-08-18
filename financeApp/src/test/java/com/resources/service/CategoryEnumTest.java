package com.resources.service;

import com.resources.service.impl.CategoryEnum;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

/**
 *
 * @author Praca
 */
public class CategoryEnumTest {
    
    @InjectMocks
    CategoryEnum categoryEnum;
    
    @Test
    public void CategoryEnum_correctName_success(){
        assertEquals("profit", CategoryEnum.PROFIT.name);
    }
    
    @Test
    public void CategoryEnum_correctValue_success(){
        assertEquals(8, CategoryEnum.PROFIT.categoryId);
    }
}
