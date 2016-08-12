
public abstract class Borrower {

    private String name;
    private int ID;
 
    public Borrower(String name, int ID) {
        this.name = name;
        this.ID = ID;
    }
 
    public String getName() {
  return name;
 }
    public int getID() {
  return ID;
 }
    public abstract void borrow(Library library,Book book);

    public abstract void returnBook(Library library,int whichBook);
}
