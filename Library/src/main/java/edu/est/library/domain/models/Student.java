package edu.est.library.domain.models;

public class Student implements Comparable<Student> {
    private String firstName;
    private String lastName;
    private int studentId;

    public Student(){}

    public Student(String firstName, String lastName, int studentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
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

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public boolean isIncomplete(){
        return  firstName == null || firstName.isEmpty() ||
                lastName == null || lastName.isEmpty() ||
                studentId == 0;

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Student otherStudent = (Student) obj;
        return studentId == otherStudent.getStudentId();
    }


    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", studentId=" + studentId +
                '}';
    }

    @Override
    public int compareTo(Student otherStudent) {
        int lastNameComparison = this.lastName.compareTo(otherStudent.lastName);

        if (lastNameComparison != 0) {
            return lastNameComparison;
        } else {
            int firstNameComparison = this.firstName.compareTo(otherStudent.firstName);

            if (firstNameComparison != 0) {
                return firstNameComparison;
            } else {
                return Integer.compare(this.studentId, otherStudent.studentId);
            }
        }
    }


}
