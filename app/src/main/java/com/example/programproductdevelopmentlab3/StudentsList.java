package com.example.programproductdevelopmentlab3;

public class StudentsList {
    int student_ID;
    String student_FIO;
    String student_Adding_Time;

    StudentsList() {
        this.student_ID = 0;
        this.student_FIO = "Никто";
        this.student_Adding_Time = "0";
    }

    StudentsList(int student_ID, String student_FIO, String student_Adding_Time) {
        this.student_ID = student_ID;
        this.student_FIO = student_FIO;
        this.student_Adding_Time = student_Adding_Time;
    }

    @Override
    public String toString() {
        return "ID Студента: " + student_ID + "\nФИО Студента: " + student_FIO +
                "\nВремя добавления записи: " + student_Adding_Time;
    }
}
