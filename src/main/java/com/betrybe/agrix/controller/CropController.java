package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropResponseDto;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.exception.CropNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller de Crop.
 */
@RestController
@RequestMapping(value = "/crops")
public class CropController {
  private final CropService cropService;

  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Rota GET que retorna todas as plantações.
   */
  @GetMapping()
  public List<CropResponseDto> getAllCrops() {
    List<Crop> crops = cropService.getAllCrops();

    return crops.stream()
        .map((crop) -> new CropResponseDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getFarm().getId()
        )).collect(Collectors.toList());
  }

  /**
   * Rota GET /crops/{id}/ que retorna uma plantação pelo seu id.
   */
  @GetMapping("/{id}")
  public ResponseEntity<CropResponseDto> getCropById(@PathVariable Long id) {
    Optional<Crop> optionalCrop = cropService.getCropById(id);

    if (optionalCrop.isEmpty()) {
      throw new CropNotFoundException();
    }

    Crop crop = optionalCrop.get();

    CropResponseDto cropResponseDto = new CropResponseDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getFarm().getId()
    );

    return ResponseEntity.ok().body(cropResponseDto);
  }
}
