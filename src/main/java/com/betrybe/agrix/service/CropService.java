package com.betrybe.agrix.service;

import com.betrybe.agrix.controller.dto.CropResponseDto;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.repositories.CropRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service de plantação.
 */
@Service
public class CropService {

  private CropRepository cropRepository;

  @Autowired
  public CropService(CropRepository cropRepository) {
    this.cropRepository = cropRepository;
  }

  public Optional<Crop> getCropById(Long cropId) {
    return cropRepository.findById(cropId);
  }

  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  public Crop saveCrop(Crop crop) {
    return cropRepository.save(crop);
  }

  public Optional<List<Crop>> getCropByHarvestDate(LocalDate start, LocalDate end) {
    Optional<List<Crop>> crops = cropRepository.findAllCropByHarvestDateBetween(start, end);
    return crops;
  }

}
