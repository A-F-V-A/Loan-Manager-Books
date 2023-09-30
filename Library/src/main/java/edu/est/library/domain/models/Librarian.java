package edu.est.library.domain.models;

import java.time.LocalDate;

public class Librarian {
    private String firstName;
    private String lastName;
    private String identification;
    private LocalDate startDate;

    public Librarian(){}

    public Librarian(String firstName, String lastName, String identification, LocalDate startDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.identification = identification;
        this.startDate = startDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public boolean isIncomplete() {
        return  firstName == null      || firstName.isEmpty()      ||
                lastName == null       || lastName.isEmpty()       ||
                identification == null || identification.isEmpty() ||
                startDate == null;
    }
    @Override
    public int hashCode() {
        int hash = 31;
        hash = hash * firstName.hashCode() + lastName.hashCode() + identification.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Librarian otherLibrarian = (Librarian) obj;

        if (!firstName.equals(otherLibrarian.firstName)) return false;
        if (!lastName.equals(otherLibrarian.lastName)) return false;

        return identification.equals(otherLibrarian.identification);
    }
}
