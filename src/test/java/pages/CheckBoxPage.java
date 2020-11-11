package pages;

import org.openqa.selenium.By;

public class CheckBoxPage extends BaseCommands {
    private By checkBox1 = By.xpath("//*[@id='checkboxes']/input[1]");
    private By checkBox2 = By.xpath("//*[@id='checkboxes']/input[2]");

    public void userSelectCheckBoxes(){
        selectCheckBox(checkBox1,true);
        selectCheckBox(checkBox2,false);
    }
}
