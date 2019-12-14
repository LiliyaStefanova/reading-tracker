package org.elstere.booktrkr.api.controllers;

import org.elstere.booktrkr.dao.Author;
import org.elstere.booktrkr.api.entities.inbound.AuthorInbound;
import org.elstere.booktrkr.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    private AuthorController(AuthorService service){
        this.authorService = service;
    }

    @GetMapping("/author/all")
    @CrossOrigin(origins="*")
    @ResponseBody
    public ResponseEntity<Set<Author>> fetchAuthors(){
        Set<Author> authorResults =  this.authorService.getAllAuthors();
        return ResponseEntity.ok(authorResults);
    }

    @GetMapping("/author/{id}")
    @CrossOrigin(origins="*")
    @ResponseBody
    public ResponseEntity<Author> fetchAuthorById(@PathVariable UUID id){

        Optional<Author> maybeAuthor = this.authorService.getAuthorById(id);
        return ResponseEntity.of(maybeAuthor);
    }

    @GetMapping("/author")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public ResponseEntity<Author>  searchByName(@RequestParam String searchTerm){

        //TODO
        return ResponseEntity.ok(null);
    }

    @PostMapping("/author")
    @CrossOrigin(origins="*")
    @ResponseBody
    public ResponseEntity<UUID> insertAuthor(AuthorInbound authorInbound){
            return ResponseEntity.ok(this.authorService.insertAuthor(authorInbound));

        }
}
