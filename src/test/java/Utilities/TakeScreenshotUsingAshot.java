package Utilities;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TakeScreenshotUsingAshot extends Setup {

	public static String sshot(String methodname, String SavePath) throws IOException, InterruptedException {
		Thread.sleep(200);
		Screenshot screenshot = new AShot()
				.shootingStrategy(ShootingStrategies.simple()) // Capture only the visible area
				.takeScreenshot(driver);

		File dir = new File(SavePath);
		if (!dir.exists()) {
			dir.mkdirs();
			System.out.println("Screenshot directory created: " + SavePath);
		}

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd-HH_mm");
		String currentDate = LocalDateTime.now().format(dtf);
		String filePath = SavePath + File.separator + methodname + "-" + currentDate + ".jpg";

		ImageIO.write(screenshot.getImage(), "jpg", new File(filePath));
		System.out.println("Screenshot saved: " + filePath);
		return currentDate;
	}


}