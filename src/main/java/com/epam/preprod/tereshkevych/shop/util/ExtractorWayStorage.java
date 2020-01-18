package com.epam.preprod.tereshkevych.shop.util;

import com.epam.preprod.tereshkevych.shop.security.holder.ModeStorage;
import com.epam.preprod.tereshkevych.shop.security.holder.StorageContext;
import com.epam.preprod.tereshkevych.shop.security.holder.StorageSession;
import com.epam.preprod.tereshkevych.shop.security.holder.Storage;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ExtractorWayStorage {

    private static final Map<ModeStorage, Storage> storageMap = getStorageMapping();

    public static Storage getWayStorage(String nameMode) {
        ModeStorage modeStorage = ModeStorage.getEnumByString(nameMode);
        return storageMap.get(modeStorage);
    }

    private static Map<ModeStorage, Storage> getStorageMapping() {
        Map<ModeStorage, Storage> storageMap = new HashMap<>();
        storageMap.put(ModeStorage.SESSION, new StorageSession());
        storageMap.put(ModeStorage.CONTEXT, new StorageContext());
        return Collections.unmodifiableMap(storageMap);
    }
}