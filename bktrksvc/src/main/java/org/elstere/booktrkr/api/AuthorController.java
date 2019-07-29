package org.elstere.booktrkr.api;

import org.elstere.booktrkr.dao.Author;
import org.elstere.booktrkr.dao.AuthorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuthorController {

    private final AuthorRepository authorRepository;

    private AuthorController(AuthorRepository repository){
        this.authorRepository = repository;
    }

    @GetMapping("/author/all")
    @CrossOrigin(origins="*")
    @ResponseBody
    public List<Author> fetchAuthors(){
        return this.authorRepository.findAll();
    }

    @GetMapping("/author/{id}")
    @CrossOrigin(origins="*")
    @ResponseBody
    public Author fetchAuthorById(@PathVariable long id){
        return this.authorRepository.findById(id);
    }

    @PostMapping("/author")
    @CrossOrigin(origins="*")
    @ResponseBody
    public long insertAuthor(Author author){
        Author newAuthor = this.authorRepository.save(author);
        if(newAuthor!=null){
            return newAuthor.getId();
        } else{
            return 0L;
        }
    }
}
