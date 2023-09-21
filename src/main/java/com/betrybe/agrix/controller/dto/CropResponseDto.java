package com.betrybe.agrix.controller.dto;

/**
 * Dto de resposta de Crop.
 */
public record CropResponseDto(Long id, String name, Double plantedArea, Long farmId) {}
