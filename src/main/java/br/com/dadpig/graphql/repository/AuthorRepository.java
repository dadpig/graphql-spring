package br.com.dadpig.graphql.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.dadpig.graphql.entities.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {


}
