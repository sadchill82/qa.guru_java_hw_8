package lesson8.tests;

import lesson8.pages.RegistrationPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class PracticeFormParameterizedTest extends TestBase {

    private final RegistrationPage registrationPage = new RegistrationPage();

    // --- Вариант 1: Использование @MethodSource ---

    @DisplayName("MethodSource вариант")
    @ParameterizedTest(name = "[{index}] MethodSource - {0}, {1}, {2}, {3}, {4}, {5}, {6}")
    @MethodSource("methodSourceProvider")
    void fillFormUsingMethodSource(String firstName,
                                   String lastName,
                                   String gender,
                                   String phoneNumber,
                                   String day,
                                   String month,
                                   String year) {
        registrationPage.openPage()
                .removeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .chooseGender(gender)
                .setPhoneNumber(phoneNumber)
                .setDateOfBirth(day, month, year)
                .submitRegistration()
                .modalWindowShouldExist()
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phoneNumber)
                .checkResult("Date of Birth", day + " " + month + "," + year)
                .closeModalWindow()
                .modalWindowShouldNotExist();
    }

    static Stream<Arguments> methodSourceProvider() {
        return Stream.of(
                Arguments.of("John","Doe"  ,"Male"  ,"1234567890","01","January" ,"2000"),
                Arguments.of("Jane","Smith","Female","0987654321","02","February","1995"),
                Arguments.of("Alex","Jones","Other" ,"9999999999","03","March"   ,"1990")
        );
    }

    // --- Вариант 2: Использование @CsvSource ---

    @DisplayName("CsvSource вариант")
    @ParameterizedTest(name = "[{index}] CsvSource - {0}, {1}, {2}, {3}, {4}, {5}, {6}")
    @CsvSource({
            // Формат: firstName, lastName, gender, phoneNumber, day, month, year
            "John      ,Doe      ,Male   ,1234567890  ,01  ,January  ,2000",
            "Jane      ,Smith    ,Female ,0987654321  ,02  ,February ,1995",
            "Alex      ,Jones    ,Other  ,9999999999  ,03  ,March    ,1990"
    })
    void fillFormUsingCsvSource(String firstName,
                                String lastName,
                                String gender,
                                String phoneNumber,
                                String day,
                                String month,
                                String year) {
        registrationPage.openPage()
                .removeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .chooseGender(gender)
                .setPhoneNumber(phoneNumber)
                .setDateOfBirth(day, month, year)
                .submitRegistration()
                .modalWindowShouldExist()
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phoneNumber)
                .checkResult("Date of Birth", day + " " + month + "," + year)
                .closeModalWindow()
                .modalWindowShouldNotExist();
    }

    // --- Вариант 3: Использование @CsvFileSource ---

    @DisplayName("CsvFileSource вариант")
    @ParameterizedTest(name = "[{index}] CsvFileSource - {0}, {1}, {2}, {3}, {4}, {5}, {6}")
    @CsvFileSource(resources = "/studentData.csv", numLinesToSkip = 1)
    void fillFormUsingCsvFileSource(String firstName,
                                    String lastName,
                                    String gender,
                                    String phoneNumber,
                                    String day,
                                    String month,
                                    String year) {
        registrationPage.openPage()
                .removeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .chooseGender(gender)
                .setPhoneNumber(phoneNumber)
                .setDateOfBirth(day, month, year)
                .submitRegistration()
                .modalWindowShouldExist()
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phoneNumber)
                .checkResult("Date of Birth", day + " " + month + "," + year)
                .closeModalWindow()
                .modalWindowShouldNotExist();
    }
}