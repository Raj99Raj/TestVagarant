package StepDef;

import POM.RecentlyPlayedStore;
import io.cucumber.java.en.Given;

public class Main {
	
	@Given("Create an in-memory store for recently played songs that can accommodate N songs per user, with a fixed initial capacity")
	public void create_an_in_memory_store_for_recently_played_songs_that_can_accommodate_N_songs_per_user_with_a_fixed_initial_capacity() {
	  
	        RecentlyPlayedStore store = new RecentlyPlayedStore(5); // max 5 songs per user
	        store.addSong("alice", "song1");
	        store.addSong("bob", "song2");
	        store.addSong("alice", "song3");
	        store.addSong("bob", "song4");
	        store.addSong("alice", "song5");
	        store.addSong("bob", "song6"); // song2 should be removed from bob's list
	        store.addSong("alice", "song7"); // song6 should be removed from alice's list
	        store.addSong("bob", "song8");
	        store.addSong("bob", "song2"); // song2 should be at the beginning of bob's list now
	        store.addSong("alice", "song9"); // song3 should be removed from alice's list
	        System.out.println(store.getRecentSongs("alice", 3)); // [song9, song7, song5]
	        System.out.println(store.getRecentSongs("bob", 4)); // [song2, song8, song6, song4]
	    }
}
