package pages;

import org.openqa.selenium.By;

public class DownloadFilePage extends BaseCommands {

    public void userDownloadDocument() throws InterruptedException {
        isFileDownloaded(System.getProperty("user.home")+"/Downloads","programming-ruby.pdf");
    }
}
