Chinook Manipulation


Christos Giannikis – Tilemachos Ziogas


This is the second Assignment of the BackEnd section of Noroff FullStack course. It’s a Spring Boot Application
that, using the given Chinook database, processes the asked entries of the database’s tables with the help of
Queries and produces the expected results.
We used four branches while working on this project, one for each of us, one secondary(?) that is used for
merging the commits from our respective branches, working basically as a collective back-up branch and lastly
the main branch which is the final product, containing the finished result of both Appendix A and Appendix B
of the assignment.
From the first moment, we worked as a team, meaning that we split the workload equally and after an agreed
upon time period we discussed what we have come up with and how do we proceed, and of course the results
of each other’s result of work.
The project below consists of the main SpringBootApplication Class and three packages that are described
below:

1. The models package that contains Record Classes Customer, CustomerCountry, CustomerSpender and
   CustomerGenre which are used for accessing the respective fields from the database.
2. The repositories package that contains the CRUDrepository (parent) and CustomerRepository (child)
   interfaces and the CustomerRepositoryImpl Class which uses the @Repository decorator and, itself,
   contains the requested methods.
3. The runner package that contains a single Class (named PgAppRunner), using the @Component, thus is
   responsible for running the entire project.