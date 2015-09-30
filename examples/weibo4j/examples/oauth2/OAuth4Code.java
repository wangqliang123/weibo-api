package weibo4j.examples.oauth2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import weibo4j.Oauth;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;
import weibo4j.util.BareBonesBrowserLaunch;
import weibo4j.wang.Constants;
import weibo4j.wang.FileHandler;
import weibo4j.wang.WeiboAuthInfo;

public class OAuth4Code {
    public static void main(String[] args) throws WeiboException, IOException {
        OAuth4Code o = new OAuth4Code();
        String lines = FileHandler.readTXT(Constants.TXTFILEPATH + "weibo-auth-info.txt");
        WeiboAuthInfo info;
        String[] fields;
        FileHandler.saveText2Txt("", Constants.TXTFILEPATH + "access-code.txt", true);

        for (String line : lines.split("\n")) {
            line = line.trim();
            fields = line.split(" ");
            info = new WeiboAuthInfo(fields[0], fields[1], fields[2], "");
            o.getAccessToken(info);
            System.out.println("----------------------------");
        }
    }

    public void getAccessToken(WeiboAuthInfo info) throws WeiboException, IOException {
        Oauth oauth = new Oauth();
        BareBonesBrowserLaunch.openURL(oauth.authorize(info, "code"));
        System.out.print("Hit enter when it's done.[Enter]:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String code = br.readLine();
        System.out.println("code: " + code);
        try {
            String token = oauth.getAccessTokenByCode(info, code);
            System.out.println(token);
            FileHandler.appText2File(Constants.TXTFILEPATH + "access-code.txt", token + "\r\n");
        } catch (WeiboException e) {
            if (401 == e.getStatusCode()) {
                Log.logInfo("Unable to get the access token.");
            } else {
                e.printStackTrace();
            }
        }
    }

}
