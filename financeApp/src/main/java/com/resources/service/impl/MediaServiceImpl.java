package com.resources.service.impl;

import com.resources.dto.MediaDto;
import com.resources.dto.dtoMapper.MediaDtoMapper;
import com.resources.entity.*;
import com.resources.repository.*;
import com.resources.service.MediaService;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Praca
 */
@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private DistributorsRepository distributorsRepository;

    private List<Distributors> distributorsList;

    @Override
    public List<MediaDto> findAllMedia() {
        List<Media> mediaList = mediaRepository.findAll();
        distributorsList = distributorsRepository.findAll();
        return MediaDtoMapper.mapToMediaDtos(mediaList, distributorsList);
    }
}
