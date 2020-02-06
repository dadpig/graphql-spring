package br.com.dadpig.graphql.resolver;



import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import br.com.dadpig.graphql.entities.Author;
import br.com.dadpig.graphql.entities.Book;
import br.com.dadpig.graphql.exception.BookNotFoundException;
import br.com.dadpig.graphql.repository.AuthorRepository;
import br.com.dadpig.graphql.repository.BookRepository;

public class Mutation implements GraphQLMutationResolver {
	
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public Mutation(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }
    
    public Author newAuthor(String firstName, String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);

        return authorRepository.save(author);
    }

    public Book newBook(String title, String isbn, Integer pageCount, String authorId) {
        Book book = new Book();
        book.setAuthor(new Author(authorId));
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setPageCount(pageCount != null ? pageCount : 0);

        return bookRepository.save(book);
    }
    
    public Book updateBookPageCount(Integer pageCount, String id) {
        Book book = bookRepository.findById(id).orElse(null);
        if(book == null) {
            throw new BookNotFoundException("The book to be updated was not found", id);
     
        }
        book.setPageCount(pageCount);

        return bookRepository.save(book);
    }
    
    public void deleteBook(Book book) {
    	bookRepository.delete(book);
    }
    
    public void deleteAuthor(Author author) {
    	authorRepository.delete(author);
    }
}