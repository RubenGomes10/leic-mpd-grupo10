package pt.isel.mpd.jsonzai;

import org.junit.Test;
import pt.isel.mpd.Github.GitHubUser;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ruben Gomes on 31/03/2015.
 */
public class JsonParserTest {

    String appKey = "&key=bc7c2e7a68a6f65cab1533b2c9ce9";
    String urlApi = "http://api.worldweatheronline.com/free/v2/weather.ashx?";
    String location = "q=Lisbon";
    String format = "&format=json";
    String date = "&date=2015-03-20";
    String endDate = "&enddate=2015-03-30";

    @Test
    public void assertObjectIsOk () throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        // Arrange
     /*   UrlStreamSupplier responseUrl = new UrlStreamSupplier(urlApi+location+format+appKey);
        InputStream x = responseUrl.get();


        String response = "" +x;
        */

        String response ="{ \n" +
                "login: \"achiu\", \n" +
                "id   : 24772, \n" +
                "type: \"User\", \n" +
                "name: \"Arthur Chiu\", \n" +
                "company: \"GitHub\", \n" +
                "location: \"San Francisco, CA\", \n" +
                "email: \"achiu@github.com\", \n" +
                "hireable: false, \n" +
                "public_repos: 52, \n" +
                "public_gists: 37, \n" +
                "followers: 198, \n" +
                "following: 38, \n" +
                "created_at: \"2008-09-16T03:24:44Z\", \n" +
                "updated_at: \"2015-03-26T06:29:14Z\" \n" +
                "}\n";

        System.out.println(response);

        JsonParser parser = new JsonParser();

        //Act

        GitHubUser user = parser.<GitHubUser>toObject(response, GitHubUser.class);

        //Assert
        assertEquals(user.login,"achiu");
        assertEquals(user.id,24772);
        assertEquals(user.email,"achiu@github.com");
        assertEquals(user.location,"San Francisco, CA");
    }



}
