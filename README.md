# graphql-spring
Sample springboot app to test graphql with java.


to test just POST this json: 
{
	"query":"{findAllBooks  { id title isbn pageCount author{ id firstName lastName }}}"
}
to localhost:8080/graphql


save using GRAPHQL Beta

mutation {
  createAuthor(author:{
   	firstName: "Dan",
	lastName: "Brown"
  }) {
    firstName,
  	lastName
  } 
}

simple query:

query{ 
	findAllBooks { id title isbn pageCount 
                        author{ id firstName lastName }
                    }
    }

query with parameters:

query{ 
   findBooks(book: {
        title: null,
        isbn: "B078H61SCH",
        pageCount: null})
        {id title isbn pageCount author{ id firstName lastName }}
    }
