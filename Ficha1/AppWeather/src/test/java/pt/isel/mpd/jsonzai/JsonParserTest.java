package pt.isel.mpd.jsonzai;

import org.junit.Test;
import pt.isel.mpd.Github.GithubRepo;
import pt.isel.mpd.Github.GithubUser;
import pt.isel.mpd.streamUtils.IOUtils;
import pt.isel.mpd.streamUtils.UrlStreamSupplier;

import java.io.BufferedInputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JsonParserTest {

    String appKey = "&key=bc7c2e7a68a6f65cab1533b2c9ce9";
    String urlApi = "http://api.worldweatheronline.com/free/v2/weather.ashx?";
    String location = "q=Lisbon";
    String format = "&format=json";
    String date = "&date=2015-03-20";
    String endDate = "&enddate=2015-03-30";

    @Test
    public void assertObjectIsOk () throws Exception {
        // Arrange

        UrlStreamSupplier responseUrl = new UrlStreamSupplier("https://api.github.com/users/achiu");
        BufferedInputStream reader = new BufferedInputStream(responseUrl.get());
        String response = IOUtils.getStringFromInputStream(reader);
        JsonParser parser = new JsonParser();

        //Act

        GithubUser user = parser.<GithubUser>toObject(response, GithubUser.class);

        //Assert
        assertEquals(user.login,"achiu");
        assertEquals(user.id,24772);
        assertEquals(user.email,"achiu@github.com");
        assertEquals(user.location,"San Francisco, CA");
    }


    @Test
    public void assertMultipleObjectIsOk() throws Exception {
       /* String response = "{\n" +
                "    \"id\": 12345,\n" +
                "    \"name\": \"achiu.github.com\",\n" +
                "    \"full_name\": \"achiu/achiu.github.com\",\n" +
                "    \"owner\": {\n" +
                "      \"login\": \"achiu\",\n" +
                "      \"id\": 24772,\n" +
                "      \"email\": \"achiu@github.com\",\n" +
                "      \"location\": \"San Francisco, CA\",\n" +
                "      \"avatar_url\": \"https://avatars.githubusercontent.com/u/24772?v=3\",\n" +
                "      \"gravatar_id\": \"\",\n" +
                "      \"url\": \"https://api.github.com/users/achiu\",\n" +
                "      \"html_url\": \"https://github.com/achiu\",\n" +
                "      \"followers_url\": \"https://api.github.com/users/achiu/followers\",\n" +
                "      \"following_url\": \"https://api.github.com/users/achiu/following{/other_user}\",\n" +
                "      \"gists_url\": \"https://api.github.com/users/achiu/gists{/gist_id}\",\n" +
                "      \"starred_url\": \"https://api.github.com/users/achiu/starred{/owner}{/repo}\",\n" +
                "      \"subscriptions_url\": \"https://api.github.com/users/achiu/subscriptions\",\n" +
                "      \"organizations_url\": \"https://api.github.com/users/achiu/orgs\",\n" +
                "      \"repos_url\": \"https://api.github.com/users/achiu/repos\",\n" +
                "      \"events_url\": \"https://api.github.com/users/achiu/events{/privacy}\",\n" +
                "      \"received_events_url\": \"https://api.github.com/users/achiu/received_events\",\n" +
                "      \"type\": \"User\",\n" +
                "      \"site_admin\": true\n" +
                "    }\n" +
                "}";
*/
        // Ainda esta mal temos que ver ainda bem o toList!!! mas já lê bem da net.
        UrlStreamSupplier responseUrl = new UrlStreamSupplier("https://api.github.com/users/achiu/repos");
        BufferedInputStream reader = new BufferedInputStream(responseUrl.get());
        String response = IOUtils.getStringFromInputStream(reader);

        JsonParser parser = new JsonParser();

        List<GithubRepo> repo = parser.<GithubRepo>toList(response, GithubRepo.class);

        //Assert
        GithubRepo repo1 = repo.get(0);
        assertEquals(repo1.id,363183);
        assertEquals(repo1.name,"achiu.github.com");
        assertEquals(repo1.full_name,"achiu/achiu.github.com");


        assertEquals(repo1.owner.login,"achiu");
        assertEquals(repo1.owner.id,24772);
        //assertEquals(repo.owner.email,"achiu@github.com");
        //assertEquals(repo.owner.location,"San Francisco, CA");
    }

}

