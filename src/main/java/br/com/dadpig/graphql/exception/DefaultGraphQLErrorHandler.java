package br.com.dadpig.graphql.exception;

import java.util.List;

import graphql.GraphQLError;
import graphql.servlet.GenericGraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DefaultGraphQLErrorHandler implements GraphQLErrorHandler {
    // ...

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        final List<GraphQLError> clientErrors = filterGraphQLErrors(errors);
        if (clientErrors.size() < errors.size()) {

            // Some errors were filtered out to hide implementation - put a generic error in place.
            clientErrors.add(new GenericGraphQLError("Internal Server Error(s) while executing query"));

            errors.stream()
                .filter(error -> !isClientError(error))
                .forEach(error -> {
                    if(error instanceof Throwable) {
                        log.error("Error executing query!", (Throwable) error);
                    } else {
                        log.error("Error executing query ({}): {}", error.getClass().getSimpleName(), error.getMessage());
                    }
                });
        }

        return clientErrors;
    }

	private boolean isClientError(GraphQLError error) {
		// TODO Auto-generated method stub
		return false;
	}

	private List<GraphQLError> filterGraphQLErrors(List<GraphQLError> errors) {
		// TODO Auto-generated method stub
		return null;
	}

    // ...
}