package com.logiccache;

import com.logiccache.core.BookService;
import com.logiccache.core.BookServiceImpl;
import com.logiccache.factory.MappingManagerFactory;
import com.logiccache.factory.MappingManagerFactoryImpl;
import com.logiccache.health.BookHealthCheck;
import com.logiccache.resources.BookResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import systems.composable.dropwizard.cassandra.CassandraBundle;
import systems.composable.dropwizard.cassandra.CassandraFactory;

import java.util.concurrent.ExecutorService;

public class AsyncApplication extends Application<AsyncConfiguration> {

    public static void main(final String[] args) throws Exception {
        new AsyncApplication().run(args);
    }

    @Override
    public String getName() {
        return "dropwizard-hk2-async";
    }

    @Override
    public void initialize(final Bootstrap<AsyncConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<AsyncConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(AsyncConfiguration configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });
        bootstrap.addBundle(new CassandraBundle<AsyncConfiguration>() {
            @Override
            public CassandraFactory getCassandraFactory(AsyncConfiguration configuration) {
                return configuration.getCassandraConfig();
            }
        });
    }

    @Override
    public void run(final AsyncConfiguration configuration, final Environment environment) {
        environment.healthChecks().register("book", new BookHealthCheck());
        environment.jersey().register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(BookServiceImpl.class).to(BookService.class);
                bind(environment.lifecycle().executorService("executor").build()).to(ExecutorService.class);
                bind(MappingManagerFactoryImpl.class).to(MappingManagerFactory.class);
            }
        });
        environment.jersey().register(BookResource.class);
    }
}