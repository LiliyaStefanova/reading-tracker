package org.elstere.booktrkr.api.controllers;

import org.elstere.booktrkr.api.entities.outbound.AuthorOutbound;
import org.elstere.booktrkr.api.entities.inbound.AuthorInbound;
import org.elstere.booktrkr.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<Set<AuthorOutbound>> fetchAuthors(){
        Set<AuthorOutbound> authorResults =  this.authorService.getAllAuthors();
        return ResponseEntity.ok(authorResults);
    }

    @GetMapping("/author/{id}")
    @CrossOrigin(origins="*")
    @ResponseBody
    public ResponseEntity<AuthorOutbound> fetchAuthorById(@PathVariable UUID id){

        Optional<AuthorOutbound> maybeAuthor = this.authorService.getAuthorById(id);
        return ResponseEntity.of(maybeAuthor);
    }

    @GetMapping("/author")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public ResponseEntity<List<AuthorOutbound>>  searchByName(@RequestParam String searchTerm){

        List<AuthorOutbound> authorResults = this.authorService.searchByName(searchTerm);
        return ResponseEntity.ok(authorResults);
    }

    @PostMapping("/author")
    @CrossOrigin(origins="*")
    @ResponseBody
    public ResponseEntity<UUID> insertAuthor(AuthorInbound authorInbound){
        //TODO map exception to service exception response
            return ResponseEntity.ok(this.authorService.insertAuthor(authorInbound));

        }
}
