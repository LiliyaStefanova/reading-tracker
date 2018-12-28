package com.elster.booktracker;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientConfiguration;
import io.dropwizard.db.DataSourceFactory;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class BookTrackerConfiguration extends Configuration {

    @Valid
    public SwaggerBundleConfiguration swaggerBundleConfiguration;

    @Valid
    private HttpClientConfiguration httpClient = new HttpClientConfiguration();

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty
    public SwaggerBundleConfiguration getSwaggerBundleConfiguration() {
        return swaggerBundleConfiguration;
    }

    @JsonProperty("httpClient")
    public HttpClientConfiguration getHttpClientConfiguration(){
        return httpClient;
    }

    @JsonProperty("httpClient")
    public void setHttpClientConfiguration(HttpClientConfiguration httpClient){
        this.httpClient = httpClient;
    }


    @JsonProperty("database")
    public DataSourceFactory getDatabase(){
        return database;
    }

}


