package weibo4j.wang;

import weibo4j.Weibo;
import weibo4j.model.PostParameter;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;
import weibo4j.util.WeiboConfig;

public class Location {
	private String ip = "";
	private String longitude = "";
	private String latitude = "";
	private String city = "";
	private String province = "";
	private String city_name = "";
	private String pinyin = "";
	private String more = "";
	private String address = "";

	/* package */public Location(String ip, JSONObject json) throws WeiboException {
		super();
		init(json);
		this.ip = ip;
	}
	
	public Location() {
		
	}

	private void init(JSONObject json) throws WeiboException {
		if (json != null) {
			try {
				String geos = json.getString("geos");
				geos = geos.replaceAll("\"", "");
				if (geos.length() < 3)
					return;
				geos = geos.substring(0, geos.length() - 2);
				geos = geos.substring(2);
				P.pl("geo: " + geos);
				String[] items = geos.split(",");
				for (int i = 0; i < items.length; i++) {
					String[] keyValue = items[i].split(":");
					if (keyValue.length < 2)
						continue;
					if(keyValue[0].equals("city_name"))
						city_name = keyValue[1];
					else if(keyValue[0].equals("ip"))
						ip = keyValue[1];
					else if(keyValue[0].equals("longitude"))
						longitude = keyValue[1];
					else if(keyValue[0].equals("latitude"))
						latitude = keyValue[1];
					else if(keyValue[0].equals("city"))
						city = keyValue[1];
					else if(keyValue[0].equals("province"))
						province = keyValue[1];
					else if(keyValue[0].equals("pinyin"))
						pinyin = keyValue[1];
					else if(keyValue[0].equals("more"))
						more = keyValue[1];
				}
			} catch (JSONException jsone) {
				throw new WeiboException(jsone.getMessage() + ":"
						+ json.toString(), jsone);
			}
		} else {
			P.pr("JSON is null");
		}
	}
	
	public String toString() {
		String str = "";
		str += ip + "\t";
		str += longitude + "\t";
		str += latitude + "\t";
		str += city + "\t";
		str += province + "\t";
		str += city_name + "\t";
		str += pinyin + "\t";
		//str += address + ", ";
		str += more + "\n";
		return str;
	}
	
	public Location getLocByIp(String ip) throws WeiboException {
		return new Location(ip, Weibo.client.get(
			WeiboConfig.getValue("baseURL") + "location/geo/ip_to_geo.json",
			new PostParameter[] { new PostParameter("ip", ip) })
			.asJSONObject());
	}
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
