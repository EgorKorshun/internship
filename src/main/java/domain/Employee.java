package domain;

public class Employee {

    private String io;
    private String surname;
    private String operNumber;

    public Employee(String user) {
    }

    public Employee(){}

    public Employee(String surname, String io, String operNumber) {
        this.surname = surname;
        this.io = io;
        this.operNumber = operNumber;
    }


    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "ФИО :" + getSurname() + getIo() +
                "номер телефона - " + getOperNumber();
    }


    public String getIo() {
        return io;
    }

    public void setIo(String io) {
        this.io = io;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getOperNumber() {
        return operNumber;
    }

    public void setOperNumber(String operNumber) {
        this.operNumber = operNumber;
    }
}

