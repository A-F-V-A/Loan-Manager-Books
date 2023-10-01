package edu.est.library.infrastructure.db;
import edu.est.library.domain.models.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class LibraryDB  {
    private Library library;
    private BookDb books;
    private StudentDb student;
    private LibrarianDb librarian;
    private LoanBookDb loanBookDb;
    public LibraryDB() {
        library = new Library();
        books = new BookDb(library.getBooks());
        student = new StudentDb(library.getStudents());
        librarian = new LibrarianDb(library.getLibrarians());
        loanBookDb = new LoanBookDb(library.getLoanBooks());
        dataInit();
    }
    public void dataInit()  {

        try {

            /* Books */
            Date date1 = new Date(122, 2, 15); // Fecha: 15 de marzo de 2022
            Date date2 = new Date(121, 8, 30); // Fecha: 30 de septiembre de 2021
            Date date3 = new Date(120, 4, 10); // Fecha: 10 de mayo de 2020

            Book book1 = new Book("El Jardín Secreto", "Frances Hodgson Burnett", date1,"0001");
            Book book2 = new Book("La Sombra del Viento", "Carlos Ruiz Zafón", date2,"0011");
            Book book3 = new Book("1984", "George Orwell", date3,"0010");

            Date date6 = new Date(121, 0, 20); // Fecha: 20 de enero de 2021
            Date date7 = new Date(120, 11, 8); // Fecha: 8 de diciembre de 2020
            Date date8 = new Date(120, 2, 18); // Fecha: 18 de marzo de 2020
            Date date9 = new Date(120, 9, 12); // Fecha: 12 de octubre de 2020
            Date date10 = new Date(119, 7, 3); // Fecha: 3 de agosto de 2019
            Date date11 = new Date(119, 4, 25); // Fecha: 25 de mayo de 2019
            Date date12 = new Date(119, 1, 14); // Fecha: 14 de febrero de 2019
            Date date13 = new Date(118, 10, 28); // Fecha: 28 de noviembre de 2018
            Date date14 = new Date(118, 5, 9); // Fecha: 9 de junio de 2018
            Date date15 = new Date(118, 2, 1); // Fecha: 1 de marzo de 2018

            Book book6 = new Book("Don Quijote de la Mancha", "Miguel de Cervantes", date6,"0102");
            Book book7 = new Book("Moby Dick", "Herman Melville", date7,"0103");
            Book book8 = new Book("Los Miserables", "Victor Hugo", date8,"0104");
            Book book9 = new Book("Drácula", "Bram Stoker", date9,"0105");
            Book book10 = new Book("La Odisea", "Homero", date10,"0106");
            Book book11 = new Book("La Divina Comedia", "Dante Alighieri", date11,"0107");
            Book book12 = new Book("Crimen y Castigo", "Fiodor Dostoievski", date12,"0108");
            Book book13 = new Book("El Principito", "Antoine de Saint-Exupéry", date13,"0109");
            Book book14 = new Book("Orgullo y prejuicio", "Jane Austen", date14,"0110");
            Book book15 = new Book("Cien años de soledad", "Gabriel García Márquez", date15,"0111");

            books.Cretae(book1);
            books.Cretae(book2);
            books.Cretae(book3);
            books.Cretae(book6);
            books.Cretae(book7);
            books.Cretae(book8);
            books.Cretae(book9);
            books.Cretae(book10);
            books.Cretae(book11);
            books.Cretae(book12);
            books.Cretae(book13);
            books.Cretae(book14);
            books.Cretae(book15);


            /* Students */
            Student student1 = new Student("John", "Doe", 1001);
            Student student2 = new Student("Jane", "Smith", 1002);
            Student student3 = new Student("Bob", "Johnson", 1003);
            Student student4 = new Student("Alice", "Doe", 1004);
            Student student5 = new Student("Charlie", "Brown", 1005);
            Student student6 = new Student("Eva", "Green", 1006);
            Student student7 = new Student("Michael", "Johnson", 1007);
            Student student8 = new Student("Laura", "Smith", 1008);
            Student student9 = new Student("David", "Lee", 1009);
            Student student10 = new Student("Maria", "Gomez", 1010);
            Student student11 = new Student("James", "Brown", 1011);
            Student student12 = new Student("Olivia", "Johnson", 1012);
            Student student13 = new Student("Daniel", "Taylor", 1013);
            Student student14 = new Student("Sophia", "Martinez", 1014);
            Student student15 = new Student("Alexander", "Smith", 1015);
            Student student16 = new Student("Emma", "Johnson", 1016);
            Student student17 = new Student("Lucas", "Brown", 1017);
            Student student18 = new Student("Isabella", "Gomez", 1018);
            Student student19 = new Student("Ethan", "Martinez", 1019);
            Student student20 = new Student("Mia", "Davis", 1020);

            student.Cretae(student1);
            student.Cretae(student2);
            student.Cretae(student3);
            student.Cretae(student4);
            student.Cretae(student5);
            student.Cretae(student6);
            student.Cretae(student7);
            student.Cretae(student8);
            student.Cretae(student9);
            student.Cretae(student10);
            student.Cretae(student11);
            student.Cretae(student12);
            student.Cretae(student13);
            student.Cretae(student14);
            student.Cretae(student15);
            student.Cretae(student16);
            student.Cretae(student17);
            student.Cretae(student18);
            student.Cretae(student19);
            student.Cretae(student20);



            /* Librarian */
            Librarian librarian1 = new Librarian("John", "Doe", "L1001", LocalDate.of(2021, 3, 15));
            Librarian librarian2 = new Librarian("Jane", "Smith", "L1002", LocalDate.of(2020, 9, 30));
            Librarian librarian3 = new Librarian("Bob", "Johnson", "L1003", LocalDate.of(2019, 5, 10));
            Librarian librarian4 = new Librarian("Alice", "Doe", "L1004", LocalDate.of(2018, 12, 5));
            Librarian librarian5 = new Librarian("Charlie", "Brown", "L1005", LocalDate.of(2022, 2, 18));
            Librarian librarian6 = new Librarian("Eva", "Green", "L1006", LocalDate.of(2021, 7, 3));
            Librarian librarian7 = new Librarian("Michael", "Johnson", "L1007", LocalDate.of(2022, 11, 20));
            Librarian librarian8 = new Librarian("Laura", "Smith", "L1008", LocalDate.of(2020, 3, 8));
            Librarian librarian9 = new Librarian("David", "Lee", "L1009", LocalDate.of(2019, 6, 25));
            Librarian librarian10 = new Librarian("Maria", "Gomez", "L1010", LocalDate.of(2018, 10, 12));
            Librarian librarian11 = new Librarian("James", "Brown", "L1011", LocalDate.of(2021, 4, 1));
            Librarian librarian12 = new Librarian("Olivia", "Johnson", "L1012", LocalDate.of(2018, 11, 28));
            Librarian librarian13 = new Librarian("Daniel", "Taylor", "L1013", LocalDate.of(2022, 5, 9));
            Librarian librarian14 = new Librarian("Sophia", "Martinez", "L1014", LocalDate.of(2020, 8, 15));
            Librarian librarian15 = new Librarian("Alexander", "Smith", "L1015", LocalDate.of(2019, 2, 1));

            librarian.Cretae(librarian1);
            librarian.Cretae(librarian2);
            librarian.Cretae(librarian3);
            librarian.Cretae(librarian4);
            librarian.Cretae(librarian5);
            librarian.Cretae(librarian6);
            librarian.Cretae(librarian7);
            librarian.Cretae(librarian8);
            librarian.Cretae(librarian9);
            librarian.Cretae(librarian10);
            librarian.Cretae(librarian11);
            librarian.Cretae(librarian12);
            librarian.Cretae(librarian13);
            librarian.Cretae(librarian14);
            librarian.Cretae(librarian15);


            /* loanBookDb */


            /* loanBookDb ONE */
            book6.setQuantity(10);
            book2.setQuantity(4);
            book3.setQuantity(6);


            LoanBook loanOne = new LoanBook(student10,new Date(122, 2, 15),librarian1);


            BookLoanDetail datailOne = new BookLoanDetail(book6, new Date(122, 2, 15),"Book with cover defects",loanOne.getIdUUID());
            book6.Quantity(1);

            BookLoanDetail datailTwo = new BookLoanDetail(book2, new Date(122, 4, 18),"No defects",loanOne.getIdUUID());
            book2.Quantity(1);

            BookLoanDetail datailThree = new BookLoanDetail(book3, new Date(122, 6, 15),"Sheet 15 is missing",loanOne.getIdUUID());
            book3.Quantity(1);

            loanOne.addDetails(datailOne,datailTwo,datailThree);


            loanBookDb.Cretae(loanOne);


            /* loanBookDb two */

            book1.setQuantity(6);
            book7.setQuantity(2);
            book8.setQuantity(3);


            LoanBook loanTwo = new LoanBook(student5,new Date(122, 5, 5),librarian6);


            BookLoanDetail datailFor = new BookLoanDetail(book1, new Date(122, 9, 20),"Book with cover defects",loanTwo.getIdUUID());
            book1.Quantity(1);

            BookLoanDetail datailFive = new BookLoanDetail(book7, new Date(123, 3, 11),"No defects",loanTwo.getIdUUID());
            book7.Quantity(1);

            BookLoanDetail datailSix = new BookLoanDetail(book8, new Date(123, 4, 8),"Sheet 15 is missing",loanTwo.getIdUUID());
            book8.Quantity(1);

            loanTwo.addDetails(datailFor,datailFive,datailSix);


            loanBookDb.Cretae(loanTwo);



            /* loanBookDb Three */

            book15.setQuantity(2);
            book14.setQuantity(3);
            book13.setQuantity(4);


            LoanBook loanThree = new LoanBook(student20,new Date(123, 2, 4),librarian15);


            BookLoanDetail datailSeven = new BookLoanDetail(book15, new Date(122, 9, 20),"Book with cover defects",loanThree.getIdUUID());
            book15.Quantity(1);

            BookLoanDetail datailEight = new BookLoanDetail(book14, new Date(123, 3, 11),"No defects",loanThree.getIdUUID());
            book14.Quantity(1);

            BookLoanDetail datailNine = new BookLoanDetail(book13, new Date(123, 4, 8),"Sheet 15 is missing",loanThree.getIdUUID());
            book13.Quantity(1);

            loanThree.addDetails(datailSeven,datailEight,datailNine);


            loanBookDb.Cretae(loanThree);




        }catch (Exception e){
            System.err.println(e);
        }
    }

    public Library getLibrary() {
        return library;
    }
    public BookDb getBooks() {
        return books;
    }
    public StudentDb getStudent() {
        return student;
    }
    public LibrarianDb getLibrarian() {
        return librarian;
    }
    public LoanBookDb getLoanBookDb() {
        return loanBookDb;
    }
}
