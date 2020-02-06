package br.com.dadpig.graphql.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.dadpig.graphql.entities.Book;

public interface BookRepository  extends MongoRepository<Book, String> {


}
