package org.elstere.booktrkr.api;

import lombok.extern.slf4j.Slf4j;
import org.elstere.booktrkr.model.GenreInbound;
import org.elstere.booktrkr.model.GenreOutbound;
import org.elstere.booktrkr.service.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
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
    public ResponseEntity fetchGenreById(@PathVariable long id){
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
    public ResponseEntity fetchTitlesInGenre(@PathVariable long id){
        List<String> results = this.service.getAllReadingEntriesForGenre(id);
        return ResponseEntity.ok(results);
    }

    @PostMapping("/genre")
    @CrossOrigin(origins="*")
    @ResponseBody
    public ResponseEntity insertGenre(@RequestBody GenreInbound genre){
        log.info("GenreInbound is: {}", genre.toString());
        Optional<GenreOutbound> newGenre = this.service.insertGenre(genre);
        long id = newGenre.map(GenreOutbound::getId).orElse(0L);
        return ResponseEntity.ok(id);
    }
}
