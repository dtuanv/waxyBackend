package com.waxy.controller.layout;

import com.waxy.database.entity.layout.EssentialLinkGroup;
import com.waxy.dto.layout.EssentialLinkGroupDto;
import com.waxy.service.layout.EssentialLinkGroupService;
import com.waxy.service.layout.EssentialLinkService;
import com.waxy.utils.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class EssentialLinkGroupController {
    @Autowired
    EssentialLinkGroupService essentialLinkGroupService;
    private static final Logger logger = LoggerFactory.getLogger(EssentialLinkGroupController.class);
    @PostMapping("/groupLink/save")
    public ResponseEntity<ApiResponse> saveGroupLink(@RequestBody EssentialLinkGroupDto essentialLinkGroupDto){
        try {
            essentialLinkGroupService.saveOrUpdate(essentialLinkGroupDto);
            return ResponseEntity.ok(new ApiResponse(true, "Group Link saved successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Error occurred while saving Group Link"));
        }
    }

    @GetMapping("/groupLink/list")
    public ResponseEntity<?> getEssentialLinkGroupList(){
        try {
            return ResponseEntity.ok(essentialLinkGroupService.getAllEssentialLinKGroup());
        }catch (Exception e){
            logger.error("An error occurred while fetching the Essential Link Groups: {}", e.getMessage(), e);

            ApiResponse errorResponse = new ApiResponse(false, "An error occurred /groupLink/list: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
