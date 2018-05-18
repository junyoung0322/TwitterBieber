import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

import javax.imageio.ImageIO;

public class ImageDown {
	final String TITLE = "JustinBieber_";
	final String FILEFOLDER = "\\file\\";
	final String DOT = ".";
	
	public void Downloader(TreeSet<String> tImageUrl) throws Exception {
		
		ArrayList<String> lImageUrl = new ArrayList<String>(tImageUrl);
		// 日付
		SimpleDateFormat oSimpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmsssss");
		Date oDate = new Date();
		String sTime = oSimpleDateFormat.format(oDate);
		
		for (int i = 0; i < 10; i++) {
			String sImgUrlString = lImageUrl.get(i);
			URL oUrl = new URL(sImgUrlString);
			// 拡張子取得
			String sExt = sImgUrlString.substring(sImgUrlString.lastIndexOf(".") + 1, sImgUrlString.length());
			String sFileName = TITLE + sTime + i + DOT + sExt;
			BufferedImage bImg = ImageIO.read(oUrl);
			
			// image download
			ImageIO.write(bImg, sExt, new File(System.getProperty("user.dir") + FILEFOLDER + sFileName));
		}
	}
}
