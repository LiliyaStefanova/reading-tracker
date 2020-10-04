package org.elstere.reading.tracker.api.controllers;

import lombok.extern.slf4j.Slf4j;
import org.elstere.reading.tracker.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class AuthorControllerTest {

    @InjectMocks
    private AuthorController uut;

    @Mock
    private AuthorService authorService;

    @Test
    public void fetchAllAuthorsReturnsOKWithResult(){

    }

    @Test
    public void fetchAllAuthorsReturnsOKWithNoResult(){

    }

    @Test
    public void getAuthorByIdReturnsOKWithResult(){

    }

    @Test
    public void getAuthorByIdReturnsNotFound(){

    }

    


}
