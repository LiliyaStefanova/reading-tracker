package org.elstere.booktrkr.api;

import lombok.extern.slf4j.Slf4j;
import org.elstere.booktrkr.dao.Genre;
import org.elstere.booktrkr.model.GenreInbound;
import org.elstere.booktrkr.model.GenreOutbound;
import org.elstere.booktrkr.service.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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
    public Set<GenreOutbound> fetchGenres(){
        return this.service.getAllGenres();
    }

    @GetMapping("/genre/{id}")
    @CrossOrigin(origins="*")
    @ResponseBody
    public GenreOutbound fetchGenreById(@PathVariable long id){
        return this.service.getGenreById(id);
    }

    @GetMapping("/genre/{id}/readingEntries")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public List<String> fetchTitlesInGenre(@PathVariable long id){
        return this.service.getAllReadingEntriesForGenre(id);
    }

    @PostMapping("/genre")
    @CrossOrigin(origins="*")
    @ResponseBody
    public long insertGenre(@RequestBody GenreInbound genre){
        log.info("GenreInbound is: {}", genre.toString());
        Genre newGenre = this.service.insertGenre(genre);
        if(newGenre!=null){
            return newGenre.getId();
        } else{
            return 0L;
        }
    }
}
