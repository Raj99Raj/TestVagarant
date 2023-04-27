package POM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RecentlyPlayedStore {
	 private final int capacity;
	    private Map<String, LinkedList<String>> songMap; // map from user to list of songs
	    private Map<String, Long> timestampMap; // map from song to timestamp

	    public RecentlyPlayedStore(int capacity) {
	        this.capacity = capacity;
	        this.songMap = new HashMap<>();
	        this.timestampMap = new HashMap<>();
	    }

	    public synchronized void addSong(String user, String song) {
	        LinkedList<String> songList = songMap.computeIfAbsent(user, k -> new LinkedList<>());
	        songList.remove(song); // remove if already present
	        songList.addFirst(song); // add at the beginning
	        timestampMap.put(song, System.currentTimeMillis()); // update timestamp
	        if (songList.size() > capacity) {
	            String removedSong = songList.removeLast(); // remove least recently played song
	            timestampMap.remove(removedSong); // remove its timestamp
	        }
	    }

	    public synchronized List<String> getRecentSongs(String user, int count) {
	        LinkedList<String> songList = songMap.get(user);
	        if (songList == null) {
	            return Collections.emptyList();
	        }
	        List<String> result = new ArrayList<>(count);
	        for (String song : songList) {
	            if (result.size() >= count) {
	                break;
	            }
	            result.add(song);
	        }
	        return result;
	    }

	    public synchronized void clear() {
	        songMap.clear();
	        timestampMap.clear();
	    }

	    private synchronized void removeOldSongs() {
	        long now = System.currentTimeMillis();
	        List<String> songsToRemove = new ArrayList<>();
	        for (Map.Entry<String, Long> entry : timestampMap.entrySet()) {
	            if (now - entry.getValue() > 3600 * 1000) { // older than 1 hour
	                songsToRemove.add(entry.getKey());
	            }
	        }
	        for (String song : songsToRemove) {
	            timestampMap.remove(song);
	        }
	    }
}
