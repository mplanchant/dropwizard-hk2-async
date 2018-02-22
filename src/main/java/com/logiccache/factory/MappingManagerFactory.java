package com.logiccache.factory;

import com.datastax.driver.mapping.MappingManager;

public interface MappingManagerFactory {
    MappingManager createMappingManager();
}
