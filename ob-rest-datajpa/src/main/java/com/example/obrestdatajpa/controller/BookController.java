package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
    public class BookController {
// atributos
    private BookRepository bookRepository;
//constructores
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

//CRUD sobre la entidad de Book
//Buscar todos los libros (ista de libros)

    /**
     * http://localhost:8081/api/books
     *     @param //id
     *     @return
     *
     * */

    @GetMapping("/api/books")
    public List<Book> findAll (){
//recuperar y devolver los libros de base de datos
        return bookRepository.findAll();
    }
// Buscar un solo libro en base de datos segun su Id
    @GetMapping("/api/books/{id}")
    public Book findOneById( @PathVariable long id) {
        bookRepository.findById(id);
        Optional<Book> bookOpt = bookRepository.findById(id); //321567489

        //opcion 1
        // if (bookOpt.isPresent())
        //  return ResponseEntity.ok(bookOpt.get());
        //else
        //return ResponseEntity.notFound().build();
        return bookOpt.orElse(null);
    }
//crear un nuevo libro en base datos

    @PostMapping("/api/books")
    public Book create (@RequestBody Book book, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));
        //guardar el libro recibido por parametro en la base de datos
       return bookRepository.save(book);

    }

//Actualizar un libro existente en base de datos

//Borrar un libro en base de datos

}
