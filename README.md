# graphql-spring
Sample springboot app to test graphql with java.


to test just POST this json: 
{
	"query":"{findAllBooks  { id title isbn pageCount author{ id firstName lastName }}}"
}
to localhost:8080/graphql


save

mutation {
  createAuthor(author:{
   	firstName: "Dan",
	lastName: "Brown"
  }) {
    firstName,
  	lastName
  } 
}
