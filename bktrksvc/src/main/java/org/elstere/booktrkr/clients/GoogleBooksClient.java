package org.elstere.booktrkr.clients;

import org.elstere.booktrkr.api.entities.inbound.GoogleClientVolumeInfo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class GoogleBooksClient {

    private final WebClient webClient;

    public GoogleBooksClient(){

        //TODO token interceptors and metric ClientHttpRequestInterceptors can be added here
        this.webClient = WebClient.builder()
                .baseUrl("")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public ResponseEntity<List<GoogleClientVolumeInfo>> searchForVolumes(String searchTerm){

        WebClient.RequestHeadersUriSpec getRequest = webClient.get();
        WebClient.RequestHeadersSpec spec = getRequest.uri("/additionalUrl").header("additional_header", "value");

        //TODO

        return null;
    }




}
