package lesson8.tests;

import lesson8.models.StudentData;
import lesson8.pages.RegistrationPage;
import lesson8.utils.StudentDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class PracticeFormParameterizedTest extends TestBase {

    private final RegistrationPage registrationPage = new RegistrationPage();

    private void fillForm(StudentData studentData) {
        if (studentData.getFirstName() != null)
            registrationPage.setFirstName(studentData.getFirstName());
        if (studentData.getLastName() != null)
            registrationPage.setLastName(studentData.getLastName());
        if (studentData.getEmail() != null)
            registrationPage.setEmail(studentData.getEmail());
        if (studentData.getGender() != null)
            registrationPage.chooseGender(studentData.getGender());
        if (studentData.getPhoneNumber() != null)
            registrationPage.setPhoneNumber(studentData.getPhoneNumber());
        if (studentData.getDay() != null && studentData.getMonth() != null && studentData.getYear() != null)
            registrationPage.setDateOfBirth(studentData.getDay(), studentData.getMonth(), studentData.getYear());
        if (studentData.getSubjects() != null && !studentData.getSubjects().isEmpty())
            registrationPage.setSubjects(studentData.getSubjects());
        if (studentData.getHobbies() != null && !studentData.getHobbies().isEmpty())
            registrationPage.chooseHobbies(studentData.getHobbies());
        if (studentData.getPicture() != null)
            registrationPage.uploadFile(studentData.getPicture());
        if (studentData.getAddress() != null)
            registrationPage.setAddress(studentData.getAddress());
        if (studentData.getState() != null)
            registrationPage.setState(studentData.getState());
        if (studentData.getCity() != null)
            registrationPage.setCity(studentData.getCity());
    }

    private void checkResults(StudentData studentData) {
        if (studentData.getFirstName() != null || studentData.getLastName() != null) {
            String studentName = (studentData.getFirstName() != null ? studentData.getFirstName() : "") +
                    " " +
                    (studentData.getLastName() != null ? studentData.getLastName() : "");
            registrationPage.checkResult("Student Name", studentName.trim());
        }
        if (studentData.getEmail() != null)
            registrationPage.checkResult("Student Email", studentData.getEmail());
        if (studentData.getGender() != null)
            registrationPage.checkResult("Gender", studentData.getGender());
        if (studentData.getPhoneNumber() != null)
            registrationPage.checkResult("Mobile", studentData.getPhoneNumber());
        if (studentData.getDay() != null && studentData.getMonth() != null && studentData.getYear() != null)
            registrationPage.checkResult("Date of Birth",
                    studentData.getDay() + " " + studentData.getMonth() + "," + studentData.getYear());
        if (studentData.getSubjects() != null && !studentData.getSubjects().isEmpty())
            registrationPage.checkResult("Subjects", String.join(", ", studentData.getSubjects()));
        if (studentData.getHobbies() != null && !studentData.getHobbies().isEmpty())
            registrationPage.checkResult("Hobbies", String.join(", ", studentData.getHobbies()));
        if (studentData.getPicture() != null)
            registrationPage.checkResult("Picture", studentData.getPicture());
        if (studentData.getAddress() != null)
            registrationPage.checkResult("Address", studentData.getAddress());
        if (studentData.getState() != null && studentData.getCity() != null)
            registrationPage.checkResult("State and City", studentData.getState() + " " + studentData.getCity());
    }

    // --- Вариант 1: Использование @MethodSource ---

    @DisplayName("MethodSource вариант")
    @ParameterizedTest(name = "[{index}] MethodSource - {0}")
    @MethodSource("methodSourceProvider")
    void fillFormUsingMethodSource(String testCaseName, StudentData studentData, boolean modalWindowExpected) {
        registrationPage.openPage().removeBanners();
        fillForm(studentData);
        registrationPage.submitRegistration();

        if (modalWindowExpected) {
            registrationPage.modalWindowShouldExist();
            checkResults(studentData);
            registrationPage.closeModalWindow().modalWindowShouldNotExist();
        } else {
            registrationPage.modalWindowShouldNotExist();
        }
    }

    static Stream<Arguments> methodSourceProvider() {
        return Stream.of(
                Arguments.of("Full Data", StudentDataFactory.generateFullStudentData(), true),
                Arguments.of("Minimal Data", StudentDataFactory.generateMinimalStudentData(), true),
                Arguments.of("FirstName Only", StudentDataFactory.generateFirstNameOnlyData(), false)
        );
    }

    // --- Вариант 2: Использование @CsvSource ---

    @DisplayName("CsvSource вариант")
    @ParameterizedTest(name = "[{index}] CsvSource - {0}")
    @CsvSource({
            // Формат: testCaseName, firstName, lastName, gender, phoneNumber, day, month, year, modalWindowExpected
            "Full Data     ,John ,Doe   ,Male   ,1234567890 ,01 ,January  ,2000 ,true",
            "Minimal Data  ,Jane ,Smith ,Female ,0987654321 ,02 ,February ,1995 ,true",
            "FirstName Only,Alex ,      ,       ,           ,   ,         ,     ,false"
    })
    void fillFormUsingCsvSource(String testCaseName,
                                String firstName,
                                String lastName,
                                String gender,
                                String phoneNumber,
                                String day,
                                String month,
                                String year,
                                boolean modalWindowExpected) {
        StudentData studentData = new StudentData();
        studentData.setFirstName(firstName);
        studentData.setLastName(lastName);
        studentData.setGender(gender);
        studentData.setPhoneNumber(phoneNumber);
        studentData.setDay(day);
        studentData.setMonth(month);
        studentData.setYear(year);

        registrationPage.openPage().removeBanners();
        fillForm(studentData);
        registrationPage.submitRegistration();

        if (modalWindowExpected) {
            registrationPage.modalWindowShouldExist();
            checkResults(studentData);
            registrationPage.closeModalWindow().modalWindowShouldNotExist();
        } else {
            registrationPage.modalWindowShouldNotExist();
        }
    }

    // --- Вариант 3: Использование @CsvFileSource ---

    @DisplayName("CsvFileSource вариант")
    @ParameterizedTest(name = "[{index}] CsvFileSource - {0}")
    @CsvFileSource(resources = "/studentData.csv", numLinesToSkip = 1)
    void fillFormUsingCsvFileSource(String testCaseName,
                                    String firstName,
                                    String lastName,
                                    String gender,
                                    String phoneNumber,
                                    String day,
                                    String month,
                                    String year,
                                    boolean modalWindowExpected) {
        StudentData studentData = new StudentData();
        studentData.setFirstName(firstName);
        studentData.setLastName(lastName);
        studentData.setGender(gender);
        studentData.setPhoneNumber(phoneNumber);
        studentData.setDay(day);
        studentData.setMonth(month);
        studentData.setYear(year);

        registrationPage.openPage().removeBanners();
        fillForm(studentData);
        registrationPage.submitRegistration();

        if (modalWindowExpected) {
            registrationPage.modalWindowShouldExist();
            checkResults(studentData);
            registrationPage.closeModalWindow().modalWindowShouldNotExist();
        } else {
            registrationPage.modalWindowShouldNotExist();
        }
    }
}