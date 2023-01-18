package Automation.Automation;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.Test;

public class spotify {
	String token = "Bearer BQAcNPbHfXQAnTMJNW6cXEG0rK3U-LRBpR0JML5tT_vu4znbzElrJ5jl_HfUrsPlMwshNBtEFTPeWzHHdcaqEHBkwOgBLeBqnJX7UMcDJZ6tcfVjQ8jboTfPqh1jsWEc3dP66-wYakZcVJBLUAd_wUlMwvTdIjj-yUw2nrT6TwI_3VGGxXtmsqtNeYd3rQVJc9q2Rwc3GxjjR2WKrC7x2bkSEAdoybxOFeR-rjJqfCx--d--790Dbt_PMk8REMN19Z_ewThGduVo3GpN";
	String User_Id;
	 String PlayList_Id;
	 @Test(priority=1)
	 public void userID() {
			Response res = given()
					.header("Accept", "aplication/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)
					.when()
					.get("https://api.spotify.com/v1/me");
				res.prettyPrint();
				res.then().statusCode(200);
				Assert.assertEquals(res.statusCode(), 200);
				String name = res.path("display_name");
				User_Id = res.path("id");
				System.out.println(name);		
		}
	@Test(priority=2)
	public void GetProfiles() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/users/"+User_Id);
			res.prettyPrint();
			res.then().statusCode(200);
			Assert.assertEquals(res.statusCode(), 200);
			String name = res.path("display_name");
			System.out.println(name);
	}
 //Playlists
	@Test(priority=3)
	public void CreatePlaylist() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.body("{\"name\":\"New Playlist\",\"description\":\"New playlist description\",\"public\":false}")
				.when()
				.post("https://api.spotify.com/v1/users/"+ User_Id +"/playlists");
			res.prettyPrint();
			PlayList_Id = res.path("id");
			System.out.println(PlayList_Id);
	}
	@Test(priority=4)
	public void AddItemsToPlaylist() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.body("{\"uris\":[\"spotify:track:4iV5W9uYEdYUVa79Axb7Rh\",\"spotify:track:1301Wley"
						+ "T98MSxVHPZCA6M\",\"spotify:episode:512ojhOuo1ktJprKbVcKyQ\"]}")
				.when()
				.post("https://api.spotify.com/v1/playlists/"+PlayList_Id +"/tracks?position=0");
			res.prettyPrint();

	}
	@Test(priority=5)
	public void Get_User_Playlist() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/users/"+ User_Id + "/playlists");
			res.prettyPrint();
	}
	@Test(priority=6)
	public void Get_Playlist() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/playlists/"+ PlayList_Id +"");
			res.prettyPrint();
	}
	@Test(priority=7)
	public void Get_Playlist_Items() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/playlists/"+ PlayList_Id +"/tracks");
			res.prettyPrint();
	}
	@Test(priority=8)
	public void Get_Playlist_Cover_Image() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/playlists/"+ PlayList_Id +"/images");
			res.prettyPrint();		
	}
	@Test(priority=9)
	public void Remove_Playlist_Items() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.body("{\"tracks\":[{\"uri\":\"spotify:track:4iV5W9uYEdYUVa79Axb7Rh\"},"
						+ "{\"uri\":\"spotify:track:1301WleyT98MSxVHPZCA6M\"}]}")
				.when()
				.delete("https://api.spotify.com/v1/playlists/"+ PlayList_Id +"/tracks");
			res.prettyPrint();
	}
	@Test(priority=10)
	public void Get_Current_User_Playlist() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/me/playlists");
			res.prettyPrint();
	}
	@Test(priority=11)
	public void Update_Playlist_Items() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.body("{\"range_start\":1,\"insert_before\":3,\"range_length\":2}")
				.when()
				.put("https://api.spotify.com/v1/playlists/4q8n5IsqvC5g0oXR7gxMNS/tracks");
			res.prettyPrint();
	}
//Tracks
	@Test(priority=12)
	public void Get_Track() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/tracks/11dFghVXANMlKmJXsNCbNl");
			res.prettyPrint();
	}
	@Test(priority=13)
	public void Get_Several_Track() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/tracks?ids=7ouMYWpwJ422jRcDASZB7P");
			res.prettyPrint();
	}
	@Test(priority=14)
	public void Get_Track_Audio_FeaturesID() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/audio-features/11dFghVXANMlKmJXsNCbNl");
			res.prettyPrint();
	}
	@Test(priority=15)
	public void Get_Track_Audio_Features() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/audio-features?ids=7ouMYWpwJ422jRcDASZB7P%2C"
						+ "4VqPOruhp5EdPBeR92t6lQ%2C2takcwOaAZWiXQijPHIx7B");
			res.prettyPrint();
	}
	@Test(priority=16)
	public void Get_Track_Audio_Analysis() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/audio-analysis/11dFghVXANMlKmJXsNCbNl");
			res.prettyPrint();
	}
	//Shows
	@Test(priority=17)
	public void Get_Show() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/shows/38bS44xjbVVZ3No3ByF1dJ");
			res.prettyPrint();
	}
	@Test(priority=18)
	public void Get_show_episods() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/shows/38bS44xjbVVZ3No3ByF1dJ/episodes");
			res.prettyPrint();
	}
//Search
	@Test(priority=19)
	public void Search_for_item() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.pathParam("q", "artist")
				.pathParam("type", "track")
				.when()
				.get("https://api.spotify.com/v1/search?q={q}&type={type}");
			res.prettyPrint();
	}
//Player 
	@Test(priority=20)
	public void Get_Available_Device() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/me/player/devices");
			res.prettyPrint();
	}
	@Test(priority=21)
	public void Get_current_playing_track() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/me/player/currently-playing");
			res.prettyPrint();
	}
//Markets 
	@Test(priority=22)
	public void Get_available_markets() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/markets");
			res.prettyPrint();
	}
//Follow 
	@Test(priority=23)
	public void Get_followed_artists() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/me/following?type=artist&after=0I2XqVXqHScXjHhk6AYYRe");
			res.prettyPrint();
	}
	@Test(priority=24)
	public void Check_user_follow_playlist() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/playlists/3cEYpjA9oz9GiPac4AsH4n/followers/contains?ids=jmperezperez");
			res.prettyPrint();
	}
	@Test(priority=25)
	public void Follow_playlist() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.body("{\"public\":false}")
				.when()
				.put("https://api.spotify.com/v1/playlists/4q8n5IsqvC5g0oXR7gxMNS/followers");
			res.prettyPrint();
	}
	@Test(priority=26)
	public void unfollow_playlist() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.delete("https://api.spotify.com/v1/playlists/3cEYpjA9oz9GiPac4AsH4n/followers");
			res.prettyPrint();
	}
//Episode 
	@Test(priority=27)
	public void Get_episode() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/episodes/512ojhOuo1ktJprKbVcKyQ");
			res.prettyPrint();
	}
	@Test(priority=28)
	public void Get_several_episode() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/episodes?ids=77o6BIVlYM3msb4MMIL1jH");
			res.prettyPrint();
	}
//Chapters
	@Test(priority=29)
	public void Get_several_chapters() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/chapters?ids=7ouMYWpwJ422jRcDASZB7P");
		res.prettyPrint();
	}
//Browse 
	@Test(priority=30)
	public void Get_available_genre_seeds() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/recommendations/available-genre-seeds");
		res.prettyPrint();
	}
	@Test(priority=31)
	public void Get_several_browse_categories() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/browse/categories");
			res.prettyPrint();
	}
	@Test(priority=32)
	public void Get_single_browse_category() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/browse/categories/dinner");
			res.prettyPrint();
	}
	@Test(priority=33)
	public void Get_category_playlist() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/browse/categories/dinner/playlists");
			res.prettyPrint();
	}
	@Test(priority=34)
	public void Get_featured_playlist() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/browse/featured-playlists");
			res.prettyPrint();
	}
	@Test(priority=35)
	public void Get_new_realease() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/browse/new-releases");
			res.prettyPrint();
	}
	@Test(priority=36)
	public void Get_recommendations() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/recommendations?seed_artists=4NHQUGzhtTLFvgF5SZesLK&"
						+ "seed_genres=classical%2Ccountry&seed_tracks=0c6xIDDpzE81m2q797ordA");
			res.prettyPrint();
	}
//Audio book
	@Test(priority=37)
	public void GetAudioBook()
	{
		Response res = given()
				.header("Accept","application/json")
				.header("Content-Type","application/json")
				.header("Authorization",token)
				.when()
				.get("https://api.spotify.com/v1/audiobooks/38bS44xjbVVZ3No3ByF1dJ");
		res.prettyPrint();
	}
	@Test(priority=38)
	public void Get_several_audiobook() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/audiobooks?ids=7ouMYWpwJ422jRcDASZB7P%2C4VqP"
						+ "Oruhp5EdPBeR92t6lQ%2C2takcwOaAZWiXQijPHIx7B");
			res.prettyPrint();
	}
	
	//Albums
		@Test(priority=39)
		public void Get_album_track() {
			Response res = given()
					.header("Accept", "aplication/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)
					.when()
					.get("https://api.spotify.com/v1/albums/4aawyAB9vmqN3uQ7FjRGTy/tracks");
				res.prettyPrint();	
		}
		@Test(priority=40)
		public void Get_album() {
			Response res = given()
					.header("Accept", "aplication/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)
					.when()
					.get("https://api.spotify.com/v1/albums/4aawyAB9vmqN3uQ7FjRGTy");
				res.prettyPrint();	
		}
		@Test(priority=41)
		public void Get_several_album() {
			Response res = given()
					.header("Accept", "aplication/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)
					.when()
					.get("https://api.spotify.com/v1/albums?ids=382ObEPsp2rxGrnsizN5TX");
				res.prettyPrint();
		}
		
//Artists 
	@Test(priority=42)
	public void Get_artist_albums() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myaseg/albums");
			res.prettyPrint();
	}
	@Test(priority=43)
	public void Get_artist_related_artist() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myaseg/related-artists");
			res.prettyPrint();
	}
	@Test(priority=44)
	public void Get_artist_top_tracks() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myaseg/top-tracks?market=ES");
			res.prettyPrint();
	}
	@Test(priority=45)
	public void Get_artist() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myaseg");
			res.prettyPrint();
	}
	@Test(priority=46)
	public void Get_several_artist() {
		Response res = given()
				.header("Accept", "aplication/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/artists?ids=2CIMQHirSU0MQqyYHq0eOx");
			res.prettyPrint();
	}
@Test
public void searchQuery()
{
	Response res = given()
			.header("Accept", "aplication/json")
			.header("Content-Type", "application/json")
			.header("Authorization", token)
			.queryParam("q","artist")
			.queryParam("type","track")
			.when()
			.get("https://api.spotify.com/v1/search?q={q}&type={type}");
	res.prettyPrint();
}
			
			
}