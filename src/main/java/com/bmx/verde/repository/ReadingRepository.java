package com.bmx.verde.repository;

import com.bmx.verde.model.Reading;
import com.bmx.verde.model.ReadingPk;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingRepository extends JpaRepository<Reading, ReadingPk> {

    // @Query("SELECT count(*) FROM Reading d where d.noDispoTest = :noDispoTest")
    // public long getCount(@Param("noDispoTest") Long noDispoTest);

    List<Reading> findByIdNoDispoTest(long noDispoTest);
}
