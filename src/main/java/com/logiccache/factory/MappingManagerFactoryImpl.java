package com.logiccache.factory;

import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MappingManagerFactoryImpl implements MappingManagerFactory {

    private final MappingManager mappingManager;

    @Inject
    public MappingManagerFactoryImpl(final Session session) {
        mappingManager = new MappingManager(session);
    }

    @Override
    public MappingManager createMappingManager() {
        return mappingManager;
    }
}
