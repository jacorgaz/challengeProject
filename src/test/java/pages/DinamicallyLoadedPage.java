package pages;

import org.openqa.selenium.By;
import utils.Constants;

public class DinamicallyLoadedPage extends BaseCommands {

    By textHelloWorld=  By.xpath("//*[@id=\"finish\"]/h4");
    By buttonStart=  By.xpath("//*[@id=\"start\"]/button");

    public void userVerifyDynamicText() throws InterruptedException {
        click(buttonStart);
        waitFluentElement(textHelloWorld,"Hello World!");
    }
}