
public class Book {
    private Status status;
    private String category;
    private String quality;
    private String title, author;
    private int ISBN;
    private Date inLibrary;
    private boolean isBorrowed;
    private Date dueDate;
   
    // ------------constructor------------
    public Book(String title, String author, int ISBN, String category, String quality, Date inLibrary) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.category = category.toLowerCase();
        this.quality = quality;
        status = Status.IN_STOCK;
        this.inLibrary = inLibrary;
        this.isBorrowed = false;
        this.dueDate = null;
     
    }
    // ---------------getters--------------
    public String getTitle() {
     return title;
   }
    public String getAuthor() {
    return author; 
   }
    public int getISBN() {
    return ISBN; 
   }
    public String getCategory() {
    return category; 
   }
    public String getQuality() {
    return quality;
   }
    public Status getStatus() {
     return status;
   }
    public Date getInLibrary() {
  return inLibrary;
   }
    public boolean isBorrowed() {
    return this.isBorrowed;
   }
    public Date getDueDate() {
    return this.dueDate;
   }
    // --------------setters--------------
    public void setQuality(String quality) {
     this.quality = quality;
   }
   
    public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
   }
    public void setStatus(Status status) {
    this.status = status;
   }
    // ------------ other methods -----------
    public boolean equals(Book b) {
        return (this.getISBN() == b.getISBN() && this.getStatus() == b.getStatus() &&
                b.getTitle().equalsIgnoreCase(this.getTitle()));
    }
    @Override
    public String toString() {
        return getTitle() + " " + getAuthor() + " " + getISBN() + " " + getCategory() + " " + getQuality();
    }
    public void borrow(Date dueDate) {
        this.dueDate = dueDate;
        this.isBorrowed = true;
        this.status = Status.IN_TRANSIT;
    }

}