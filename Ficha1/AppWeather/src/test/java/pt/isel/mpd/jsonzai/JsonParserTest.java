package pt.isel.mpd.jsonzai;

import org.junit.Test;
import pt.isel.mpd.Github.GithubUser;

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

        String response ="{\"login\":\"achiu\",\"id\":24772,\"avatar_url\":\"https://avatars.githubusercontent.com/u/24772?v=3\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/achiu\",\"html_url\":\"https://github.com/achiu\",\"followers_url\":\"https://api.github.com/users/achiu/followers\",\"following_url\":\"https://api.github.com/users/achiu/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/achiu/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/achiu/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/achiu/subscriptions\",\"organizations_url\":\"https://api.github.com/users/achiu/orgs\",\"repos_url\":\"https://api.github.com/users/achiu/repos\",\"events_url\":\"https://api.github.com/users/achiu/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/achiu/received_events\",\"type\":\"User\",\"site_admin\":true,\"name\":\"Arthur Chiu\",\"company\":\"GitHub\",\"blog\":\"\",\"location\":\"San Francisco, CA\",\"email\":\"achiu@github.com\",\"hireable\":false,\"bio\":null,\"public_repos\":51,\"public_gists\":37,\"followers\":200,\"following\":38,\"created_at\":\"2008-09-16T03:24:44Z\",\"updated_at\":\"2015-04-11T05:39:05Z\"}";



        System.out.println(response);

        JsonParser parser = new JsonParser();

        //Act

        GithubUser user = parser.<GithubUser>toObject(response, GithubUser.class);

        //Assert
        assertEquals(user.login,"achiu");
        assertEquals(user.id,24772);
        assertEquals(user.email,"achiu@github.com");
        assertEquals(user.location,"San Francisco, CA");
    }



}
