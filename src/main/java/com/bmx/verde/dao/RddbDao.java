package com.bmx.verde.dao;

import com.bmx.verde.model.InstrumentOpticConfig;
import java.util.List;

public interface RddbDao {
    int addTypDisp(String cardName, int numWells, String userField, String instrumentType, String activeFlag,
            String productionFlag, String barcode);

    int addDispoTest(int carouselSlot, String cardName, int defaultExper, int defaultEssay, String barcode,
            int defaultStrainId, String defaultStorageConditions, String defaultBadFlag, String defaultFlagActive,
            String runDate, String serialNumber, String instrumentType, String itgStatusCode, String dilutionType,
            String barcodDispoTest);

    int updateDispoTest(long noDispoTest, String dilutionType, Integer carousel, String instrSerialNumber,
            String instrType, String runDate, String itgStatusCode);

    List<InstrumentOpticConfig> loadOpticConfigs();
}
