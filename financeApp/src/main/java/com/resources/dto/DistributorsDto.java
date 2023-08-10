package com.resources.dto;

import com.resources.entity.Media;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Praca
 */
@Getter
@Setter
public class DistributorsDto {
    
    private int distributorsId;
    
    private String distributorsName;
    
    private Media media;
}
