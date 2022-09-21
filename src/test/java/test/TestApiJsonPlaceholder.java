package test;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojo.api.postdata.Post;
import pojo.api.userdata.User;
import utils.apiutils.*;
import utils.commonutils.RandomUtils;
import utils.commonutils.JsonUtil;
import configuration.TestApiDataConfiguration;
import data.Endpoint;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static io.restassured.http.ContentType.JSON;

import static org.apache.http.HttpStatus.*;

public class TestApiJsonPlaceholder extends BaseTestPlaceholder {

    private User userSelected;
   
    @Test 
    public void testCase1() {
        Response response = ApiUtilsResponses.executeGet(Endpoint.POSTS.getValue());
        Assert.assertEquals(response.getStatusCode(), SC_OK,
                "Response code should be correct");
        Assert.assertTrue(response.getContentType().contains(JSON.toString()),
                String.format("The list in response body should be json." +
                        "Expected value: %s, Actual value: %s", JSON.toString(),
                        response.getContentType()));
        List<Post> posts = Arrays.asList(JsonUtil.deserialization(response.asString(), Post[].class));
        List<Integer> id = posts.stream().map(Post::getId).collect(Collectors.toList());
        List<Integer> sortedId = id.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(id, sortedId, "Posts should be sorted ascending (by id)");
    }

    @Test
    public void testCase2() {
        Response response = ApiUtilsResponses.executeGet(Endpoint.POSTS.getValue
                (TestApiDataConfiguration.getIdRequestTestCase2()));
        Assert.assertEquals(response.getStatusCode(), SC_OK, 
                "Response code should be correct");
        Post post = JsonUtil.deserialization(response.asString(), Post.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(post.getUserId(), TestApiDataConfiguration.getUserIdTestCase2(),
                String.format("UserId should be %d", TestApiDataConfiguration.getUserIdTestCase2()));
        softAssert.assertEquals(post.getId(), TestApiDataConfiguration.getIdTestCase2(), 
                String.format("Id should be %d", TestApiDataConfiguration.getIdTestCase2()));
        softAssert.assertFalse(post.getTitle().isEmpty(), 
                String.format("Title shouldn't be empty\nActual title %s", post.getTitle()));
        softAssert.assertFalse(post.getBody().isEmpty(), 
                String.format("Body shouldn't be empty \nActual body %s", post.getBody()));
        softAssert.assertAll("Post information should be correct");
    }
    
    @Test
    public void testCase3() {
        Response response = ApiUtilsResponses.executeGet(
                Endpoint.POSTS.getValue(TestApiDataConfiguration.getIdRequestTestCase3()));
        Assert.assertEquals(response.getStatusCode(), SC_NOT_FOUND, 
                "Response code should be correct");
        Assert.assertEquals(response.getBody().asString(), TestApiDataConfiguration.getBodyTestCase3(),
                "Response body should be empty");
    }
    
    @Test
    public void testCase4() {
        Post newPost = Post.builder()
                .id(TestApiDataConfiguration.getIdTestCase4())
                .userId(TestApiDataConfiguration.getUserIdTestCase4())
                .title(RandomUtils.generateRandomString())
                .body(RandomUtils.generateRandomString())
                .build();
        Response response = ApiUtilsResponses.executePost(newPost, Endpoint.POSTS.getValue());
        Assert.assertEquals(response.getStatusCode(), SC_CREATED,
                "Response code should be correct");
        Post newPostResponse = JsonUtil.deserialization(response.asString(), Post.class);
        Assert.assertEquals(newPostResponse, newPost, 
                "Post information should match the test data");
    }

    @Test
    public void testCase5() {
        Response response = ApiUtilsResponses.executeGet(Endpoint.USERS.getValue());
        Assert.assertEquals(response.getStatusCode(), SC_OK, 
                "Response code should be correct");
        Assert.assertTrue(response.getContentType().contains(JSON.toString()), 
                String.format("The list in response body should be json." + 
                         "Expected value: %s, Actual value: %s",
                        JSON.toString(), response.getContentType()));
        List<User> users = Arrays.asList(JsonUtil.deserialization(response.asString(), User[].class));
        userSelected = users.stream().filter(e -> e.getId()== (TestApiDataConfiguration.getIdTestCase5()))
                .findFirst().orElseThrow(() -> 
                        new NoSuchElementException(String.format("User with id = %d isn't found", 
                                TestApiDataConfiguration.getIdTestCase5())));
        User userId5TestData = JsonUtil.readJsonFromFileToObject
                (TestApiDataConfiguration.getDataUserTestCase5(), User.class);
        Assert.assertEquals(userSelected, userId5TestData, 
                "Data about the selected user should match the required test data");
    }
   
    @Test(dependsOnMethods = "testCase5")
    public void testCase6() {
        Response response = ApiUtilsResponses.executeGet
                (Endpoint.USERS.getValue(TestApiDataConfiguration.getIdRequestTestCase6()));
        Assert.assertEquals(response.getStatusCode(), SC_OK, 
                "Response code should be correct");
        User userResponse = JsonUtil.deserialization(response.asString(), User.class);
        Assert.assertEquals(userResponse, userSelected, 
                "Post information should match the test data from the test case5");
    }
}
