# weibo-api

## Configure the App key and secret

In the file `src/config.properties`, fill in the app key and secret:

client_ID=3722953941
client_SERCRET=47cf202ca308ad73869992e1b5230b60
redirect_URI=http://www.baidu.com
baseURL=https://api.weibo.com/2/
accessTokenURL=https://api.weibo.com/oauth2/access_token
authorizeURL=https://api.weibo.com/oauth2/authorize

The `redirect_UIR` must be the one specified in the Weibo App

## Get the access token

1. Open the project `weibo-api` using Eclipse
2. Right click the file `examples\weibo4j\examples\oauth2\OAuth4Code.java`, choose `Run as Java Application...`.
3. On the `Console` panel, the following line will be displayed:

> https://api.weibo.com/oauth2/authorize?client_id=3722953941&redirect_uri=http://www.baidu.com&response_type=code Hit enter when it's done.[Enter]:

4. Meanwhile, your browser will open a page automatically. Here you enter your Weibo credentials and assign the permission. Then submit.
5. The page will be redirected to `http://www.baidu.com/?code=873652da20697fdc137b6585e24cae05`. The copy the string after `code`, i.e., `873652da20697fdc137b6585e24cae05`, paste to the Eclipse console and press enter key.
6. An access token will be issued like:

> AccessToken [accessToken=2.00oCIiNDN5IxDE78814a2afeBDSCHC, expireIn=157679999, refreshToken=,uid=2951008222]

Then we get the access token to call Weibo APIs. This token expires in some time. And the API call rate is limited to the Weibo App type.

## Grap Weibo comments data

Before you can grap the data, please configure your local database. Here we are using MySQL. Install MySQL and create a database name `sina_weibo`. Then create a table `comment` through the SQL statement defined in file `\file\sql\create_Comment.sql`.

Inside the file `examples\weibo4j\wang\db\LocalDbConnection.java` Line 45, change your database username and password.

1. Copy this `accessToken` value and paste to file `\examples\weibo4j\wang\ExtractInfo.java` Line 30.
2. Right click file `\examples\weibo4j\wang\ExtractInfo.java`, choose `Run as Java Application...`.
3. On the Eclipse console it shows: `1 - Getting comments of ...`. Then it means the retrieval starts.

If your program stops due to various reason, you can run the program again from where it stops last time. Change the value of `start` at Line 39.

Those weibos whose comments are failed to be retrieved are stored in the file `\file\txt\weiboid_2000_error.txt`.