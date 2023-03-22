// Name: Jack Heintz
// Student ID: 501162746

import java.util.ArrayList;

public class Podcast extends AudioContent
{
    public static final String TYPENAME = "PODCAST";
    private String host;
    private ArrayList<Season> seasons;
    private int currentSeason = 0;

    // constructor
    public Podcast(String title, int year, String id, String type, String audioFile, int length,
        String host, ArrayList<Season> seasons){
        super(title, year, id, type, audioFile, length);
        this.host = host;
        this.seasons = seasons;
    }

    public String getType(){
        return TYPENAME;
    }

    public void printInfo(){
        super.printInfo();
		System.out.println("Host: " + host + "\nSeasons: " + seasons.size());
    }

    // play a podcast episode
    public void play(int index){
        // set the audiofile to the specified episode in the current season
        setAudioFile(seasons.get(currentSeason).getEpisode(index) + "\n");
        super.play();
    }

    public String getHost(){
        return host;
    }

    public void setHost(String host){
        this.host = host;
    }

    public int getSeason(){
        return currentSeason;
    }

    public void setSeason(int num){
        if (num >= 1 && num <= seasons.size()){
			currentSeason = num - 1;
		}
    }

    // print titles of the episodes in the current season
    public void printContents(){
        for (int i = 0; i < seasons.get(currentSeason).episodeTitles.size(); i++) {
			System.out.println(i+1 + ". " + seasons.get(currentSeason).episodeTitle(i) + "\n");
		}
    }

    // equals method just compares hosts
    public boolean equals(Object other)
	{
		String otherHost = ((Podcast) other).getHost();
		if (host.equals(otherHost)) return true;
		return false;
	}
}
