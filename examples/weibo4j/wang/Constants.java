package weibo4j.wang;


/**
 * Some Constants
 * 
 * @author Carl
 * @6:36:40 PM @Sep 9, 2009
 * @GGLL
 */
public final class Constants {
	
	/** 
	 * App Key锛�274853287
	 * App Secret锛�79b3424e80cbe770da5af082df40cce1 
	 * 
	 * App Key锛�3996047685 
	 * App Secret锛�0eade751f488d7d358e00824133e21d6 
    **/

    // The default path for files.
    public static final String FILEPATH = System.getProperty("user.dir")
            + "/file/";
    public static String TXTFILEPATH = System.getProperty("user.dir")
            + "/file/txt/";
    public static String HTMLFILEPATH = System.getProperty("user.dir")
            + "/file/html/";
    public static String XMLFILEPATH = System.getProperty("user.dir")
    + "/file/xml/";
    
    // The default enconding.
    public static final String ENCODE_DEFAULT = "GB2312";
    
    public static final Integer N_RECORD_ON_PAGE = 199;
    
    public static final Integer MAX_USERS = 5000;
    
    public static final Integer MAX_FRIENDS = 2000;
    
    public static final Integer N_RECORD_ON_USER_PAGE = 199; // Bug in Weibo API: if reaches exactly 200, next_cursor will return 0
}
