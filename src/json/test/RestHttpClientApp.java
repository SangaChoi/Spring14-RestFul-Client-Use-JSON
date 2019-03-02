package json.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import spring.domain.User;

public class RestHttpClientApp {

	public static void main(String[] args) throws Exception{
		
		//1.1
		//RestHttpClientApp.RequestHttpGet_UseJsonSimple();
		//1.2
		//RestHttpClientApp.RequestHttpGet_UseCodeHaus();
		//2.1
		//RestHttpClientApp.RequestHttpPostData_UseJsonSimple();
		//2.2
		RestHttpClientApp.RequestHttpPostData_UseCodeHaus();

	}

	public static void RequestHttpGet_UseJsonSimple() throws Exception{
		HttpClient httpClient=new DefaultHttpClient();
		
		String url="http://127.0.0.1:8080/Spring14/user/json/user01?name=user02&age=10";
		
		HttpGet httpGet=new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		HttpResponse httpResponse=httpClient.execute(httpGet);
		
		System.out.println(httpResponse);
		System.out.println();
		
		HttpEntity responsHttpEntity=httpResponse.getEntity();
		
		InputStream is=responsHttpEntity.getContent();
		BufferedReader br=new BufferedReader(new InputStreamReader(is, "UTF-8"));
		
		System.out.println("[Server에서 받은 data 확인]");
		String serverData=br.readLine();
		System.out.println(serverData);
		
		JSONObject jsonObj=(JSONObject) JSONValue.parse(serverData);
		System.out.println(jsonObj);		
	}
	
	public static void RequestHttpGet_UseCodeHaus() throws Exception{
		
		HttpClient httpClient=new DefaultHttpClient();
		
		String url="http://127.0.0.1:8080/Spring14/user/json/user01?name=user02&age=10";
		
		HttpGet httpGet=new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		HttpResponse httpResponse=httpClient.execute(httpGet);
		
		System.out.println(httpResponse);
		System.out.println();
		
		HttpEntity responsHttpEntity=httpResponse.getEntity();
		
		InputStream is=responsHttpEntity.getContent();
		BufferedReader br=new BufferedReader(new InputStreamReader(is, "UTF-8"));
		
		System.out.println("[Server에서 받은 data 확인]");
		String serverData=br.readLine();
		System.out.println(serverData);
		
		JSONObject jsonObj=(JSONObject) JSONValue.parse(serverData);
		
		ObjectMapper objectMapper=new ObjectMapper();
		User user=objectMapper.readValue(jsonObj.get("user").toString(), User.class);
		System.out.println(user);	
	}

	public static void RequestHttpPostData_UseJsonSimple() throws Exception{
		
		HttpClient httpClient=new DefaultHttpClient();
		
		String url="http://127.0.0.1:8080/Spring14/user/json/getUser/user01";
		
		HttpPost httpPost=new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		//String data="{\"userId\":\"test\",\"userName\":\"홍길동\"}";
		
		JSONObject json=new JSONObject();
		json.put("userId", "test");
		json.put("userName", "홍길동");
		
		HttpEntity requestHttpEntity=new StringEntity(json.toString(),"UTF-8");
		httpPost.setEntity(requestHttpEntity);
		
		HttpResponse httpResponse=httpClient.execute(httpPost);
		
		System.out.println(httpResponse);
		System.out.println();
		
		HttpEntity responseHttpEntity=httpResponse.getEntity();
		
		InputStream is=responseHttpEntity.getContent();
		BufferedReader br=new BufferedReader(new InputStreamReader(is, "UTF-8"));
		
		System.out.println("[Server에서 받은 data 확인]");
		String serverData=br.readLine();
		System.out.println(serverData);
		
		JSONObject jsonObj=(JSONObject) JSONValue.parse(serverData);
		System.out.println(jsonObj);	
	}
	

	public static void RequestHttpPostData_UseCodeHaus() throws Exception{
		
		HttpClient httpClient=new DefaultHttpClient();
		
		String url="http://127.0.0.1:8080/Spring14/user/json/getUser/user01";
		
		HttpPost httpPost=new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		//String data="{\"userId\":\"test\",\"userName\":\"홍길동\"}";
		
		//JSONObject json=new JSONObject();
		//json.put("userId", "test");
		//json.put("userName", "홍길동");
		
		User user=new User("test","홍길동","1111",null,10);
		ObjectMapper objectMapper01=new ObjectMapper();
		String jsonValue=objectMapper01.writeValueAsString(user);
		
		HttpEntity requestHttpEntity=new StringEntity(jsonValue,"UTF-8");
		httpPost.setEntity(requestHttpEntity);
		
		HttpResponse httpResponse=httpClient.execute(httpPost);
		
		System.out.println(httpResponse);
		System.out.println();
		
		HttpEntity responseHttpEntity=httpResponse.getEntity();
		
		InputStream is=responseHttpEntity.getContent();
		BufferedReader br=new BufferedReader(new InputStreamReader(is, "UTF-8"));
		
		System.out.println("[Server에서 받은 data 확인]");
		String serverData=br.readLine();
		System.out.println(serverData);
		
		JSONObject jsonObj=(JSONObject) JSONValue.parse(serverData);

		ObjectMapper objectMapper=new ObjectMapper();
		User returnUser=objectMapper.readValue(jsonObj.get("user").toString(),User.class);
		System.out.println(returnUser);		
	}
}
