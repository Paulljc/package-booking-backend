package com.oocl.packagebooking.repository;

import com.oocl.packagebooking.entity.Packaging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Supplier;

@Repository
public interface PackagingRepository extends JpaRepository<Packaging, Long> {

    List<Packaging> findByStatus(String packageStatus);
    Supplier<Packaging> findByBillno(String billNo);
}
