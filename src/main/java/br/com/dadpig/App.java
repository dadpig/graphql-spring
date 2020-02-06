package br.com.dadpig;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.dadpig.graphql.entities.Author;
import br.com.dadpig.graphql.entities.Book;
import br.com.dadpig.graphql.exception.GraphQLErrorAdapter;
import br.com.dadpig.graphql.repository.AuthorRepository;
import br.com.dadpig.graphql.repository.BookRepository;
import br.com.dadpig.graphql.resolver.Mutation;
import br.com.dadpig.graphql.resolver.Query;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;

@SpringBootApplication(scanBasePackages = {"br.com.dadpig"})
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo(AuthorRepository authorRepository, BookRepository bookRepository) {
		return (args) -> {
			Author author = new Author();
			author.setFirstName("Herbert");
			author.setLastName("Schildt");
			authorRepository.save(author);
			
			Book book = new Book();
			book.setAuthor(author);
			book.setTitle("Java: A Beginner's Guide, Sixth Edition");
			book.setIsbn("0071809252");
			book.setPageCount(728);
			
			bookRepository.save(book);
			
			
			
			Author author1 = new Author();
			author1.setFirstName("Joshua");
			author1.setLastName("Bloch");
			authorRepository.save(author1);
			
			Book book1 = new Book();
			book1.setAuthor(author);
			book1.setTitle("Effective Java (3rd  Edition) ");
			book1.setIsbn("B078H61SCH");
			book1.setPageCount(414);
			
			bookRepository.save(book1);
		};
	}
	
	@Bean
	public GraphQLErrorHandler errorHandler() {
		return new GraphQLErrorHandler() {
			@Override
			public List<GraphQLError> processErrors(List<GraphQLError> errors) {
				List<GraphQLError> clientErrors = errors.stream()
						.filter(this::isClientError)
						.collect(Collectors.toList());

				List<GraphQLError> serverErrors = errors.stream()
						.filter(e -> !isClientError(e))
						.map(GraphQLErrorAdapter::new)
						.collect(Collectors.toList());

				List<GraphQLError> e = new ArrayList<>();
				e.addAll(clientErrors);
				e.addAll(serverErrors);
				return e;
			}

			protected boolean isClientError(GraphQLError error) {
				return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
			}
		};
	}
	
	

	@Bean
	public Query query(AuthorRepository authorRepository, BookRepository bookRepository) {
		return new Query(authorRepository, bookRepository);
	}

	@Bean
	public Mutation mutation(AuthorRepository authorRepository, BookRepository bookRepository) {
		return new Mutation(authorRepository, bookRepository);
	}
}