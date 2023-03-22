// Name: Jack Heintz
// Student ID: 501162746

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library
{
	private ArrayList<Song> 			songs; 
	private ArrayList<AudioBook> 	audiobooks;
	private ArrayList<Playlist> 	playlists; 
	
  	private ArrayList<Podcast> 	podcasts;
	
	// Public methods in this class set errorMesg string 
	// Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions
	String errorMsg = "ERROR";
	
	public String getErrorMessage()
	{
		return errorMsg;
	}

	public Library()
	{
		songs 		= new ArrayList<Song>(); 
		audiobooks 	= new ArrayList<AudioBook>(); ;
		playlists   = new ArrayList<Playlist>();
	  	podcasts	= new ArrayList<Podcast>();
	}
	/*
	 * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
	 * audio content (hint: use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc)
	 * to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/podcast/audiobook to a list if it is already there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
	 * See the video
	 */
	public boolean download(AudioContent content)
	{
		if (content.getType() == Song.TYPENAME){
			if (songs.indexOf((Song) content) != -1){
				errorMsg = "Song already downloaded";
				getErrorMessage();
				return false;
			}
			songs.add((Song) content);
			return true;
		}
		else if (content.getType() == AudioBook.TYPENAME){
			if (audiobooks.indexOf((AudioBook) content) != -1){
				errorMsg = "Audiobook already downloaded";
				getErrorMessage();
				return false;
			}
			audiobooks.add((AudioBook) content);
			return true;
		}
		else if (content.getType() == Podcast.TYPENAME){
			if (podcasts.indexOf((Podcast) content) != -1){
				errorMsg = "Podcast already downloaded";
				getErrorMessage();
				return false;
			}
			podcasts.add((Podcast) content);
			return true;
		}
		return true;
	}
	
	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs()
	{
		if (songs.isEmpty()){
			errorMsg = "There are no songs in the library";
			getErrorMessage();
		}
		else{
			for (int i = 0; i < songs.size(); i++)
			{
				int index = i + 1;
				System.out.print("" + index + ". ");
				songs.get(i).printInfo();
				System.out.println();	
			}
		}
	}
	
	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks()
	{
		if (audiobooks.isEmpty()){
			errorMsg = "There are no audiobooks in the library";
			getErrorMessage();
		}
		else{
			for (int i = 0; i < audiobooks.size(); i++)
			{
				int index = i + 1;
				System.out.print("" + index + ". ");
				audiobooks.get(i).printInfo();
				System.out.println();	
			}
		}
	}
	
  // Print Information (printInfo()) about all podcasts in the array list
	public void listAllPodcasts()
	{
		if (podcasts.isEmpty()){
			errorMsg = "There are no podcasts in the library";
			getErrorMessage();
		}
		else{
			for (int i = 0; i < podcasts.size(); i++)
			{
				int index = i + 1;
				System.out.print("" + index + ". ");
				podcasts.get(i).printInfo();
				System.out.println();	
			}
		}
	}
	
  // Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists()
	{
		if (playlists.isEmpty()){
			errorMsg = "There are no playlists in the library";
			getErrorMessage();
		}
		else{
			for (int i = 0; i < playlists.size(); i++){
				int index = i + 1;
				System.out.print(index + ". ");
				System.out.println(playlists.get(i).getTitle());
			}
		}
	}
	
  // Print the name of all artists. 
	public void listAllArtists()
	{
		// First create a new (empty) array list of string 
		ArrayList<String> artists = new ArrayList<String>();
		// Go through the songs array list and add the artist name to the new arraylist only if it is
		// not already there. Once the artist arraylist is complete, print the artists names
		for (Song song : songs) {
			// check if the list contains the artist
			if (artists.indexOf(song.getArtist()) == -1){
				artists.add(song.getArtist());
			}
		}
		for (int i = 0; i < artists.size(); i++) {
			System.out.println(artists.get(i));
		}
	}

	// Delete a song from the library (i.e. the songs list) - 
	// also go through all playlists and remove it from any playlist as well if it is part of the playlist
	public boolean deleteSong(int index)
	{
		if (index >= 1 && index <= songs.size()){
			// check if the song is in a playlist
			for (Playlist playlist : playlists){
				if (playlist.getContent().contains(songs.get(index-1))){
					playlist.deleteContent(playlist.getContent().indexOf(songs.get(index-1))+1);
				}
			}
			songs.remove(index-1);
			return true;
		}
		else{
			errorMsg = "Song not in library";
			return false;
		}
	}
	
  //Sort songs in library by year
	public void sortSongsByYear()
	{
		// Use Collections.sort() 
		Collections.sort(songs, new SongYearComparator());
	}
  // Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator<Song>
	{
		// compare the years of two songs
		// used in sortSongsByYear()
		public int compare(Song a, Song b){
			return a.getYear() - b.getYear();
		}

	}

	// Sort songs by length
	public void sortSongsByLength()
	{
		// Use Collections.sort() 
		Collections.sort(songs, new SongLengthComparator());
	}
  // Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator<Song>
	{
		// compare the lengths of two songs
		// used in sortSongsByYear()
		public int compare(Song a, Song b){
			return a.getLength() - b.getLength();
		}
	}

	// Sort songs by title 
	public void sortSongsByName()
	{
	  // Use Collections.sort()
		// class Song should implement the Comparable interface
		// see class Song code
		Collections.sort(songs);
	}
	
	/*
	 * Play Content
	 */
	
	// Play song from songs list
	public boolean playSong(int index)
	{
		System.out.println("calling playSong in library");
		if (index < 1 || index > songs.size()){
			errorMsg = "Song Not Found";
			return false;
		}
		else{
			songs.get(index-1).play();
			return true;
		}
		
	}
	
	// Play podcast from list (specify season and episode)
	// Bonus
	public boolean playPodcast(int index, int season, int episode)
	{
		if (index < 1 || index > podcasts.size()){
			errorMsg = "Podcast not found";
			return false;
		}
		podcasts.get(index-1).setSeason(season);
		podcasts.get(index-1).play(episode);
		return true;
	}
	
	// Print the episode titles of a specified season
	// Bonus 
	public boolean printPodcastEpisodes(int index, int season)
	{
		if (index < 1 || index > podcasts.size()){
			errorMsg = "Podcast not found";
			return false;
		}
		podcasts.get(index-1).setSeason(season);
		podcasts.get(index-1).printContents();
		return true;
	}
	
	// Play a chapter of an audio book from list of audiobooks
	public boolean playAudioBook(int index, int chapter)
	{
		if (index < 1 || index > audiobooks.size())
		{
			errorMsg = "Audiobook Not Found";
			return false;
		}
		audiobooks.get(index-1).selectChapter(chapter);
		audiobooks.get(index-1).play();
		return true;
	}
	
	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public boolean printAudioBookTOC(int index)
	{
		if (index >= 1 && index <= audiobooks.size()){
			audiobooks.get(index-1).printTOC();
			return true;
		}
		errorMsg = "Index out of range";
		return false;
	}
	
  /*
   * Playlist Related Methods
   */
	
	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public boolean makePlaylist(String title)
	{
		Playlist new_playlist = new Playlist(title);
		// check if the playlist is not in playlists array list
		if (playlists.indexOf(new_playlist) == -1){
			playlists.add(new_playlist);
			return true;
		}
		errorMsg = "That playlist already exists";
		return false;
	}
	
	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	public boolean printPlaylist(String title)
	{
		Playlist pl = new Playlist(title);
		int content = playlists.indexOf(pl);
		// if the playlist does exist:
		if (content != -1){
			playlists.get(content).printContents();
			return true;
		}
		errorMsg = "Playlist does not exist";
		return false;
	}
	
	// Play all content in a playlist
	public boolean playPlaylist(String playlistTitle)
	{
		if (playlists.size() != 0){
			for (Playlist playlist : playlists) {
				if (playlist.getTitle().equals(playlistTitle)){
					playlist.playAll();
					return true;
				}
			}
		}
		errorMsg = "Playlist does not exist";
		return false;
	}
	
	// Play a specific song/audiobook in a playlist
	public boolean playPlaylist(String playlistTitle, int indexInPL)
	{
		Playlist pl = new Playlist(playlistTitle);
		if (!playlists.contains(pl)){
			errorMsg = "Playlist does not exist";
			return false;
		}
		for (Playlist playlist : playlists){
			if (playlist.getTitle().equals(playlistTitle)){
				if (indexInPL >= 1 && indexInPL <= playlist.getContent().size()){
					System.out.println(playlistTitle);
					playlist.play(indexInPL);
					return true;
				}
				errorMsg = "Index out of range";
				return false;
			}
			errorMsg = "Content not in playlist";
			return false;
		}
		return true;
	}
	
	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	public boolean addContentToPlaylist(String type, int index, String playlistTitle)
	{
		if (type.equals(Song.TYPENAME)){
			if (index >= 1 && index <= songs.size()){
				for (Playlist playlist : playlists){
					 // find right playlist
					if (playlist.getTitle().equals(playlistTitle)){
						for (int i = 0; i < playlist.getContent().size(); i++) {
							// if song is already in playlist, return false
							if (playlist.getContent().get(i).getTitle().equals(songs.get(index-1).getTitle())){
								errorMsg = "Song already in playlist";
								return false;
							}
						}
						
					playlist.addContent(songs.get(index-1));
						return true;
					}
					else{
						errorMsg = "Playlist does not exist";
						return false;
					}
				}
			}
			else if (index < 1 || index >= songs.size()){
				errorMsg = "Index out of range";
				return false;
			}
		}
		else if (type.equals(AudioBook.TYPENAME)){
			if (index >= 1 && index <= audiobooks.size()){
				for (Playlist playlist : playlists){
					// find right playlist
					if (playlist.getTitle().equals(playlistTitle)){
						for (int i = 0; i < playlist.getContent().size(); i++) {
							// if audiobook is already in playlist, return false
							if (playlist.getContent().get(i).getTitle().equals(audiobooks.get(index-1).getTitle())){
								errorMsg = "Audiobook already in playlist";
								return false;
							}
						}
						playlist.addContent(audiobooks.get(index-1));
						return true;
					}
					else{
						errorMsg = "Playlist does not exist";
						return false;
					}
				}
			}
			else if (index < 1 || index >= audiobooks.size()){
				errorMsg = "Index out of range";
				return false;
			}
		}
		else if (type.equals(Podcast.TYPENAME)){
			if (index >= 1 && index <= podcasts.size()){
				for (Playlist playlist : playlists){
					// find right playlist
					if (playlist.getTitle().equals(playlistTitle)){
						for (int i = 0; i < playlist.getContent().size(); i++) {
							// if audiobook is already in playlist, return false
							if (playlist.getContent().get(i).getTitle().equals(podcasts.get(index-1).getTitle())){
								errorMsg = "Podcast already in playlist";
								return false;
							}
						}
						playlist.addContent(podcasts.get(index-1));
						return true;
					}
					else{
						errorMsg = "Playlist does not exist";
						return false;
					}
				}
			}
			else if (index < 1 || index >= podcasts.size()){
				errorMsg = "Index out of range";
				return false;
			}
		}
		return true;
	}

  // Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is valid 
	public boolean delContentFromPlaylist(int index, String title)
	{
		for (Playlist playlist : playlists){
			if (playlist.getTitle().equals(title)){
				playlist.deleteContent(index);
				return true;
			}
			// if index out of bounds
			else if (index > playlist.getContent().size()){
				errorMsg = "Index out of range";
				getErrorMessage();
				return false;
			}
		}
		// if title is not found
		errorMsg = "That playlist does not exist";
		getErrorMessage();
		return false;
	}
	
}
