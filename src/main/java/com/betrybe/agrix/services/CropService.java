package com.betrybe.agrix.services;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.repositories.CropRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Crop service.
 */
@Service
public class CropService {

  private CropRepository cropRepository;

  /**
   * Crop constructor.
   */
  @Autowired
  public CropService(CropRepository cropRepository) {
    this.cropRepository = cropRepository;
  }

  public Crop insertCrop(Crop crop) {
    return cropRepository.save(crop);
  }

  /**
   * Atualiza crop.
   */
  public Optional<Crop> updateCrop(Long id, Crop crop) {
    Optional<Crop> optionalCrop = cropRepository.findById(id);

    if (optionalCrop.isPresent()) {
      Crop cropFromDb = optionalCrop.get();
      cropFromDb.setName(crop.getName());
      cropFromDb.setPlantedArea(crop.getPlantedArea());
      cropFromDb.setFarmId(crop.getFarmId());


      Crop updatedCrop = cropRepository.save(cropFromDb);
      return Optional.of(updatedCrop);
    }
    return optionalCrop;
  }

  /**
   * Remove crop.
   */
  public Optional<Crop> removeCropById(Long id) {

    Optional<Crop> cropOptional = cropRepository.findById(id);

    if (cropOptional.isPresent()) {

      cropRepository.deleteById(id);

    }
    return cropOptional;
  }

  public Optional<Crop> getCropById(Long id) {
    return cropRepository.findById(id);
  }

  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

}
