import java.util.TreeSet;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JBSearcher {

	public static void main(String[] args) {
		// Twitter TimeLineAPIオブジェクト生成
		TwitterAPI twitter = new TwitterAPI();
		try {
			// 検索結果JSON取得
			String sJson = twitter.getUsesTimeLine();
			// 結果出力
			printJson(sJson);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 結果出力
	 *
	 * @param sJson
	 */
	public static void printJson(String sJson) {
		String sHeader= "<<<  Twitter API検索結果 - 検索文字列：\"JustinBieber\"  >>>";
		String sBoundary = "======================================";

		JsonParser jsonParser = new JsonParser();
		JsonElement eJson = jsonParser.parse(sJson);
//		JsonArray aJson = (JsonArray)jsonParser.parse(sJson);
		JsonObject ObjectJson = eJson.getAsJsonObject();
		JsonArray aJson = ObjectJson.get("statuses").getAsJsonArray();

		System.out.println(sHeader);
		System.out.println(sBoundary);

		TreeSet<String> tImageUrls = new TreeSet<String>();
		for (int i = 0; i < aJson.size(); i++) {
			JsonObject oJson = (JsonObject)aJson.get(i);
			JsonObject oEntJson = (JsonObject)oJson.get("entities");
			if (oEntJson != null && oEntJson.size() != 0) {
				JsonArray aMediaJson = (JsonArray)oEntJson.get("media");
				if (aMediaJson != null && aMediaJson.size() != 0) {
					JsonObject oMediaJson = (JsonObject)aMediaJson.get(0);
					
//					JsonObject oUser = (JsonObject)oJson.get("user");
//					
//					System.out.println("<< " + oJson.get("created_at") + " - " + oUser.get("screen_name") + " >>");
//					System.out.println(oJson.get("text"));
//					System.out.println(oMediaJson.get("media_url_https"));
//					System.out.println("------------------------");

					String sImgUrlString = oMediaJson.get("media_url").toString().split("\"")[1];
					tImageUrls.add(sImgUrlString);
					
				}
			}
		}
		try {
			ImageDown oImageDown = new ImageDown();
			oImageDown.Downloader(tImageUrls);
//						URL oUrl = new URL(sImgUrlString);
//
//						SimpleDateFormat oSimpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmsssss");
//						Date oDate = new Date();
//						String sTime = oSimpleDateFormat.format(oDate);
//
//						String sExt = sImgUrlString.substring(sImgUrlString.lastIndexOf(".") + 1, sImgUrlString.length());
//						String sFileName = "JustinBieber_" + sTime + iCount + "." + sExt;
//						BufferedImage bImg = ImageIO.read(oUrl);
//			
//			// image download
//			ImageIO.write(bImg, sExt, new File(System.getProperty("user.dir") + "\\file\\" + sFileName));
//			
			System.out.println(" Image download is complete! ");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(sBoundary);
	}
}
