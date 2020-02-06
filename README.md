# graphql-spring
Sample springboot app to test graphql with java.


test POSTing this json: 
{
	"query":"{findAllBooks  { id title isbn pageCount author{ id firstName lastName }}}"
}
to localhost:8080/graphql
