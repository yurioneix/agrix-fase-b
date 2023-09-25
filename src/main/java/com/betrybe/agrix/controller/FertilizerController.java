package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.FertilizerCreationDto;
import com.betrybe.agrix.controller.dto.FertilizerResponseDto;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller da classe Fertilizer.
 */
@RestController
@RequestMapping(value = "/fertilizers")
public class FertilizerController {
  private final FertilizerService fertilizerService;

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Rota GET /fertilizers.
   */
  @GetMapping()
  public ResponseEntity<List<FertilizerResponseDto>> getAllFertilizers() {
    List<Fertilizer> fertilizers = fertilizerService.getAllFertilizers();

    List<FertilizerResponseDto> fertilizerResponseDtoList = fertilizers.stream()
        .map((fertilizer) -> new FertilizerResponseDto(
            fertilizer.getId(),
            fertilizer.getName(),
            fertilizer.getBrand(),
            fertilizer.getComposition()
        )).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(fertilizerResponseDtoList);
  }

  /**
   * Rota POST /fertilizers.
   */
  @PostMapping()
  public ResponseEntity<FertilizerResponseDto> saveFertilizer(
      @RequestBody FertilizerCreationDto fertilizerCreationDto
  ) {
    Fertilizer fertilizer = fertilizerService.saveFertilizer(fertilizerCreationDto);

    FertilizerResponseDto fertilizerResponseDto = new FertilizerResponseDto(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition()
    );

    return ResponseEntity.status(HttpStatus.CREATED).body(fertilizerResponseDto);
  }
}
