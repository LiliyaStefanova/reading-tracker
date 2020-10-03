package org.elstere.reading.tracker.api.controllers;

import lombok.extern.slf4j.Slf4j;
import org.elstere.reading.tracker.api.entities.inbound.GenreInbound;
import org.elstere.reading.tracker.api.entities.outbound.GenreOutbound;
import org.elstere.reading.tracker.service.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
public class GenreController {

    private final GenreService service;

    public GenreController(GenreService service){
        this.service = service;
    }

    @GetMapping("/genre/all")
    @CrossOrigin(origins="*")
    @ResponseBody
    public ResponseEntity fetchGenres(){
        return ResponseEntity.ok(this.service.getAllGenres());
    }

    @GetMapping("/genre/{id}")
    @CrossOrigin(origins="*")
    @ResponseBody
    public ResponseEntity fetchGenreById(@PathVariable UUID id){
        Optional<GenreOutbound> genreOutbound = this.service.getGenreById(id);
        if(genreOutbound.isPresent()){
            return ResponseEntity.ok(genreOutbound);
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/genre/{id}/readingEntries")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public ResponseEntity fetchTitlesInGenre(@PathVariable UUID id){
        List<String> results = this.service.getAllReadingEntriesForGenre(id);
        return ResponseEntity.ok(results);
    }

    @PostMapping("/genre")
    @CrossOrigin(origins="*")
    @ResponseBody
    public ResponseEntity insertGenre(@RequestBody GenreInbound genre){
        log.info("GenreInbound is: {}", genre.toString());
        Optional<GenreOutbound> newGenre = this.service.insertGenre(genre);
        UUID id = newGenre.map(GenreOutbound::getId).orElse(new UUID(0, 0));
        return ResponseEntity.ok(id);
    }
}
