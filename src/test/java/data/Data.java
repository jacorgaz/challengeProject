package data;

import java.sql.SQLException;
import java.time.Instant;

import static pages.BaseCommands.getCurrentTimeInNanoSeconds;

public class Data {
    public static final class LoginUsers {
        public DataModel.LoginTestData USER_TYPE;
        public DataModel.LoginTestData USER_WRONG_EMAIL;
        public DataModel.LoginTestData USER_WRONG_PASS;
        public DataModel.LoginTestData ROL_4_NURSE_3;
        public DataModel.LoginTestData ROL_4_NURSE_4;
        public DataModel.LoginTestData ROL_4_NURSE_2;
        public DataModel.LoginTestData ROL_1_NURSE_NEONATOS;
        public DataModel.LoginTestData ROL_1_NURSE_NEONATOS_WRONG_PASS;
        public LoginUsers() {
            String environment = System.getProperty("dev.env");
            switch (environment) {
                case "dev":
                case "pro":
                    USER_TYPE = new DataModel.LoginTestData(
                            "tomsmith",
                            "SuperSecretPassword!"
                    );
                    USER_WRONG_EMAIL = new DataModel.LoginTestData(
                            "tomsmith@gmail.com",
                            "SuperSecretPassword!"
                    );
                    USER_WRONG_PASS = new DataModel.LoginTestData(
                            "enfermera2@sanitas.es",
                            "SuperSecretPassword!"
                    );
                    ROL_4_NURSE_3 = new DataModel.LoginTestData(
                            "enfermera3@sanitas.es",
                            "wOU4HV7Upd"
                    );
                    ROL_4_NURSE_4 = new DataModel.LoginTestData(
                            "enfermera4@sanitas.es",
                            "VaejvLn1Yc"
                    );
                    ROL_4_NURSE_2 = new DataModel.LoginTestData(
                            "enfermera2@sanitas.es",
                            "TPKPL1lpkm"
                    );
                    ROL_1_NURSE_NEONATOS_WRONG_PASS = new DataModel.LoginTestData(
                            "mgarrido@hospitalmanises.es",
                            "Etrienfkei3i3i3i3i3i3o@"
                    );
                    ROL_1_NURSE_NEONATOS = new DataModel.LoginTestData(
                            "mgarrido@hospitalmanises.es",
                            "Etrienfo@"
                    );
                    break;
            }
        }
    }
    public static final class AddParentsPage {
        public DataModel.AddParentsPageData MISSING_PARENT_NAME;
        public DataModel.AddParentsPageData MISSING_CHILD_NAME;
        public DataModel.AddParentsPageData WRONG_EMAIL_FORMAT_MISSING_AT;
        public DataModel.AddParentsPageData WRONG_EMAIL_FORMAT_MISSING_DOT_COM;
        public DataModel.AddParentsPageData MISSING_PARENT_SURNAME;
        public DataModel.AddParentsPageData RIGHT_PERSONAL_DATA;
        public AddParentsPage() throws SQLException, ClassNotFoundException {
            String environment = System.getProperty("dev.env");
            switch (environment) {
                case "dev":
                case "pro":
                    MISSING_PARENT_NAME = new DataModel.AddParentsPageData(
                            "",
                            "Child",
                            "test@test.com"
                    );
                    MISSING_CHILD_NAME = new DataModel.AddParentsPageData(
                            "Padre madre",
                            "",
                            "test@test.com"
                    );
                    WRONG_EMAIL_FORMAT_MISSING_AT = new DataModel.AddParentsPageData(
                            "Padre madre",
                            "Nombre del niño",
                            "testtest.com"
                    );
                    WRONG_EMAIL_FORMAT_MISSING_DOT_COM = new DataModel.AddParentsPageData(
                            "Padre madre",
                            "Nombre del niño",
                            "test@test"
                    );
                    MISSING_PARENT_SURNAME = new DataModel.AddParentsPageData(
                            "Padre",
                            "Nombre del niño",
                            "test@test.com"
                    );
                    RIGHT_PERSONAL_DATA = new DataModel.AddParentsPageData(
                            "Padre Madre niño",
                            "Nombre del niño",
                            "email_"+getCurrentTimeInNanoSeconds()+"@yopmail.com"
                    );
                    break;
            }
        }
    }
}





