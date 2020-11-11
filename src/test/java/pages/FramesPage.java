package pages;

import org.openqa.selenium.By;

public class FramesPage extends BaseCommands {

    public void userSelectFrame() throws InterruptedException {
        int framesSize = findElementSize(element(By.tagName("frame")));
        outter : for (int i = 0; i <= framesSize-1; i++) {
            changeToFrame(i);//Change to one of the to parents frame
            int numberOfInnerFrames = findElementsChild(By.tagName("frameset"), By.tagName("frame")).size();
            for (int j = 0; j <= numberOfInnerFrames-1; j++) {
                By frameInnerText = By.xpath("/html/body");
                changeToFrame(j); //Change to childFrame
                System.out.println(getText(frameInnerText));
                changeToParentFrame();
            }
            changeToMainFrame();
            break outter;
        }
    }
}

