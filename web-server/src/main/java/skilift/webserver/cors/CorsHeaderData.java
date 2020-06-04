package skilift.webserver.cors;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class CorsHeaderData {

	public static final String CREDENTIALS_NAME = "Access-Control-Allow-Credentials";
	public static final String ORIGIN_NAME = "Access-Control-Allow-Origin";
	public static final String METHODS_NAME = "Access-Control-Allow-Methods";
	public static final String HEADERS_NAME = "Access-Control-Allow-Headers";
	public static final String MAX_AGE_NAME = "Access-Control-Max-Age";
	
	public static MultiValueMap<String, String> getCorsHeaderData() {
		MultiValueMap<String, String> corsHeaderData = new LinkedMultiValueMap<String, String>();
		corsHeaderData.add(CREDENTIALS_NAME, "true");
		corsHeaderData.add(ORIGIN_NAME, "http://127.0.0.1:5500");
		corsHeaderData.add(METHODS_NAME, "GET, OPTIONS, POST, PUT, DELETE");
		corsHeaderData.add(HEADERS_NAME, "Origin, X-Requested-With, Content-Type, Accept, authorization, x-csrf-token");
		corsHeaderData.add(MAX_AGE_NAME, "3600");
		return corsHeaderData;
	}
	
}
