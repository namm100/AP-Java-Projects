
import java.util.ArrayList;
public class Library {
    ArrayList<Book> books;
 


 
    // ------------- constructors ----------
    public Library(Book[] b) {
        books = new ArrayList<Book>();
        for (int i = 0; i < b.length; i++) {
            books.add(b[i]);
        }


    }
    public Library(ArrayList<Book> b) {
        books = new ArrayList<Book>();
        books = b;


    }
    public Library() {
        books = new ArrayList<Book>();


    }
 
    public void addBook(Book b) {
        books.add(b);

    }
 
    public boolean removeBook(int ISBN) {
        int indexOfBook = -1;
        Book b = null;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getISBN() == ISBN) {
                b = books.get(i);
                indexOfBook = i;
            }
        }
        if (b == null) return false;
        else {
            books.remove(indexOfBook);
            return true;
        }
  
    }
    public int totalBooks() {
  return books.size();
 }
 
    // ------------- find methods ------------
    public Book findByISBN(int ISBN) {
        boolean isFound = false;
        int indexOfBook = 0;
        for (int i = 0; i < books.size(); i ++) {
            if (books.get(i).getISBN() == ISBN) {
                isFound = true;
                indexOfBook = i;
            }
        }
        return (isFound)?books.get(indexOfBook):null;
    }
 
    public Book findByTitle(String title) {
        boolean isFound = false;
        int indexOfBook = 0;
        for (int i = 0; i < books.size(); i ++) {
            if (books.get(i).getTitle().equals(title)) {
                isFound = true;
                indexOfBook = i;
            }
        }
        return (isFound)?books.get(indexOfBook):null;
    }
    // --------------- borrow methods ---------
    public Book borrow(Book book) {
        Book bookWithISBN = findByISBN(book.getISBN());

        if (book.equals(bookWithISBN) && bookWithISBN.getStatus() == Status.IN_STOCK) {
            books.get(books.indexOf(bookWithISBN)).setStatus(Status.IN_TRANSIT);
            return bookWithISBN;
        } else {
            // bookWithISBN is null
            System.out.println("Book Not Found Sorry");
            return null;
        }
    }

 
    public ArrayList<Book> getBooksInCategory(String category) {
        ArrayList<Book> theBookers = new ArrayList<Book>();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getCategory().equalsIgnoreCase(category))
                theBookers.add(books.get(i));
        }
        return theBookers;
    }
    public void printBooks() {
        System.out.println("Books:");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i));
        }
    }
    public void returnBook(Book book) {
        book = findByISBN(book.getISBN());
        if (book == null || book.getStatus() == Status.IN_STOCK) {
            System.out.println("Book not found or in stock");
        } else {

            books.get(books.indexOf(book)).setStatus(Status.IN_STOCK);
            System.out.println(book + " has been returned");
        }
    }
}
