package nl.postnl.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import nl.postnl.pom.objects.User;
import nl.postnl.pom.utils.ConfigLoader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class SignUpApi {
    private Cookies cookies;

    public Cookies getCookies(){
        return cookies;
    }

    public void setCookies(Cookies cookies) {
        this.cookies = cookies;
    }

    public String fetchRegisterNonceValueUsingGroovy(){
        Response response = getAccount();
        return response.htmlPath().getString("**.findAll { it.@name == 'woocommerce-register-nonce'}.@value");
    }

    public String fetchRegisterNonceValueUsingJSoup() {
        Response response = getAccount();
        Document doc = Jsoup.parse(response.body().prettyPrint());
        Element element = doc.selectFirst("#woocommerce-register-nonce");
        return element.attr("value");
    }

    private Response getAccount(){
        Cookies cookies = new Cookies();
        Response response = given().
                baseUri(ConfigLoader.getInstance().getBaseUrl())
                .cookies(cookies)
                .when()
                .get("/account")
                .then()
                .extract()
                .response();

        if(response.getStatusCode()!= 200){
            throw new RuntimeException("Filed to fetch the account, HTTP Status Code "+ response.getStatusCode());
        }
        return response;
    }

    public Response register(User user) {
        Cookies cookies = new Cookies();

        Header header =new Header("content-type", "application/x-www-form-urlencoded");
        Headers headers=new Headers(header);

        HashMap<String, Object> formParams=new HashMap<>();
        formParams.put("username", user.getUserName());
        formParams.put("email", user.getEmailId());
        formParams.put("password", user.getPassword());
        formParams.put("woocommerce-register-nonce", fetchRegisterNonceValueUsingJSoup());
        formParams.put("register", "Register");


		  Response response =
				  given().
				  	baseUri(ConfigLoader.getInstance().getBaseUrl()).
				  	headers(headers).
				  	formParams(formParams).
				  	cookies(cookies).
				  	log().all().
				  when()
				  	.post("/account").
				  then().
				  	log().all().extract().response();

        if(response.getStatusCode()!= 302){
            throw new RuntimeException("Filed to register the account, HTTP Status Code "+ response.getStatusCode());
        }

        //Response response=ApiRequest.post(Endpoint.ACCOUNT.url, headers, formParams, cookies);
//        Response response=ApiRequest.post("/account", headers, formParams, cookies);
//
//        VerificationManager.validateResponse(response.getStatusCode(),302,
//                FrameworkConstants.ASSERTION_FOR_RESPONSE_STATUS_CODE +" - <b> <u> Register the account </u> </b>");
//
        this.cookies=response.getDetailedCookies();
        return response;
    }

}
