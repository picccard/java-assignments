/**

	Title:         Book.java
	Date:          20.03.2018
    Author:        Else Lervik
	Translator:    Eskil Uhlving Larsen

*/

/**
 * Book.java
 *
 */
public class Book {

  private final String isbn;
  private final String title;
  private final String author;

/**
 * Constructor:
 * Isbn, title and author.
 */
  public Book(String isbn, String title, String author) {
    this.isbn = isbn;
    this.title = title;
    this.author = author;
  }

  public String getIsbn() {
    return isbn;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public String toString() {
    return isbn + ": " + author + ", " + title;
  }
}
