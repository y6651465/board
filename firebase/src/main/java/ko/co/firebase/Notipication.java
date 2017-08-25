package ko.co.firebase;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
public class Notipication {
	private static final Logger logger = LoggerFactory.getLogger(Notipication.class);
	
	@RequestMapping("/test1")
	public void test1 () {
		logger.info("test1 controller");
		
	}
	@RequestMapping("/test2")
	public void test2 () {
		logger.info("test2 controller");
		
	}
	@RequestMapping("/map")
	public void map () {
		logger.info("map controller");
		
	}
	@RequestMapping("/translate")
	public void translate () {
		logger.info("translate controller");
		
	}
	@RequestMapping("/webrtc")
	public String webrtc () {
		logger.info("webrtcController");
		return "webrct.html";
	}
	
	
	@RequestMapping(value="/translateok", produces="application/text; charset=utf-8")
	@ResponseBody
	public String translateok (TransVO trans) {
		logger.info("test1 controller");
		  String test = "";
	      String clientId = "Bg67_lf59dWjjBN2RBxP";// 애플리케이션 클라이언트 아이디값";
	      String clientSecret = "w956Wk8kex";// 애플리케이션 클라이언트 시크릿값";
	      StringBuffer response = new StringBuffer();
	      
	      try {
	         String text = URLEncoder.encode(trans.getText(), "UTF-8");
	         String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
	         URL url = new URL(apiURL);
	         HttpURLConnection con = (HttpURLConnection) url.openConnection();
	         con.setRequestMethod("POST");
	         con.setRequestProperty("X-Naver-Client-Id", clientId);
	         con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
	         // post request\
	         switch(trans.getInput()) {
	         case "ko-KR": trans.setInput("ko");
	         	break;
	         case "en-US": trans.setInput("en");
	         	break;
	         case "cmn-Hans-CN": trans.setInput("zh-CN");
	         	break;
	         }
	         
	         
	         String postParams = "source=" + trans.getInput() + "&target=" + trans.getOutput() + "&text=" + text;
	         
	         
	         con.setDoOutput(true);
	         DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	         wr.writeBytes(postParams);
	         
	         
	         wr.flush();
	         wr.close();
	         
	         System.out.println("wr : "+wr);
	         
	         
	         int responseCode = con.getResponseCode();
	         BufferedReader br;
	         if (responseCode == 200) { // 정상 호출
	            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	         } else { // 에러 발생
	            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	         }
	         String inputLine;
	         
	         
	         while ((inputLine = br.readLine()) != null) {
	            response.append(inputLine);
	         }
	         
	         
	         br.close();
	         TranslateVO tran = new Gson().fromJson(
	                  response.toString(), TranslateVO.class
	            );
	         test = tran.getMessage().getResult().getTranslatedText();
	         System.out.println("response : "+response.toString());
	         System.out.println("result : " + test);
	         
	         return test;
	         
	      } catch (Exception e) {
	         System.out.println(e);
	         return "해석불가";
	      }

	}
	
}
