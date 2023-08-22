package com.resources.service;

import com.resources.dto.HistoryTradeDto;
import com.resources.entity.Category;
import com.resources.entity.HistoryTrade;
import com.resources.entity.Product;
import com.resources.entity.UserEntity;
import com.resources.repository.HistoryTradeRepository;
import com.resources.repository.ProductRepository;
import com.resources.repository.UserRepository;
import com.resources.service.impl.SummaryServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import com.resources.service.serviceConfig.ServiceConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Praca
 */
public class SummaryServiceImplTest extends ServiceConfig{
    
    @Mock
    UserRepository userRepository;
    
    @Mock
    BillsWalletService billsWalletService;
    
    @Mock
    HistoryTradeRepository historyTradeRepository;
    
    @Mock
    ProductRepository productRepository;
    
    @InjectMocks
    SummaryServiceImpl summaryServiceImpl;
    
    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void getBalance_1000_2000() {
        HistoryTrade historyTradeFirst = new HistoryTrade();
        historyTradeFirst.setId(1);
        historyTradeFirst.setAmount(1000);
        historyTradeFirst.setProductId(1);
        HistoryTrade historyTradeSecond = new HistoryTrade();
        historyTradeSecond.setId(2);
        historyTradeSecond.setAmount(1000);
        historyTradeSecond.setProductId(1);
        List<HistoryTrade> mounthBalance = new ArrayList<>();
        mounthBalance.add(historyTradeFirst);
        mounthBalance.add(historyTradeSecond);
        Product product = new Product();
        product.setProductId(1);
        product.setProductName("Toy");
        product.setCategory(new Category());
        List<Product> listProduct = new ArrayList<>();
        listProduct.add(product);
        UserEntity userEntity = getCorrectUserEntity();
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("nowak@wp.pl");
        when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
        when(historyTradeRepository.findByUserId(anyInt())).thenReturn(mounthBalance);
        when(productRepository.findAll()).thenReturn(listProduct);
        double result = summaryServiceImpl.getBalance();
        Assertions.assertEquals(2000, result);
    }
}
