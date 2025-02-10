package lesson8.utils;

import net.datafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public final class FakePersonMethods {
    private static final Faker FAKER = new Faker();
    private static final Locale LOCALE = Locale.US;

    public static String getFirstName() {
        return FAKER.name().firstName();
    }

    public static String getLastName() {
        return FAKER.name().lastName();
    }

    public static String getEmail() {
        return FAKER.internet().emailAddress();
    }

    public static String getGender() {
        return FAKER.options().option("Male", "Female", "Other");
    }

    public static String getPhoneNumber() {
        return FAKER.numerify("##########");
    }

    public static Date getBirthdayDate() {
        return FAKER.date().birthday(18, 70);
    }

    public static String getDay(Date birthdayDate) {
        return new SimpleDateFormat("dd", LOCALE).format(birthdayDate);
    }

    public static String getMonth(Date birthdayDate) {
        return new SimpleDateFormat("MMMM", LOCALE).format(birthdayDate);
    }

    public static String getYear(Date birthdayDate) {
        return new SimpleDateFormat("yyyy", LOCALE).format(birthdayDate);
    }

    public static String getSubject() {
        String[] subjects = {
                "Hindi", "English", "Maths", "Physics", "Chemistry", "Biology",
                "Computer Science", "Commerce", "Accounting", "Economics",
                "Arts", "Social Studies", "History", "Civics"
        };
        return FAKER.options().option(subjects);
    }

    public static List<String> getSubjects(int count) {
        List<String> subjectsList = new ArrayList<>();
        while (subjectsList.size() < count) {
            String subject = getSubject();
            if (!subjectsList.contains(subject)) {
                subjectsList.add(subject);
            }
        }
        return subjectsList;
    }

    public static String getHobby() {
        String[] hobbies = {"Sports", "Reading", "Music"};
        return FAKER.options().option(hobbies);
    }

    public static List<String> getHobbies(int count) {
        List<String> hobbiesList = new ArrayList<>();
        while (hobbiesList.size() < count) {
            String hobby = getHobby();
            if (!hobbiesList.contains(hobby)) {
                hobbiesList.add(hobby);
            }
        }
        return hobbiesList;
    }

    public static String getPicture() {
        String[] pictures = {"picture1.jpg", "picture2.jpg"};
        return FAKER.options().option(pictures);
    }

    public static String getAddress() {
        return FAKER.address().fullAddress();
    }

    public static String getState() {
        String[] states = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
        return FAKER.options().option(states);
    }

    public static String getCity(String state) {
        return switch (state) {
            case "NCR" -> FAKER.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> FAKER.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> FAKER.options().option("Karnal", "Panipat");
            case "Rajasthan" -> FAKER.options().option("Jaipur", "Jaiselmer");
            default -> "";
        };
    }
}
