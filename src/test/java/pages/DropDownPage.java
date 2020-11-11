package pages;

import org.openqa.selenium.By;
import utils.Constants;

public class DropDownPage extends BaseCommands {

    private By dropDown = By.id("dropdown");
    public void userSelectOptionInDropDown() throws InterruptedException {
       selectDropDown(dropDown, "Option 2");
    }
}