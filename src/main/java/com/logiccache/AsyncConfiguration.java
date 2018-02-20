package com.logiccache;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import systems.composable.dropwizard.cassandra.CassandraFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class AsyncConfiguration extends Configuration {
    @JsonProperty("swagger")
    public SwaggerBundleConfiguration swaggerBundleConfiguration;

    @Valid
    @NotNull
    private CassandraFactory cassandra;

    @JsonProperty("cassandra")
    public CassandraFactory getCassandraConfig() {
        return cassandra;
    }

    @JsonProperty("cassandra")
    public void setCassandraConfig(CassandraFactory cassndraConfig) {
        this.cassandra = cassndraConfig;
    }
}
