package com.bmx.verde.repository;

import com.bmx.verde.model.Instrument;
import com.bmx.verde.model.InstrumentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrumentRepository extends JpaRepository<Instrument, InstrumentId> {
}
