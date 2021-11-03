package organicfood.recaptcha;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
public class RecaptchaVerification {
	
	public static final String verificationurl = "https://www.google.com/recaptcha/api/siteverify";
	  public static final String secretkey = "6Le4CwAdAAAAALoHutl4aOB2VC5sHFXqa1RGBUxC";
	  private final static String USER_AGENT = "Mozilla/5.0";

	  public static boolean verify(String gRecaptchaResponse) throws IOException {
	    if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
	      return false;
	    }
	    
	    try{
	    URL obj = new URL(verificationurl);
	    HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

	    // add reuqest header
	    con.setRequestMethod("POST");
	    con.setRequestProperty("User-Agent", USER_AGENT);
	    con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

	    String postParams = "secret=" + secretkey + "&response="
	        + gRecaptchaResponse;

	    // Send post request
	    con.setDoOutput(true);
	    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	    wr.writeBytes(postParams);
	    wr.flush();
	    wr.close();

	    int responseCode = con.getResponseCode();
	    
	    BufferedReader in = new BufferedReader(new InputStreamReader(
	        con.getInputStream()));
	    String inputLine;
	    StringBuffer response = new StringBuffer();

	    while ((inputLine = in.readLine()) != null) {
	      response.append(inputLine);
	    }
	    in.close();
	    JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
	    JsonObject jsonObject = jsonReader.readObject();
	    jsonReader.close();
	    
	    return jsonObject.getBoolean("success");
	    }catch(Exception e){
	      e.printStackTrace();
	      return false;
	    }
	  }
	
	
	

}
