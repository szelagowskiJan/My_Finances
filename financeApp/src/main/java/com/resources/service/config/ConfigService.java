package com.resources.service.config;

import com.resources.dto.HistoryTradeDto;
import com.resources.dto.UserDto;
import com.resources.entity.Category;
import com.resources.entity.HistoryTrade;
import com.resources.entity.Product;
import com.resources.entity.UserEntity;
import com.resources.repository.HistoryTradeRepository;
import com.resources.repository.ProductRepository;
import com.resources.repository.UserRepository;
import com.resources.service.impl.CategoryEnum;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Praca
 */
public class ConfigService {

    final static int USER_ROLE = 1;
    final static int ADMIN_ROLE = 2;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HistoryTradeRepository historyTradeRepository;

    protected Calendar rightNow = Calendar.getInstance();

    protected List<Product> getProductsList() {
        return productRepository.findAll();
    }

    protected int getUserId(String email) {
        UserEntity user = userRepository.findByEmail(email);
        return user.getUserId();
    }

    protected HistoryTradeDto mapToHistoryTradeDto(HistoryTrade transaction, List<Product> products) {

        HistoryTradeDto historyTradeDto = new HistoryTradeDto();
        historyTradeDto.setId(transaction.getId());
        historyTradeDto.setUserId(transaction.getUserId());
        historyTradeDto.setTradeDt(transaction.getTradeDt());
        historyTradeDto.setAmount(transaction.getAmount() * getExpense(transaction));
        historyTradeDto.setProductId(transaction.getProductId());
        historyTradeDto.setProduct(getProduct(transaction.getProductId(), products));
        historyTradeDto.setExpense(getExpense(transaction));
        historyTradeDto.setTitle(transaction.getTitle());

        return historyTradeDto;
    }

    protected Product getProduct(int productId, List<Product> products) {
        Product product = products.get(productId);
        for (Product prd : products) {
            if (prd.getProductId() == productId) {
                product = prd;
            }
        }
        return product;
    }

    protected int getExpense(HistoryTrade historyTrade) {
        Product product = getProduct(historyTrade.getProductId(), getProductsList());
        Category category = product.getCategory();
        return (category.getCategoryId() == CategoryEnum.PROFIT.categoryId) ? 1 : -1;
    }

    protected List<HistoryTradeDto> allTransactionForUser(String email) {
        List<HistoryTrade> transactions = historyTradeRepository.findByUserId(getUserId(email));
        List<Product> products = getProductsList();
        return transactions.stream()
                .map((trade) -> mapToHistoryTradeDto(trade, products))
                .sorted((d1, d2) -> d1.getTradeDt().compareTo(d2.getTradeDt()))
                .toList();
    }

    protected String getUserEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    protected Calendar dateToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    protected UserEntity userDtoMappedToUserEntity(UserDto userDto) {
        UserEntity user = new UserEntity();

        user.setUserId(userDto.getUserId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setUserRole(USER_ROLE);
        user.setVerificationCode(userDto.getVerificationCode());
        return user;
    }
}
