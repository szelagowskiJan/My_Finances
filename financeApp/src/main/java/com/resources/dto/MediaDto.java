package com.resources.dto;

import com.resources.entity.Distributors;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Praca
 */
@Getter
@Setter
public class MediaDto {

    private int id;
    
    private String mediaName;
    
    private List<Distributors> distributors;
}
