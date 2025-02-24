package com.qpa.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.qpa.dto.SpotCreateDTO;
import com.qpa.dto.SpotResponseDTO;
import com.qpa.dto.SpotSearchCriteria;
import com.qpa.dto.SpotStatistics;
import com.qpa.entity.User;
import com.qpa.repository.UserRepository;
import com.qpa.service.SpotService;

@RestController
@RequestMapping("/api/spots")
public class SpotController {
    private static final Logger log = LoggerFactory.getLogger(SpotController.class);
    private final SpotService spotService;
    private final UserRepository userRepository;
    
    @Autowired
    public SpotController(SpotService spotService, UserRepository userRepository) {
        this.spotService = spotService;
        this.userRepository = userRepository;
    }
    
    // Spot Owner endpoints
    @PostMapping
    public ResponseEntity<SpotResponseDTO> createSpot(
            @RequestPart("spot") SpotCreateDTO spotDTO,
            @RequestPart("images") List<MultipartFile> images) {
        spotDTO.setImages(images);
        spotDTO.setOwner(userRepository.findById(getCurrentUserId()).get()); // Might throw exception, needs to be handled

        return ResponseEntity.ok(spotService.createSpot(spotDTO, getCurrentUserId()));
    }
    
    @PutMapping("/{spotId}")
    public ResponseEntity<SpotResponseDTO> updateSpot(
            @PathVariable Long spotId,
            @RequestPart("spot") SpotCreateDTO spotDTO,
            @RequestPart(value = "images", required = false) List<MultipartFile> images) {
        spotDTO.setImages(images);
        return ResponseEntity.ok(spotService.updateSpot(spotId, spotDTO));
    }
    
    @DeleteMapping("/{spotId}")
    public ResponseEntity<Void> deleteSpot(@PathVariable Long spotId) {
        spotService.deleteSpot(spotId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/owner")
    public ResponseEntity<List<SpotResponseDTO>> getOwnerSpots() {
        return ResponseEntity.ok(spotService.getSpotByOwner(getCurrentUserId()));
    }
    
    // Vehicle Owner endpoints
    @GetMapping("/search")
    public ResponseEntity<List<SpotResponseDTO>> searchSpots(SpotSearchCriteria criteria) {
        return ResponseEntity.ok(spotService.searchSpots(criteria));
    }

    @PostMapping("/{spotId}/rating")
    public ResponseEntity<SpotResponseDTO> postRating(
            @PathVariable Long spotId,
            @RequestParam Double rating) {
        return ResponseEntity.ok(spotService.postSpotRating(spotId, rating));
    }
    
    @PutMapping("/{spotId}/rating")
    public ResponseEntity<SpotResponseDTO> updateRating(
            @PathVariable Long spotId,
            @RequestParam Double rating) {
        return ResponseEntity.ok(spotService.updateSpotRating(spotId, rating));
    }
    
    // Admin endpoints
    @GetMapping("/admin/all")
    public ResponseEntity<List<SpotResponseDTO>> getAllSpots() {
        return ResponseEntity.ok(spotService.getAllSpots());
    }
    
    @DeleteMapping("/admin/{spotId}")
    public ResponseEntity<Void> adminDeleteSpot(@PathVariable Long spotId) {
        spotService.deleteSpot(spotId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/admin/statistics")
    public ResponseEntity<SpotStatistics> getStatistics() {
        return ResponseEntity.ok(spotService.getStatistics());
    }
    
    private Long getCurrentUserId() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername())
                .map(User::getId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
