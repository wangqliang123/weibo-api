package weibo4j.wang;

import java.io.IOException;

import weibo4j.Weibo;
import weibo4j.model.WeiboException;

public class IP2Location {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		String access_token = "2.00DjkOMBBYA83E4b85c7d6f3TZh8yB";
		
		int try_time = 0;
		int start_index = 29600;
		while (try_time < 5) {
			Weibo weibo = new Weibo();
			weibo.setToken(access_token);
			try {
				IP2Location iploc = new IP2Location();
				start_index = iploc.getLocations(start_index, "ip.txt",
						"locations.txt");
				Thread.sleep(600000);
			} catch (Exception e) {
				P.pr("Weibo error! Pause for a 5 seconds");
				P.pr("Exception Msg: " + e.getMessage());
				e.printStackTrace();
				Thread.sleep(600000);
				try_time++;
			}
		}

		P.pl("Done");
	}

	public int getLocations(int start_index, String fileName, String locFile) {
		String ipStr;
		int i = 0;
		try {
			ipStr = FileHandler.readTXT(fileName);
			String[] ips = ipStr.split("\n");
			Location loc = new Location();
			String locStr = "";
			for (i = start_index; i < ips.length; i++) {
				String line = ips[i];
				String ip = line.split("\t")[1].replaceAll("\"", "");
//				P.pl("Processing " + ip);
				Location l = loc.getLocByIp(ip);
				locStr += l.toString();
				P.pl("============================ Writting " + i
						+ "th locations to " + locFile + "... ");
				FileHandler.appText2File(locFile, locStr, false);
				Thread.sleep(5000);
				locStr = "";
				if (i % 1000 == 0) {
					Thread.sleep(10000);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WeiboException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return i;
	}

}
