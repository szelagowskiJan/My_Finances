package com.resources.service;

import com.resources.entity.BillsWallet;
import com.resources.entity.UserEntity;
import com.resources.repository.BillsWalletRepository;
import com.resources.repository.UserRepository;
import com.resources.service.impl.BillsWalletServiceImpl;
import com.resources.service.serviceConfig.BillsWalletServiceConfig;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.same;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Praca
 */
public class BillsWalletServiceImplTest extends BillsWalletServiceConfig {

    @Mock
    BillsWalletRepository billsWalletRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    BillsWalletServiceImpl billsWalletServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void save_correctBillsWallet_success() {
        BillsWallet billsWallet = getCorectDetailsBillsWallet();

        billsWalletServiceImpl.save(billsWallet);

        verify(billsWalletRepository).save(same(billsWallet));
    }

    @Test
    public void removeWallet_correctId_success() {
        int plannedExpensesId = 5;

        billsWalletServiceImpl.removeWallet(plannedExpensesId);

        verify(billsWalletRepository).deleteById(same(plannedExpensesId));
    }

    @Test
    public void getWalletBalance_positiveValues_positiveResult() {
        BillsWallet billsWalletAmount500 = getCorectDetailsBillsWallet();
        billsWalletAmount500.setAmount(500);
        BillsWallet billsWalletAmount700 = getCorectDetailsBillsWallet();
        billsWalletAmount700.setAmount(700);
        List<BillsWallet> listBillsWallet = new ArrayList<>();
        listBillsWallet.add(billsWalletAmount500);
        listBillsWallet.add(billsWalletAmount700);

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(5);

        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(authentication.getName()).thenReturn("nowak@wp.pl");
        when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
        when(billsWalletRepository.findByUserId(anyInt())).thenReturn(listBillsWallet);

        double result = billsWalletServiceImpl.getWalletBalance();

        Assertions.assertEquals(1200.0, result);
    }

    @Test
    public void getWalletBalance_negativeValues_negativeResult() {
        BillsWallet billsWalletAmount500 = getCorectDetailsBillsWallet();
        billsWalletAmount500.setAmount(-500);
        BillsWallet billsWalletAmount700 = getCorectDetailsBillsWallet();
        billsWalletAmount700.setAmount(-700);

        List<BillsWallet> listBillsWallet = new ArrayList<>();
        listBillsWallet.add(billsWalletAmount500);
        listBillsWallet.add(billsWalletAmount700);

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(5);

        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(authentication.getName()).thenReturn("nowak@wp.pl");
        when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
        when(billsWalletRepository.findByUserId(anyInt())).thenReturn(listBillsWallet);

        double result = billsWalletServiceImpl.getWalletBalance();

        Assertions.assertEquals(-1200.0, result);
    }
}
