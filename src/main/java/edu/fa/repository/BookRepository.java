package edu.fa.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.fa.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	Book findBookById(Integer id);

	List<Book> findAllByIsDelete(Boolean b);

	//@Query(value="select * from Book p where p.image =:keyword", nativeQuery = true)
	@Query(value="select * from Book p where p.name LIKE %:keyword% or p.des LIKE %:keyword%  ", nativeQuery = true)
	Set<Book> search(String keyword);


//@Query("select p from Book p where p.`name` LIKE %:keyword%")
//	@Query("SELECT m FROM Book m WHERE m.des LIKE %:keyword%")
//	List<Book> searchByNameContainingOrDesContaining(String keyword);

}
