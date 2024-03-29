// Name : Pallav Regmi
// CS152 project
// Assignment : Library
// Prof. Brooke

public class Library {

    /**
     * Unique books in the library.
     */
    private Book[] books;

    /**
     * Number of copies for each book.
     */
    private int[] copies;

    /**
     * Number of copies currently on the shelf (checked in) for each book.
     */
    private int[] checkedIn;

    /**
     * Number of unique books in the library.
     */
    private int numBooks;

    /**
     * Construct a new empty Library.
     */
    public Library(int librarySize) {
        books = new Book[librarySize];
        copies = new int[librarySize];
        checkedIn = new int[librarySize];
        numBooks = 0;
    }

    /**
     * Get the number of total copies of all books that exist in the
     * library.
     *
     * @return Total number of copies in the library.
     */

    public int getTotalCopies() {
        // to get the total number of copies
        int total = 0;
        for (int i = 0; i < numBooks; i++) {
            total += copies[i];
        }
        return total;
    }


    public int getNumCheckedOut() {
        // to get the number of the checkedout  copies
        int outCopies = 0;
        for (int i = 0; i < numBooks; i++) {
            outCopies += checkedIn[i];
        }
        return outCopies;
    }


    public String getStatus() {
        // to get the status of the books in the library
        return "Total unique books: "+numBooks+
                "\n" +"Total number of copies: "+getTotalCopies()
                +"\n"+"Total checked out: "+getNumCheckedOut();
    }


    public void addBook(Book b) {
        // to add the book in the library
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) {
                books[i] = b; copies[i] = 1;numBooks++;
                break;
            } else if (books[i].equals(b)) {
                copies[i]++;
                break;
            }
        }
    }

    public void addBooks(Book[] newBooks) {
        // to add the new books in the library
        for (int i = 0; i < newBooks.length; i++) {
            books[numBooks] = newBooks[i];
            copies[numBooks]++;
            numBooks++;
        }
    }

    /**
     * Checks out a book from the library if possible.
     *
     * @param b Book to check out.
     * @return String denoting success or failure.
     */
    public String checkOut(Book b) {
        // to check out the book from library
        String status = "Book not found.";
        for (int i = 0; i < numBooks; i++) {
            if (books[i].equals(b)) {
                if (copies[i] > checkedIn[i]) {
                    status = "Checked out!";
                    checkedIn[i]++;
                    break;
                } else {
                    status = "All out of copies.";
                    break;
                }
            }
        }
        return status;
    }

    public String checkIn(Book b) {
        // to check in the books in the library
        String status = "Book not found.";
        for (int i = 0; i < numBooks; i++) {
            if (books[i].equals(b)) {
                if (checkedIn[i] > 0) {
                    status = "Checked in!";
                    checkedIn[i]--;
                    break;
                } else {
                    status = "All of our copies are already checked in.";
                    break;
                }
            }
        }
        return status;
    }


    public String toString() {
        // to get the entire library status and collection
        String toRepresent;
        toRepresent = "";
        for (int i = 0; i < numBooks; i++) {
            toRepresent = toRepresent + i + ". " + books[i] + " : " +
                    (copies[i] - checkedIn[i]) + "/" + copies[i] + "\n";
        }
        toRepresent = toRepresent + "\n" + getStatus();
        return toRepresent;
    }


    public int numBooksByAuthor(Author a) {
        // to get the number of the unique book
        int uniqueBook=0;
        for(int i=0; i<numBooks;i++){
            if(books[i].getAuthor().isSame(a)){
                uniqueBook++;
            }
        }
        return uniqueBook;
    }


    public String listBooksByAuthor(Author a) {
        // tp return the book for the author
        String listBook="";
        for(int i=0; i<numBooks;i++){
            if(books[i].getAuthor().isSame(a)){
                listBook+=books[i]+"\n";
            }
        }if(listBook=="") return "No books by "+a+".";
        return listBook;
    }

    public String listBooksByTitle(String s) {
        //to return string that list the unique books
        String listBook="";
        for(int i=0; i<numBooks;i++){
            if(books[i].getTitle().toLowerCase().contains(s.toLowerCase())){
                listBook+=books[i]+"\n";
            }
        }
        if(listBook.equals("")) {
            return "No books with " + "\"" + s + "\"" + " in the title.";
        }
        return listBook;
    }


    public String deleteBook(Book b) {
        // This method is for optional bonus points.
        // to delete the book entirely from the library
        String status="Book not found.";
        int limit=-1;
        for(int i=0;i<numBooks;i++){
            if(books[i].equals(b)){
                limit=i;
                break;
            }
        }
        if(limit>-1){
            for(int i=limit;i<numBooks-1;i++){
                books[i]=books[i+1];
                copies[i]=copies[i+1];
                checkedIn[i]=checkedIn[i+1];}
            numBooks--;
            books[numBooks]=null;
            copies[numBooks]=0;
            checkedIn[numBooks]=0;
        }
        if(limit!=-1){
            status= "Book removed.";
        }
        return status;
    }
}

