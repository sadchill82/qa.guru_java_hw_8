package lesson8.utils;

import lesson8.models.StudentData;

import java.util.Date;

public final class StudentDataFactory {

    public static StudentData generateFullStudentData() {
        StudentData student = new StudentData();

        student.setFirstName(FakePersonMethods.getFirstName());
        student.setLastName(FakePersonMethods.getLastName());
        student.setEmail(FakePersonMethods.getEmail());
        student.setGender(FakePersonMethods.getGender());
        student.setPhoneNumber(FakePersonMethods.getPhoneNumber());

        Date birthdayDate = FakePersonMethods.getBirthdayDate();
        student.setDay(FakePersonMethods.getDay(birthdayDate));
        student.setMonth(FakePersonMethods.getMonth(birthdayDate));
        student.setYear(FakePersonMethods.getYear(birthdayDate));

        student.setSubjects(FakePersonMethods.getSubjects(2));
        student.setHobbies(FakePersonMethods.getHobbies(2));

        student.setPicture(FakePersonMethods.getPicture());
        student.setAddress(FakePersonMethods.getAddress());
        student.setState(FakePersonMethods.getState());
        student.setCity(FakePersonMethods.getCity(student.getState()));

        return student;
    }

    public static StudentData generateMinimalStudentData() {
        StudentData student = new StudentData();

        student.setFirstName(FakePersonMethods.getFirstName());
        student.setLastName(FakePersonMethods.getLastName());
        student.setGender(FakePersonMethods.getGender());
        student.setPhoneNumber(FakePersonMethods.getPhoneNumber());

        Date birthdayDate = FakePersonMethods.getBirthdayDate();
        student.setDay(FakePersonMethods.getDay(birthdayDate));
        student.setMonth(FakePersonMethods.getMonth(birthdayDate));
        student.setYear(FakePersonMethods.getYear(birthdayDate));

        return student;
    }

    public static StudentData generateFirstNameOnlyData() {
        StudentData student = new StudentData();
        student.setFirstName(FakePersonMethods.getFirstName());
        return student;
    }
}