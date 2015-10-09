# weibo-api

## Whenever error happens

1. Failed insertion SQL statements are stored inside the file `file\txt\failed-insertion-sql.txt`.
2. Those weibos whose comments are failed to be retrieved are stored in the file `\file\txt\weiboid_2000_error.txt`.
3. Those weibos with more than 2000 comments are listed in file `\file\txt\weibo-ids-with-more-than-2000-comments.txt`.
3. Weibo API call details are stored inside file `weibo.log`. This file is in very detailed!

A summary log file is stored in `file\txt\summary.txt`.

> Note: please empty these two log files before a refresh run.

## Configurations

In file `\examples\weibo4j\wang\CommentExtractor.java` top lines:
    
   	public static int PAGE_SIZE = 200;				// Number of comments to be retrieved every page
	public static int START_INDEX = 0;				// If last run you stop at 100, you can set it to be 100 for next run.
	public static int PAUSE_SECONDS_COMMENTS = 45;	// Time in seconds to pause between retrieving comments of two consecutive weibos
	public static int PAUSE_SECONDS_PAGING = 10;	// Time in seconds to pause between retrieving two pages of comments of the same weibo.

## Configure the App key and secret

In the file `src/config.properties`, fill in the common Weibo configurations:

    baseURL=https://api.weibo.com/2/
    accessTokenURL=https://api.weibo.com/oauth2/access_token
    authorizeURL=https://api.weibo.com/oauth2/authorize

Whenever a "out of rate limit" error happens, the program will switch to another access code automatically. So, please provide a list of app key/client/redirect-uri values in file `\file\txt\weibo-auth-info.txt`. This file should be like:

	2208476072 9bd6e9b16fee38f9065a2ad9c4d79727 http://www.baidu.com
	3722953941 47cf202ca308ad73869992e1b5230b60 http://www.baidu.com

Every line contains the information of some Weibo App. The three columns are App Key, App Secret and Redirect-URI respectively. NOTE: It is a *SINGLE SPACE* between the properties.

## Get the access token

1. Open the project `weibo-api` using Eclipse
2. Right click the file `examples\weibo4j\examples\oauth2\OAuth4Code.java`, choose `Run as Java Application...`.
3. On the `Console` panel, the following line will be displayed:

	https://api.weibo.com/oauth2/authorize?client_id=3722953941&redirect_uri=http://www.baidu.com&response_type=code Hit enter when it's done.[Enter]:

4. Meanwhile, your browser will open a page automatically. Here you enter your Weibo credentials and assign the permission. Then submit.
5. The page will be redirected to `http://www.baidu.com/?code=873652da20697fdc137b6585e24cae05`. The copy the string after `code`, i.e., `873652da20697fdc137b6585e24cae05`, paste to the Eclipse console and press enter key.
6. An access token will be issued like:

	AccessToken [accessToken=2.00oCIiNDN5IxDE78814a2afeBDSCHC, expireIn=157679999, refreshToken=,uid=2951008222]
7. If you provide multiple lines in the file `file\txt\weibo-auth-info.txt`, the process above will repeat for all lines.


The access tokens will be written to the file `file\txt\access-code.txt` automatically.

## Grap Weibo comments data

Before you can grap the data, please configure your local database. Here we are using MySQL. Install MySQL and create a database name `sina_weibo`. Then create a table `comment` through the SQL statement defined in file `\file\sql\create_Comment.sql`.

> **Important: in order to support Chinese characters, configure the collation to be `gbk - gbk_chinese_ci`. All columns inside the tables containing Chinese characters must be configured to be `gbk - gbk_chinese_ci` as well.**

Inside the file `examples\weibo4j\wang\db\LocalDbConnection.java` Line 45, change your database username and password.

1. Right click file `\examples\weibo4j\wang\CommentExtractor.java`, choose `Run as Java Application...`.
2. On the Eclipse console it shows: `1 - Getting comments of ...`. Then it means the retrieval starts.

If your program stops due to various reason, you can run the program again from where it stops last time. Change the value of `START_INDEX` at Line 17.

