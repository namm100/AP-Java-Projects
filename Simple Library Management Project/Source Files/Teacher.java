
public class Teacher extends Borrower {

    Book book;
    public Teacher(String name, int ID) {
        super(name, ID);
        book = null;
    }

    @Override
    public void borrow(Library library, Book book) {
        if (book == null) 
            System.out.println("Cannot find book!");
        else if (this.book == null) {
            Book theBook = library.borrow(book);
            this.book = theBook;
            System.out.println(getName() + " has borrowed " + book);
        } else {
            System.out.println(getName() + " has reached the maximum size of books that can be borrowed");
        }
    }
 
    public void returnBook(Library library, int whichBook) {
        if (this.book == null) {
            System.out.println("no books found");
        } else {
            library.returnBook(this.book);
            System.out.println(getName() + " has returned " + book);
            book = null;
        }
    }

    public String toString() {
        return getName() + " " + getID();
   
    }
}
