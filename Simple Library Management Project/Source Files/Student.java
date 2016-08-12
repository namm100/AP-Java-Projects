
public class Student extends Borrower {
    // STUDENTS CAN HAVE TWO BOOKS
    private int grade;
    private String officialClass;
    private Book[] books;
    private String lastName, firstName;
    public Student(String lastName, String firstName, int ID, String officialClass, int grade) {
        super(firstName + " " + lastName, ID);
        books = new Book[2];
        this.setOfficialClass(officialClass);
        this.setGrade(grade);
        this.lastName = lastName;
        this.firstName = firstName;
    }

    @Override
    public void borrow(Library library, Book book) {
        if (book == null) 
            System.out.println("Book not found!");
        else if (books[0] == null) {
            Book theBook = library.borrow(book);
            books[0] = theBook;
            System.out.println(getName() + " has borrowed " + book);
        } else if (books[1] == null) {
            Book theBook = library.borrow(book);
            books[1] = theBook;
            System.out.println(getName() + " has borrowed " + book);
        } else {
            System.out.println(getName() + " has reached the maximum size of books that can be borrowed");
        }
    }
    public Book getFirstBook() {
  return books[0];
 }
    public Book getSecondBook() {
  return books[1];
 }

    public String getOfficialClass() {
  return officialClass;
 }

    public void setOfficialClass(String officialClass) {
  this.officialClass = officialClass;
 }

    public int getGrade() {
  return grade;
 }

    public void setGrade(int grade) {
  this.grade = grade;
 }

    public void returnBook(Library library, int whichBook) {
        if (this.books[0] == null && this.books[1] == null) {
            System.out.println("no books found");
        } else if (whichBook == 0 && books[0] != null) {
            library.returnBook(books[0]);
            System.out.println(getName() + " has returned " + books[0]);
            books[0] = null;
        } else if (whichBook == 1 && books[1] != null) {
            library.returnBook(books[1]);
            System.out.println(getName() + " has returned " + books[1]);
            books[1] = null;
        } else {
            System.out.println("Unable to return book");
        }
    }

    public String getLastName() {
  return lastName;
 }

    public String getFirstName() {
  return this.firstName;
 }
    public String toString() {
   return getFirstName() + " " + getLastName() + " " + getID();
 }
}
