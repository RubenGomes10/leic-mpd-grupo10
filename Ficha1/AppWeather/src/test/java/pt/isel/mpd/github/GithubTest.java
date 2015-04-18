package pt.isel.mpd.github;

import org.junit.Test;
import pt.isel.mpd.Github.GithubRepo;
import pt.isel.mpd.Github.GithubUser;
import pt.isel.mpd.jsonzai.JsonParser;
import pt.isel.mpd.streamUtils.IOUtils;
import pt.isel.mpd.streamUtils.UrlStreamSupplier;

import java.io.BufferedInputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class GithubTest {

    @Test
    public void assertObjectIsOk () throws Exception {

        UrlStreamSupplier responseUrl = new UrlStreamSupplier("https://api.github.com/users/achiu");
        BufferedInputStream reader = new BufferedInputStream(responseUrl.get());
        String response = IOUtils.getStringFromInputStream(reader);
        JsonParser parser = new JsonParser();

        GithubUser user = parser.<GithubUser>toObject(response, GithubUser.class);

        assertEquals(user.login,"achiu");
        assertEquals(user.id,24772);
        assertEquals(user.email,"achiu@github.com");
        assertEquals(user.location,"San Francisco, CA");
        assertEquals(user.avatar_url,'h');
        assertEquals(user.gravatar_id,"");
        assertEquals(user.site_admin,true);
    }

    @Test
    public void assertMultipleObjectIsOk() throws Exception {

        UrlStreamSupplier responseUrl = new UrlStreamSupplier("https://api.github.com/users/achiu/repos");
        BufferedInputStream reader = new BufferedInputStream(responseUrl.get());
        String response = IOUtils.getStringFromInputStream(reader);
        JsonParser parser = new JsonParser();

        List<GithubRepo> repo = parser.<GithubRepo>toList(response, GithubRepo.class);
        GithubRepo repo1 = repo.get(0);

        assertEquals(repo1.id,363183);
        assertEquals(repo1.name,"achiu.github.com");
        assertEquals(repo1.full_name,"achiu/achiu.github.com");
        assertEquals(repo1.owner.login,"achiu");
        assertEquals(repo1.owner.id,24772);
    }
}
