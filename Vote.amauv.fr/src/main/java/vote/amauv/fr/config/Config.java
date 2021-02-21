package vote.amauv.fr.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Config {
	private static Connection con = null;
	public static String getUrl() throws IOException {
		InputStream inStream = Config.class.getResourceAsStream("/database.properties");
		Properties prop = new Properties();
		prop.load(inStream);
		String type = prop.getProperty("type");
		String host = prop.getProperty("host");
		String port = prop.getProperty("port");
		String databaseName = prop.getProperty("databaseName");
		String userName = prop.getProperty("userName");
		String passWord = prop.getProperty("passWord");
		return "jdbc:"+type+"://"+host+":"+port+"/"+databaseName+"?user="+userName+"&password="+passWord;
	}
	public static Connection getCon() throws Exception {
		if (con == null)
			con = DriverManager.getConnection(getUrl());
		return con;
	}
	
	private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for (int j = 0; j < bytes.length; j++) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = (char)HEX_ARRAY[v >>> 4];
	        hexChars[j * 2 + 1] = (char)HEX_ARRAY[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
}
