package pt.isel.mpd.jsonzai;

import org.junit.Test;
import pt.isel.mpd.Github.GitHubRepo;
import pt.isel.mpd.Github.GitHubUser;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;

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


        String response ="{\"login\": \"achiu\",\"id\":24772,\"avatar_url\":\"https://avatars.githubusercontent.com/u/24772?v=3\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/achiu\",\"html_url\":\"https://github.com/achiu\",\"followers_url\":\"https://api.github.com/users/achiu/followers\",\"following_url\":\"https://api.github.com/users/achiu/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/achiu/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/achiu/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/achiu/subscriptions\",\"organizations_url\":\"https://api.github.com/users/achiu/orgs\",\"repos_url\":\"https://api.github.com/users/achiu/repos\",\"events_url\":\"https://api.github.com/users/achiu/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/achiu/received_events\",\"type\":\"User\",\"site_admin\":true,\"name\":\"Arthur Chiu\",\"company\":\"GitHub\",\"blog\":\"\",\"location\":\"San Francisco, CA\",\"email\":\"achiu@github.com\",\"hireable\":false,\"bio\":null,\"public_repos\":51,\"public_gists\":37,\"followers\":200,\"following\":38,\"created_at\":\"2008-09-16T03:24:44Z\",\"updated_at\":\"2015-04-11T05:39:05Z\"}";

       // UrlStreamSupplier responseUrl = new UrlStreamSupplier("https://api.github.com/users/achiu");
       // BufferedReader reader = new BufferedReader(new InputStreamReader(responseUrl.get()));

       // String response = IOUtils.getStringReader(reader);
        JsonParser parser = new JsonParser();

        System.out.println(response);

        //Act

        GitHubUser user = parser.<GitHubUser>toObject(response, GitHubUser.class);

        //Assert
        assertEquals(user.login,"achiu");
        assertEquals(user.id,24772);
        assertEquals(user.email,"achiu@github.com");
        assertEquals(user.location,"San Francisco, CA");
    }


    @Test
    public void assertMultipleObjectIsOk() throws IllegalAccessException, InstantiationException {

        //3 erro dava null pois nesta string nao reparamos que faltava o email e location ao user; já acrescentei!!
        String response = "{\n" +
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

        JsonParser parser = new JsonParser();

        GitHubRepo repo = parser.<GitHubRepo>toObject(response, GitHubRepo.class);

        //Assert
        assertEquals(repo.id,12345);
        assertEquals(repo.name,"achiu.github.com");
        assertEquals(repo.full_name,"achiu/achiu.github.com");


        assertEquals(repo.owner.login,"achiu");
        assertEquals(repo.owner.id,24772);
        assertEquals(repo.owner.email,"achiu@github.com");
        assertEquals(repo.owner.location,"San Francisco, CA");
    }

}

