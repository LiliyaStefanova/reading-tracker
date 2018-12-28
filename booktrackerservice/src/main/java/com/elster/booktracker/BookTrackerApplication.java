package com.elster.booktracker;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import ru.vyarus.dropwizard.guice.GuiceBundle;
import ru.vyarus.guicey.jdbi3.JdbiBundle;

public class BookTrackerApplication extends Application<BookTrackerConfiguration> {

    public static void main(String [] args) throws Exception{
        new BookTrackerApplication().run(args);
    }


    public void initialize(Bootstrap<BookTrackerConfiguration> bootstrap){

        bootstrap.addBundle(GuiceBundle.builder()
                .modules(new BookTrackerModule(), new DropwizardModule())
                .enableAutoConfig(getClass().getPackage().getName())
                .bundles(JdbiBundle.<BookTrackerConfiguration>forDatabase((conf, env) -> conf.getDatabase()))
                .build());

        super.initialize(bootstrap);

        bootstrap.addBundle(new SwaggerBundle<BookTrackerConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(BookTrackerConfiguration config){
                return config.getSwaggerBundleConfiguration();
            }
        });


    }

    public void run(BookTrackerConfiguration bookTrackerConfiguration, Environment environment) throws Exception {



    }
}
