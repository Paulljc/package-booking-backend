package com.oocl.packagebooking.service;

import com.oocl.packagebooking.entity.Packaging;
import com.oocl.packagebooking.repository.PackagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Packaging updatePickTimeByBillNumber(String billNo, Date appTime) {
        Packaging packagingDB = packagingRepository.findByBillno(billNo).get();
        packagingDB.setApptime(appTime);
        return packagingRepository.save(packagingDB);
    }
}
