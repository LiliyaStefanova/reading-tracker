package org.elstere.booktrkr.api;

import org.elstere.booktrkr.dao.Author;
import org.elstere.booktrkr.dao.AuthorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AuthorController {

    private final AuthorRepository authorRepository;

    private AuthorController(AuthorRepository repository){
        this.authorRepository = repository;
    }

    @GetMapping("/authors/all")
    @ResponseBody
    public List<Author> fetchAuthors(){
        return this.authorRepository.findAll();
    }

    @GetMapping("/author/{id}")
    @ResponseBody
    public Author fetchAuthorById(@PathVariable long id){
        return this.authorRepository.findById(id);
    }

    @PostMapping("/author")
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
