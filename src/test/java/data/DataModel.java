package data;

import org.testng.annotations.DataProvider;

public interface DataModel {

    final class LoginTestData {

        public String userName;
        public String userPassword;


        public LoginTestData  (String userName, String userPassword){
            this.userName = userName;
            this.userPassword = userPassword;
        }
    }

    final class AddParentsPageData {
        public String parentsName;
        public String childName;
        public String email;

        public AddParentsPageData  (String parentsName, String childName, String email){
            this.parentsName = parentsName;
            this.childName = childName;
            this.email= email;
        }
    }
}
