package utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import tests.BaseTestController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class GetScreenShot extends BaseTestController {

    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
    public static MediaEntityModelProvider capture (String screenShotName) throws IOException {
        WebDriver driver = DriverManager.getDriver();
        String browserName = DriverManager.getBrowserName()+"/";
        String testName = ExtentReportManager.getTestName()+"/";
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        Date date= new Date();
        long time = date.getTime();

        String dest = "./screenshots/"+browserName+testName+screenShotName+time+".png";
        File destination = new File(dest);

        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage image = ImageIO.read(destination);
        BufferedImage resized = resize(image, 768, 1024);
        ImageIO.write(resized, "png", destination);
        MediaEntityModelProvider screenshot = null;

        try {
            screenshot = MediaEntityBuilder.createScreenCaptureFromPath(dest).build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return screenshot;
    }
}
