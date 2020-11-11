package pages;

import org.openqa.selenium.By;
import utils.Constants;

//import static utils.Constants.PDF_PATH;

public class UploadFilePage extends BaseCommands {

    private By buttonSearch = By.id("file-upload");
    private By buttonUpload = By.id("file-submit");

    public void userUploadDocument() throws InterruptedException {
      // uploadFile(buttonSearch, PDF_PATH);
    }
}