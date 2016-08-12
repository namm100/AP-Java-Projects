
import java.io.*;
import java.util.ArrayList;
public class LibraryTest {

    private enum TypeBorrower {
        STUDENT, TEACHER;
    }
    static void println(String a) { System.out.println(a); }
    static void print(String a) { System.out.print(a); }

    public static Date getCurrentDate() {
        int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
        int day = java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_MONTH);
        return new Date(month + 1, day, year);
    }

    public static Book readSingleBook(String line) throws IndexOutOfBoundsException {
        int indexSpace = 0;
        indexSpace = line.indexOf(',', indexSpace);
        String isbnString = line.substring(0, indexSpace);
        int ISBN = Integer.parseInt(isbnString);
        line = line.substring(indexSpace + 1);
        indexSpace = 0;
        indexSpace = line.indexOf(',', indexSpace);
        String title = line.substring(0, indexSpace);
        line = line.substring(indexSpace + 1);
        indexSpace = 0;
        indexSpace = line.indexOf(',', indexSpace);
        String author = line.substring(0, indexSpace);
        line = line.substring(indexSpace + 1);
        indexSpace = 0;
        indexSpace = line.indexOf(',', indexSpace);
        String category = line.substring(0, indexSpace);
        line = line.substring(indexSpace + 1);
        // indexSpace = 0;
        String quality = line;
        return new Book(title,author,ISBN,category,quality,getCurrentDate());
    }
    public static ArrayList<Book> readBooks(String file) throws IOException {

        BufferedReader inputFile = new BufferedReader(new FileReader(file));
        String line;
        ArrayList<Book> bookArr = new ArrayList<Book>();
        while ((line = inputFile.readLine()) != null) {
            if (line.equals("")) continue;
   
            int indexSpace = 0;
            indexSpace = line.indexOf(',', indexSpace);
            String isbnString = line.substring(0, indexSpace);
            int ISBN = Integer.parseInt(isbnString);
            line = line.substring(indexSpace + 1);
            indexSpace = 0;
            indexSpace = line.indexOf(',', indexSpace);
            String title = line.substring(0, indexSpace);
            line = line.substring(indexSpace + 1);
            indexSpace = 0;
            indexSpace = line.indexOf(',', indexSpace);
            String author = line.substring(0, indexSpace);
            line = line.substring(indexSpace + 1);
            indexSpace = 0;
            indexSpace = line.indexOf(',', indexSpace);
            String category = line.substring(0, indexSpace);
            line = line.substring(indexSpace + 1);
            // indexSpace = 0;
            String quality = line;
            bookArr.add(new Book(title, author, ISBN, category,quality, getCurrentDate()));
        }
        inputFile.close();
        return bookArr;
    }

    public static ArrayList<Student> readStudents(String file)throws IOException {
        String line;
        ArrayList<Student> students = new ArrayList<Student>();
        BufferedReader inputFile2 = new BufferedReader(new FileReader(file));
  
        while ((line = inputFile2.readLine()) != null) {
            if (line.equals("")) continue;
            int indexSpace = 0;
   
            indexSpace = line.indexOf(',',indexSpace);
            String idString = line.substring(0, indexSpace);
            int idInt = Integer.parseInt(idString);
            line = line.substring(indexSpace + 1);
            indexSpace = 0;
   
            indexSpace = line.indexOf(',', indexSpace);
            String lastName = line.substring(0, indexSpace);
            line = line.substring(indexSpace + 1);
            indexSpace = 0;

            indexSpace = line.indexOf(',', indexSpace);
            String firstName = line.substring(0, indexSpace);
            line = line.substring(indexSpace + 1);
            indexSpace = 0;
   
            indexSpace = line.indexOf(',', indexSpace);
            String gradeString = line.substring(0, indexSpace);
            int grade = Integer.parseInt(gradeString);
            line = line.substring(indexSpace + 1);
            // indexSpace = 0;
   
   
            String offClass = line;
   
            students.add(new Student(lastName, firstName, idInt, offClass, grade));
   
        }
        inputFile2.close();
        return students;
    }
    public static ArrayList<Teacher> readTeachers(String file)throws IOException {
        String line;
        BufferedReader inputFile3 = new BufferedReader(new FileReader(file));
        ArrayList<Teacher> teachers = new ArrayList<Teacher>();
        while ((line = inputFile3.readLine()) != null) {
            if (line.equals("")) continue;
   
            int indexSpace = 0;
   
            indexSpace = line.indexOf(',', indexSpace);
            String idString = line.substring(0, indexSpace);
            line = line.substring(indexSpace + 1);
            // indexSpace = 0;
            int idInt = Integer.parseInt(idString);
            String name = line;
            teachers.add(new Teacher(name, idInt));
   
        }
        inputFile3.close();
        return teachers;
    }
    public static void main(String[] args) throws IOException {
        final boolean IS_DEBUGGING = false;

        java.util.Scanner scan = new java.util.Scanner(System.in);
        String bookFileName, studentFileName, teacherFileName;
        if (!IS_DEBUGGING) {
            println("Welcome to Simple Library Manegement Project");
            println("Make sure your files are comma separated with no spaces in between the comma and the word");
            print("Enter input file name(books): ");
            bookFileName = scan.nextLine().trim();
            print("Enter input file name(students): ");
            studentFileName = scan.nextLine().trim();
            print("Enter input file name(teachers): ");
            teacherFileName = scan.nextLine().trim();
        } else {
            bookFileName = "bookList.txt";
            studentFileName = "studentList.txt";
            teacherFileName = "teacherList.txt";
        }
        Library library = new Library(readBooks(bookFileName));


        ArrayList<Student> students = readStudents(studentFileName);


        ArrayList<Teacher> teachers = readTeachers(teacherFileName);

        library.printBooks();
        println("\nStudents: ");
        for (int i = 0; i < students.size(); i++) {
            println(students.get(i).toString());
        }
        println("\nTeachers: ");
        for (int i = 0; i < teachers.size(); i++) {
            println(teachers.get(i).toString());
        }
        println("");
        // start the program
        while (true) {
            println("Alright, are you a Borrower or a Librarian?");
            print("Borrower (b) Librarian (l) Exit Program (e): ");
            String choice = scan.nextLine().toLowerCase();
            if (choice.equals("e") || choice.equals("exit")) {
                break;
            }
            if (choice.equals("b") || choice.equals("borrower")) {
                // borrower

                println("Are you a student or a teacher?");
                print("Student (s) Teacher (t): ");
                String choice2 = scan.nextLine().toLowerCase();
                println("Enter your ID");
                println("It must match your teacher and student list");
                print("ID: ");
                int studOrTeachID = scan.nextInt();
                scan.nextLine();
                TypeBorrower typeBorrower;
                int indexOFBorrower = -1;

                if (choice2.equals("s") || choice2.equals("student")) {
                    // student code
                    typeBorrower = TypeBorrower.STUDENT;
                    for (int i = 0; i < students.size(); i++) {

                        if (students.get(i).getID() == studOrTeachID) {
                            indexOFBorrower = i;
                            break;
                            // End Here
                        }
                    }
                } else {
                    // teacher code
                    typeBorrower = TypeBorrower.TEACHER;
                    for (int i = 0; i < teachers.size(); i++) {
                        if (teachers.get(i).getID() == studOrTeachID) {
                            indexOFBorrower = i;
                            break;
                        }
                    }
                }

                if (!(indexOFBorrower == -1)) {
                    // log in here
                    // typeBorrower variable stores this
                    // indexOfBorrower stores index in ArrayList
                    if (typeBorrower == TypeBorrower.TEACHER)
                        println("Welcome teacher: " + teachers.get(indexOFBorrower));
                    else
                        println("Welcome student: " + students.get(indexOFBorrower));


                    int borrowerChoice = 0;
                    do {
                        // borrower loop
                        println("Choose options: ");
                        println("1 is to browse books by category");
                        println("2 is to check availability of a book");
                        println("3 is to check a book out");
                        println("4 is to return a book");
                        println("5 is to change borrower/librarian");
                        print("your choice: ");
                        borrowerChoice = scan.nextInt();
                        scan.nextLine();
                        switch (borrowerChoice) {
                            case 1:
                                // browse books by category
                                println("Browse books by category");
                                print("Category: ");
                                String theCategory = scan.nextLine();
                                ArrayList<Book> booksInCategory = library.getBooksInCategory(theCategory);
                                println("\nBooks in " + theCategory + ": ");
                                for (int i = 0; i < booksInCategory.size(); i++) {
                                    System.out.println(booksInCategory.get(i));
                                }
                                print("\n");
                                break;
                            case 2:
                                // print availability of book
                                println("Check a book");
                                print("ISBN (1) or Title (2): ");
                                int checkChoice = scan.nextInt();
                                scan.nextLine();
                                if (checkChoice == 1) {
                                    // check book based on ISBN
                                    print("Enter ISBN: ");
                                    int enteredISBN = scan.nextInt();
                                    Book b = library.findByISBN(enteredISBN);
                                    if (b == null) {
                                        println("book not found");
                                    } else {
                                        Status statusBook = b.getStatus();
                                        println("Book: " + b);
                                        switch (statusBook) {
                                            case IN_TRANSIT:
                                                println("Book is in transit");
                                                break;
                                            case IN_STOCK:
                                                println("Book is in stock");
                                                break;
                                        }
                                    }
                                } else {
                                    // check book based on Title

                                    print("Enter title: ");
                                    String enteredTitle = scan.nextLine();
                                    Book b = library.findByTitle(enteredTitle);
                                    if (b == null) {
                                        println("book not found");
                                    } else {
                                        Status statusBook = b.getStatus();
                                        println("Book: " + b);
                                        switch (statusBook) {
                                            case IN_TRANSIT:
                                                println("Book is in transit");
                                                break;
                                            case IN_STOCK:
                                                println("Book is in stock");
                                                break;
                                        }
                                    }
                                }
                                break;
                            case 3:
                                // check a book out by ISBN or Book Name
                                println("Check out a book");
                                print("ISBN(1) or Title(2): ");
                                int checkOutChoice = scan.nextInt(); scan.nextLine();
                                int checkOutISBN = 0; String checkOutTitle = "";
                                if (checkOutChoice == 1) {
                                    // by ISBN
                                    print("ISBN: ");
                                    checkOutISBN = scan.nextInt(); scan.nextLine();
                                } else {
                                    print("Title: ");
                                    checkOutTitle = scan.nextLine();
                                    // by Title
                                }
                                if (typeBorrower == TypeBorrower.TEACHER && checkOutChoice != 1) {
                                    // teacher with title
                                    Teacher theTeacher = teachers.get(indexOFBorrower);
                                    Book theBook = library.findByTitle(checkOutTitle);
                                    theTeacher.borrow(library, theBook);
                                    teachers.set(indexOFBorrower,theTeacher);
                                }
                                if (typeBorrower == TypeBorrower.TEACHER && checkOutChoice == 1) {
                                    // teacher with ISBN
                                    Teacher theTeacher = teachers.get(indexOFBorrower);
                                    Book theBook = library.findByISBN(checkOutISBN);
                                    theTeacher.borrow(library, theBook);
                                    teachers.set(indexOFBorrower, theTeacher);
                                }
                                if (typeBorrower == TypeBorrower.STUDENT && checkOutChoice != 1) {
                                    // student with title
                                    Student theStudent = students.get(indexOFBorrower);
                                    Book theBook = library.findByTitle(checkOutTitle);
                                    theStudent.borrow(library, theBook);
                                    students.set(indexOFBorrower, theStudent);
                                }
                                if (typeBorrower == TypeBorrower.STUDENT && checkOutChoice == 1) {
                                    // student with ISBN
                                    Student theStudent = students.get(indexOFBorrower);
                                    Book theBook = library.findByISBN(checkOutISBN);
                                    theStudent.borrow(library, theBook);
                                    students.set(indexOFBorrower, theStudent);
                                }

                                break;

                            case 4:
                                // return a book
                                println("Return a book");
                                if (typeBorrower == TypeBorrower.STUDENT) {
                                    // student returning a book
                                    Student theStudent = students.get(indexOFBorrower);

                                    System.out.println("Which book would you like to return.");
                                    System.out.println("Books (1):");
                                    System.out.println(theStudent.getFirstBook());
                                    System.out.println("Books (2): ");
                                    System.out.println(theStudent.getSecondBook());
                                    print("Which one: ");
                                    int returnStudentChoice = scan.nextInt(); scan.nextLine();
                                    theStudent.returnBook(library, returnStudentChoice - 1);
                                    students.set(indexOFBorrower, theStudent);

                                }
                                if (typeBorrower == TypeBorrower.TEACHER) {
                                    // teacher returning a book
                                    Teacher theTeacher = teachers.get(indexOFBorrower);
                                    theTeacher.returnBook(library,0);
                                    teachers.set(indexOFBorrower, theTeacher);
                                }

                                break;

                            case 5:
                                break;
                            default:
                                println("didn't choose a right choice");

                        }

                    } while (!(borrowerChoice == 5));
                } else {
                    // couldn't log in
                    println("Unable to log in");
                }
            } else {
                // librarian code

                int libChoice = 0;
                do {

                    println("Choose options:");
                    println("1 is enter new book");
                    println("2 is remove a book");
                    println("3 is to check borrowing history");
                    println("4 is to change borrower/librarian");
                    print("your choice: ");
                    libChoice = scan.nextInt();
                    scan.nextLine();
                    switch (libChoice) {
                        case 1:
                            // enter a new book
                            BufferedWriter writer = new BufferedWriter(new FileWriter(bookFileName, true));
                            // writer.newLine();
                            println("Entering new book...");
                            print("ISBN: ");
                            int newBookISBN = scan.nextInt();
                            scan.nextLine(); // to help make this work
                            print("Title: ");
                            String newBookTitle = scan.nextLine();
                            print("Author: ");
                            String newBookAuthor = scan.nextLine();
                            print("Category: ");
                            String newBookCategory = scan.nextLine();
                            print("Quality: ");
                            String newBookQuality = scan.nextLine();
                            Book theNewBook = new Book(newBookTitle, newBookAuthor, newBookISBN, newBookCategory, newBookQuality, getCurrentDate());
                            library.addBook(theNewBook);
                            writer.newLine();
                            writer.write(newBookISBN + "," + newBookTitle + "," + newBookAuthor + "," + newBookCategory + "," + newBookQuality);
                            writer.close();
                            break;
                        case 2:
                            // remove a book
                            println("Remove a book");
                            print("What is the ISBN number of the book: ");
                            int bookRemoveISBN = scan.nextInt(); scan.nextLine();
                            if (library.findByISBN(bookRemoveISBN) == null) {
                                System.out.println("Book not found");
                                break;
                            }
                            if (library.findByISBN(bookRemoveISBN).getStatus() == Status.IN_TRANSIT) {
                                System.out.println("Sorry that book is in transit");
                                System.out.println("Unable to remove it");
                                break;
                            }
                            boolean successful;

                            BufferedReader readBooks2 = new BufferedReader(new FileReader(bookFileName));

                            ArrayList<String> linesForNewFile = new ArrayList<String>();
                            String currLine;

                            // read all lines but the line that has the book
                            while ((currLine = readBooks2.readLine()) != null) {
                                Book currBook;
                                try {
                                    currBook = readSingleBook(currLine);
                                    if (currBook.getISBN() == bookRemoveISBN) {
                                        continue;
                                    }
                                } catch (IndexOutOfBoundsException e) {
                                    continue;
                                }

                                linesForNewFile.add(currLine);
                            }
                            // write all the lines read
                            readBooks2.close();

                            BufferedWriter removeWriter = new BufferedWriter(new FileWriter(bookFileName));

                            for (int i = 0; i < linesForNewFile.size(); i++) {
                                removeWriter.write(linesForNewFile.get(i));
                                removeWriter.newLine();
                            }

                            successful = library.removeBook(bookRemoveISBN);
                            removeWriter.close();


                            println((successful) ? "The book has been removed" : "The book cannot be removed");
                            break;
                        case 3:
                            // check a book
                            println("Check a book");
                            print("ISBN (1) or Title (2): ");
                            int checkChoice = scan.nextInt();
                            scan.nextLine();
                            if (checkChoice == 1) {
                                // check book based on ISBN
                                print("Enter ISBN: ");
                                int enteredISBN = scan.nextInt();
                                Book b = library.findByISBN(enteredISBN);
                                if (b == null) {
                                    println("book not found");
                                } else {
                                    Status statusBook = b.getStatus();
                                    println("Book: " + b);
                                    switch (statusBook) {
                                        case IN_TRANSIT:
                                            println("Book is in transit");
                                            break;
                                        case IN_STOCK:
                                            println("Book is in stock");
                                            break;
                                    }
                                }
                            } else {
                                // check book based on Title

                                print("Enter title: ");
                                String enteredTitle = scan.nextLine();
                                Book b = library.findByTitle(enteredTitle);
                                if (b == null) {
                                    println("book not found");
                                } else {
                                    Status statusBook = b.getStatus();
                                    println("Book: " + b);
                                    switch (statusBook) {
                                        case IN_TRANSIT:
                                            println("Book is in transit");
                                            break;
                                        case IN_STOCK:
                                            println("Book is in stock");
                                            break;
                                    }
                                }
                            }
                            break;
                        case 4:
                            break;
                        default:
                            println("didn't choose a right choice");
                    }

                } while (!(libChoice == 4));
            }

        }
        println("Thanks for using the system!");
        scan.close();
    }
}
