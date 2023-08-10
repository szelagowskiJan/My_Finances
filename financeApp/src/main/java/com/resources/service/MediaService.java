package com.resources.service;

import com.resources.dto.MediaDto;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Praca
 */
@Service
public interface MediaService {

    List<MediaDto> findAllMedia();

}
