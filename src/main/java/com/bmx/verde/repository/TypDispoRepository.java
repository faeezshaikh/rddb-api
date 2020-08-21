package com.bmx.verde.repository;

import com.bmx.verde.model.TypDispo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TypDispoRepository extends JpaRepository<TypDispo, String> {

    @Query("SELECT d FROM TypDispo d where d.nameTypDispo = :nameTypDispo")
    Optional<TypDispo> findByNameTypDispo(@Param("nameTypDispo") String nameTypDispo);
}
