package com.resources.dto;

import java.util.List;
import lombok.*;

/**
 *
 * @author szela
 */
@Getter
@Setter
public class PlannerSupportFieldsDto {

    private List<HistoryTradeDto> historyTradeList;
    private List<CategoryDto> categoryList;
}
