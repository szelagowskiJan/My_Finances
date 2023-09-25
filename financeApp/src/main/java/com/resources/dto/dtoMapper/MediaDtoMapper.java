package com.resources.dto.dtoMapper;

import com.resources.dto.MediaDto;
import com.resources.entity.*;
import java.util.*;

/**
 *
 * @author szela
 */
public class MediaDtoMapper {

    private static List<Distributors> distributorsList;

    private MediaDtoMapper() {
    }

    public static List<MediaDto> mapToMediaDtos(List<Media> mediaList, List<Distributors> distributorsList) {
        MediaDtoMapper.distributorsList = distributorsList;
        return mediaList.stream().map(mapper -> mapToMediaDto(mapper)).toList();
    }

    private static MediaDto mapToMediaDto(Media media) {
        MediaDto mediaDto = new MediaDto();
        mediaDto.setId(media.getId());
        mediaDto.setMediaName(media.getMediaName());
        mediaDto.setDistributors(getDistributorListForEachMedia(media, distributorsList));
        return mediaDto;
    }

    private static List<Distributors> getDistributorListForEachMedia(Media media, List<Distributors> distributorsList) {
        List<Distributors> listDistributorsForEachMedia = new LinkedList();
        for (Distributors distributors : distributorsList) {
            if (distributors.getMedia().getId() == media.getId()) {
                listDistributorsForEachMedia.add(distributors);
            }
        }
        return listDistributorsForEachMedia;
    }
}
