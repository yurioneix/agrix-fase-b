package com.betrybe.agrix.model.repositories;

import com.betrybe.agrix.model.entities.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository de Crop.
 */
public interface CropRepository extends JpaRepository<Crop, Long> {

}
