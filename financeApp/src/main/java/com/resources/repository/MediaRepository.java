package com.resources.repository;

import com.resources.entity.Media;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Praca
 */
@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

    List<Media> findAll();

    void removeById(int id);

    Media save(Media media);

}
