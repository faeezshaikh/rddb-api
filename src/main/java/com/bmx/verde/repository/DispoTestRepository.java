package com.bmx.verde.repository;

import com.bmx.verde.model.DispoTest;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DispoTestRepository extends JpaRepository<DispoTest, Long> {

    @Query("SELECT d FROM DispoTest d where d.barcodeNumber = :barcodeNum")
    Optional<DispoTest> findByBarcodeNumber(@Param("barcodeNum") String barcodeNum);
}
