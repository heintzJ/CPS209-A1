// Name: Jack Heintz
// Student ID: 501162746

import java.util.ArrayList;
/*
 * A Playlist contains an array list of AudioContent (i.e. Song, AudioBooks, Podcasts) from the library
 */
public class Playlist
{
	private String title;
	private ArrayList<AudioContent> contents; // songs, books, or podcasts or even mixture
	
	public Playlist(String title)
	{
		this.title = title;
		contents = new ArrayList<AudioContent>();
	}
	
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void addContent(AudioContent content)
	{
		contents.add(content);
	}
	
	public ArrayList<AudioContent> getContent()
	{
		return contents;
	}

	public void setContent(ArrayList<AudioContent> contents)
	{
		this.contents = contents;
	}
	
	/*
	 * Print the information of each audio content object (song, audiobook, podcast)
	 * in the contents array list. Print the index of the audio content object first
	 * followed by ". " then make use of the printInfo() method of each audio content object
	 * Make sure the index starts at 1
	 */
	public void printContents()
	{
		for (int i = 0; i < contents.size(); i++) {
			System.out.print(i+1 + ". ");
			contents.get(i).printInfo();
		}
	}

	// Play all the AudioContent in the contents list
	public void playAll()
	{
		for (int i = 0; i < contents.size(); i++) {
			// if a podcast is in the playlist, it will default to playing the first episode of season 1
			if (contents.get(i).getType().equals(Podcast.TYPENAME)){
				Podcast p = (Podcast) contents.get(i);
				p.play(i+1);
			}
			else contents.get(i).play();
		}
	}
	
	// Play the specific AudioContent from the contents array list.
	// First make sure the index is in the correct range. 
	public void play(int index)
	{
		if (!contains(index)) return;
		if (contents.get(index-1).getType().equals(Podcast.TYPENAME)){
			Podcast p = (Podcast) contents.get(index-1);
			p.play(index);
		}
		else contents.get(index-1).play();
	}
	
	public boolean contains(int index)
	{
		return index >= 1 && index <= contents.size();
	}
	
	// Two Playlists are equal if their titles are equal
	public boolean equals(Object other)
	{
		String otherTitle = ((Playlist) other).getTitle();
		if (title.equals(otherTitle)) return true;
		return false;
	}
	
	// Given an index of an audio content object in contents array list,
	// remove the audio content object from the array list
	// Hint: use the contains() method above to check if the index is valid
	// The given index is 1-indexed so convert to 0-indexing before removing
	public void deleteContent(int index)
	{
		if (!contains(index)) return;
		contents.remove(index-1);
	}
	
	
}
