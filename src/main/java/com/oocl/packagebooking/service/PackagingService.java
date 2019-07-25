package com.oocl.packagebooking.service;

import com.oocl.packagebooking.entity.Packaging;
import com.oocl.packagebooking.repository.PackagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PackagingService {
    @Autowired
    PackagingRepository packagingRepository;

    public List<Packaging> findAllPackage() {
        return packagingRepository.findAll().stream().collect(Collectors.toList());
    }

    public Packaging updatePackageStatus(Long packageId, Packaging packaging) {
        Packaging packagingDB = packagingRepository.findById(packageId).get();
        packagingDB.setStatus(packaging.getStatus());
        return packagingRepository.save(packagingDB);
    }

    public Packaging addPackage(Packaging packaging) {
        return packagingRepository.save(packaging);
    }

    public List<Packaging> findPackageByStatus(String packageStatus) {
        return packagingRepository.findByStatus(packageStatus);
    }

    public Packaging updatePickTimeByBillNumber(String billNo, Long appTime) {
        Packaging packagingDB = packagingRepository.findByBillno(billNo).get(0);
        packagingDB.setApptime(appTime);
        return packagingRepository.save(packagingDB);
    }


    public Packaging addAppointTime(Packaging packaging) throws Exception {
        Date date = new Date(packaging.getApptime());
        Packaging packageDB = packagingRepository.findById(packaging.getId()).get();
        if(date.getHours() > 9 && date.getHours() < 20){
            throw new Exception(String.format("Date Error", date));
        }
        packageDB.setStatus("已预约");
        packageDB.setApptime(packaging.getApptime());
        return packagingRepository.save(packageDB);
    }
}
