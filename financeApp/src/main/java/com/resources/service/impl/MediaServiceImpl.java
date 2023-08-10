package com.resources.service.impl;

import com.resources.dto.MediaDto;
import com.resources.entity.Distributors;
import com.resources.entity.Media;
import com.resources.repository.DistributorsRepository;
import com.resources.repository.MediaRepository;
import com.resources.service.MediaService;
import java.util.LinkedList;
import java.util.List;
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
        List<MediaDto> mediaDtoList = mediaList.stream().map(mapper -> mapToMediaDto(mapper)).toList();

        return mediaDtoList;
    }

    private MediaDto mapToMediaDto(Media media) {
        MediaDto mediaDto = new MediaDto();
        mediaDto.setId(media.getId());
        mediaDto.setMediaName(media.getMediaName());
        mediaDto.setDistributors(getDistributorListForEachMedia(media, distributorsList));
        return mediaDto;
    }

    private List<Distributors> getDistributorListForEachMedia(Media media, List<Distributors> distributorsList) {
        List<Distributors> listDistributorsForEachMedia = new LinkedList();
        for (Distributors distributors : distributorsList) {
            if (distributors.getMedia().getId() == media.getId()) {
                listDistributorsForEachMedia.add(distributors);
            }
        }
        return listDistributorsForEachMedia;
    }
}
