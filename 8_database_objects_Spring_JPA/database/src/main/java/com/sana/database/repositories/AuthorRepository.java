package com.sana.database.repositories;

import com.sana.database.domain.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
}

//✅ @Repository
//This annotation is part of the Spring Data and Spring Framework, and it's used to mark a DAO (Data Access Object) class as a Spring bean that's responsible for interacting with the database.
//🔍 What does it do?
// - Marks the class as a Spring-managed bean
//So Spring can automatically detect it and include it in the application context (like with @Component, @Service, etc.).
// - Translates exceptions
//Spring will automatically translate JPA/Hibernate-specific exceptions into Spring's DataAccessException hierarchy, making your exception handling more consistent.
