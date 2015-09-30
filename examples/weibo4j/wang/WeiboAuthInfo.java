package weibo4j.wang;

/**
 * Created by carl.cai on 9/30/2015.
 */
public class WeiboAuthInfo {
    private String appKey = "";
    private String appSecret = "";
    private String accessCode = "";
    private String redirectURI = "";

    public WeiboAuthInfo(String appKey, String appSecret, String redirectURI, String accessCode) {
        this.appKey = appKey.trim();
        this.appSecret = appSecret.trim();
        this.accessCode = accessCode.trim();
        this.redirectURI = redirectURI.trim();
    }

    public String toString() {
        System.out.println("App key: " + this.appKey);
        System.out.println("App secret: " + this.appSecret);
        System.out.println("Redirect URI: " + this.redirectURI);
        return "";
    }

    public String getRedirectURI() {
        return redirectURI;
    }

    public void setRedirectURI(String redirectURI) {
        this.redirectURI = redirectURI;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

}
