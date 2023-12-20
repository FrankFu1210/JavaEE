package ch05.many2many._01.anno.model;

import java.util.*;
import javax.persistence.*;

@Entity(name = "ch05_mm1_Author")
@Table(name = "ch05_mm1_Author") 
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AUTHOR_ID", unique = true, nullable = false)
	private Integer authorId;
	
	@Column(name = "AUTHOR_Name")
	private String authorName;
	
	@ManyToMany(mappedBy = "authors")
	private Set<Book> books = new HashSet<Book>(0);

	public Author() { }

	public Author(String authorName) {
		this.authorName = authorName;
	}

	
	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	
	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	
	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", authorName=" + authorName  + "]";
	}
}