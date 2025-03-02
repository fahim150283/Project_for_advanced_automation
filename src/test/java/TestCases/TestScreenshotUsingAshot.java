package TestCases;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.imageio.ImageIO;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class TestScreenshotUsingAshot extends TestBrowsers{

	public static void sshot(String methodname) throws IOException {

//		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
//		ImageIO.write(screenshot.getImage(), "jpg", new File(".\\screenshot\\"+methodname+"_"+System.currentTimeMillis()+".jpg"));

		Screenshot screenshot = new AShot()
				.shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver);

		// Use File.separator to make path OS-independent
		String directory = "." + File.separator + "screenshot";
		File dir = new File(directory);
		if (!dir.exists()) {
			dir.mkdirs(); // Create directory if it doesn't exist
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd-HH_mm");
		String currentDate = LocalDateTime.now().format(dtf);
		System.out.println(currentDate);
		// Construct the full path
		String filePath = directory + File.separator + methodname + "-" + currentDate + ".jpg";

		ImageIO.write(screenshot.getImage(), "jpg", new File(filePath));
		System.out.println("Screenshot saved: " + filePath);
	}

}
