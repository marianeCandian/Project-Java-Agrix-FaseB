package com.betrybe.agrix.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

/**
 * Crop entity.
 */
@Entity
@Table(name = "crop")
public class Crop {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "farm_id")
  private Long farmId;

  private String name;

  @Column(name = "planted_area")
  private Double plantedArea;

  @Column(name = "planting_date")
  private LocalDate plantingDate;

  @Column(name = "harvest_date")
  private LocalDate harvestDate;

  public Crop() {

  }

  /**
   * Crop entity constructor.
   */
  public Crop(Long id, Long farmId, String name, Double plantedArea, LocalDate plantingDate,
      LocalDate harvestDate) {
    this.id = id;
    this.farmId = farmId;
    this.name = name;
    this.plantedArea = plantedArea;
    this.plantingDate = plantingDate;
    this.harvestDate = harvestDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getFarmId() {
    return farmId;
  }

  public void setFarmId(Long farmId) {
    this.farmId = farmId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPlantedArea() {
    return plantedArea;
  }

  public void setPlantedArea(Double plantedArea) {
    this.plantedArea = plantedArea;
  }

  public LocalDate getPlantingDate() {
    return plantingDate;
  }

  public void setPlantingDate(LocalDate plantingDate) {
    this.plantingDate = plantingDate;
  }

  public LocalDate getHarvestDate() {
    return harvestDate;
  }

  public void setHarvestDate(LocalDate harvestDate) {
    this.harvestDate = harvestDate;
  }
}
