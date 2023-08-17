package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.ResponseDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.services.CropService;
import com.betrybe.agrix.services.FarmService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.hibernate.mapping.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe controller.
 */
@RestController
@RequestMapping()
public class CropController {

  private CropService cropService;

  private FarmService farmService;

  /**
   * Crop controller constructor.
   */
  @Autowired
  public CropController(CropService cropService, FarmService farmService) {
    this.cropService = cropService;
    this.farmService = farmService;
  }

  /**
   * Cria nova crop.
   */
  @PostMapping("/farms/{farmId}/crops")
  public ResponseEntity<?> createCrop(@PathVariable Long farmId, @RequestBody CropDto cropDto) {
    Optional<Farm> optionalFarm = farmService.getFarmById(farmId);

    if (optionalFarm.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
    }

    Crop newCrop = cropService.insertCrop(cropDto.toCrop(farmId));
    return ResponseEntity.status(HttpStatus.CREATED).body(newCrop);
  }

  /**
   * Lista as plantações de uma fazenda.
   */
  @GetMapping("/farms/{farmId}/crops")
  public ResponseEntity<?> getCropsByFarmId(@PathVariable Long farmId) {
    Optional<Farm> optionalFarm = farmService.getFarmById(farmId);

    if (optionalFarm.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
    }

    List<Crop> allCrops = cropService.getAllCrops();
    List listaStream = allCrops.stream()
        .filter(crop -> crop.getFarmId().equals(farmId))
        .map((crop) -> new CropDto(
            crop.getId(), crop.getName(), crop.getFarmId(), crop.getPlantedArea()))
        .collect(Collectors.toList());

    return  ResponseEntity.ok(listaStream);

  }

  /**
   * Atualiza crop.
   */
  @PutMapping("/{cropId}")
  public ResponseEntity<ResponseDto<Crop>> updateCrop(
      @PathVariable Long cropId, @RequestBody CropDto cropDto) {
    Optional<Crop> optionalCrop = cropService.updateCrop(cropId, cropDto.toCrop());

    if (optionalCrop.isEmpty()) {
      ResponseDto<Crop> responseDto = new ResponseDto<>(
          String.format("Não foi encontrado a fazenda de ID %d", cropId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }

    ResponseDto<Crop> responseDto = new ResponseDto<>(
        "Fazenda atualizada com sucesso!", optionalCrop.get());
    return ResponseEntity.ok(responseDto);
  }

  /**
   * Deleta crop.
   */
  @DeleteMapping("/{cropId}")
  public ResponseEntity<ResponseDto<Crop>> removeCropById(@PathVariable Long cropId) {
    Optional<Crop> optionalCrop = cropService.removeCropById(cropId);

    if (optionalCrop.isEmpty()) {
      ResponseDto<Crop> responseDto = new ResponseDto<>(
          String.format("Não foi encontrado a fazenda de ID %d", cropId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }

    ResponseDto<Crop> responseDto = new ResponseDto<>("Fazenda removida com sucesso!", null);
    return ResponseEntity.ok(responseDto);
  }

  /**
   * Encontra crops pelo id.
   */
  @GetMapping("/crops/{id}")
  public ResponseEntity<?> getCropById(@PathVariable Long id) {
    Optional<Crop> optionalCrop = cropService.getCropById(id);

    if (optionalCrop.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plantação não encontrada!");
    }
    return ResponseEntity.ok(optionalCrop.get());
  }

  /**
   * Encontra todas crops.
   */
  @GetMapping("/crops")
  public List<CropDto> getAllCrops() {
    List<Crop> allCrops = cropService.getAllCrops();
    return allCrops.stream()
        .map((crop) -> new CropDto(
            crop.getId(), crop.getName(), crop.getFarmId(), crop.getPlantedArea()))
        .collect(Collectors.toList());
  }

}