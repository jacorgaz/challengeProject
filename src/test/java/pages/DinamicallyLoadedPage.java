package pages;

import org.openqa.selenium.By;

public class DinamicallyLoadedPage extends BaseCommands {

    By textHelloWorld=  By.xpath("//*[@id=\"finish\"]/h4");
    By buttonStart=  By.xpath("//*[@id=\"start\"]/button");

    public void userVerifyDynamicText() throws InterruptedException {
        clickElement(buttonStart);
        waitFluentElement(textHelloWorld,"Hello World!");
    }
}