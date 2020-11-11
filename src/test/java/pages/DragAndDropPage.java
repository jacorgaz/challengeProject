package pages;

import org.openqa.selenium.By;
import utils.Constants;

public class DragAndDropPage extends BaseCommands {

    private By box1 = By.id("column-a");
    private By box2 = By.id("column-b");

    public void userDragAndDrop() throws InterruptedException {
        dragAndDrop(box1,box2);
    }
}
