package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.entity.Packaging;
import com.oocl.packagebooking.service.PackagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/packages")
@CrossOrigin(origins = {"http://localhost:9527", "null"})
public class PackagingController {

    @Autowired
    PackagingService packagingService;

    @GetMapping()
    public ResponseEntity findAllPackage(){
        try {
            return new ResponseEntity(packagingService.findAllPackage(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{status}")
    public ResponseEntity findPackageByStatus(@PathVariable String packageStatus){
        try {
            return new ResponseEntity(packagingService.findPackageByStatus(packageStatus), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{packageId}")
    public ResponseEntity updatePackageStatus(@PathVariable Long packageId, @RequestBody Packaging packaging){
        try {
            return new ResponseEntity(packagingService.updatePackageStatus(packageId, packaging), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity addPackage(@RequestBody Packaging packaging){
        try {
            return new ResponseEntity(packagingService.addPackage(packaging), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
