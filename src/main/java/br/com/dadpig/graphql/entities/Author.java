package br.com.dadpig.graphql.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "author")
@Data
@AllArgsConstructor
public class Author {

	@Id
	private String id;
	
	private String firstName;
	
	private String lastName;

	public Author() {
	}
	
	public Author(String id) {
		this.id = id;
	}
}
