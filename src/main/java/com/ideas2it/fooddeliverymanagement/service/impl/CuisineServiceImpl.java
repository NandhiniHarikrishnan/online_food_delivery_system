package com.ideas2it.fooddeliverymanagement.service.impl;

import com.ideas2it.fooddeliverymanagement.dto.CuisineDTO;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.mapper.CuisineMapper;
import com.ideas2it.fooddeliverymanagement.model.Cuisine;
import com.ideas2it.fooddeliverymanagement.repository.CuisineRepository;
import com.ideas2it.fooddeliverymanagement.service.CuisineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuisineServiceImpl implements CuisineService {

    @Autowired
    CuisineRepository cuisineRepository;
    @Autowired
    CuisineMapper cuisineMapper;

    public CuisineDTO createCuisine(CuisineDTO cuisineDTO) {
        cuisineDTO.setCode(generateCode());
        return cuisineMapper.convertCusineDTO(cuisineRepository.save(cuisineMapper.convertCuisine(cuisineDTO)));
    }

    public String generateCode() {
        long code = cuisineRepository.getCuisineCount();
        return "CUI-"+ code;
    }

    public List<CuisineDTO> getCuisines() throws FoodDeliveryManagementException {
        List<Cuisine> cuisines = cuisineRepository.findAll();
        if(cuisines.isEmpty()) {
            throw new FoodDeliveryManagementException("NOT_FOUND", HttpStatus.NOT_FOUND);
        }
        return cuisineMapper.convertCuisinesDTO(cuisines);
    }

    public CuisineDTO getCuisineById(int id) throws FoodDeliveryManagementException {
        Optional<Cuisine>  cuisine = cuisineRepository.findById(id);
        if(!cuisine.isPresent()) {
            throw new FoodDeliveryManagementException(id + "NOT_FOUND", HttpStatus.NOT_FOUND);
        }
        return cuisineMapper.convertCusineDTO(cuisine.get());
    }

    @Override
    public CuisineDTO updateCuisineById(CuisineDTO cuisineDTO, int id) throws FoodDeliveryManagementException {
        CuisineDTO existingCuisineDTO = null;
        if(cuisineRepository.existsById(id)) {
            existingCuisineDTO = getCuisineById(id);
            if(null != existingCuisineDTO) {
                existingCuisineDTO.setName(cuisineDTO.getName());
                if (null != existingCuisineDTO.getRestaurantDTOs()) {

                }
            }
        }
        return null;
    }

    @Override
    public String deleteCuisineById(int id) throws FoodDeliveryManagementException {
        return null;
    }
}
