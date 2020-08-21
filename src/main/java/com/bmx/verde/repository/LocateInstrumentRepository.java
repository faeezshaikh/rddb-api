package com.bmx.verde.repository;

import com.bmx.verde.model.LocateInstrument;
import com.bmx.verde.model.LocateInstrumentId;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Instrument Location JPA Repository
 */
@Repository
public interface LocateInstrumentRepository extends JpaRepository<LocateInstrument, LocateInstrumentId> {

    /**
     * Find the location record by end date. This is useful to find the current location record using the max end date (31-DEC-9999).
     *
     * @param serialNumber serial number
     * @param instrumentType type of instrument
     * @param endDate ending date
     * @return location record matching criteria
     */
    Optional<LocateInstrument> findBySerialNumberAndInstrumentTypeAndEndDate(String serialNumber, String instrumentType,
            LocalDateTime endDate);

    /**
     * Checks to see if the instrument number and type has existing location records
     *
     * @param serialNumber serial number
     * @param instrumentType type of instrument
     * @return true if location records exist
     */
    boolean existsBySerialNumberAndInstrumentType(String serialNumber, String instrumentType);

}
