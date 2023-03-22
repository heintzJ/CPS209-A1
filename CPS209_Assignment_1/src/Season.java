// Name: Jack Heintz
// Student ID: 501162746

import java.util.ArrayList;

public class Season extends AudioContent
{

    public static final String TYPENAME = "SEASON";
    ArrayList<String> episodeFiles;
    ArrayList<String> episodeTitles;
    ArrayList<Integer> episodeLengths;
    private int currentEpisode = 0;

    // constructor
    public Season(){
        episodeFiles = new ArrayList<>();
        episodeTitles = new ArrayList<>();
        episodeLengths = new ArrayList<>();
    }

    // contructor
    public Season(String title, int year, String id, String type, String audioFile, int length,
    ArrayList<String> seasons, ArrayList<String> episodeTitles, ArrayList<Integer> episodeLengths, ArrayList<String> episodeFiles) {
        super(title, year, id, type, audioFile, length);
        length = getEpisodeLength(currentEpisode);
        this.episodeTitles = episodeTitles;
        this.episodeLengths = episodeLengths;
        this.episodeFiles = episodeFiles;
    }

    public String getType(){
        return TYPENAME;
    }

    // get the current episode number
    public int getEpisodeNumber(){
        return currentEpisode;
    }

    // set episode number from the episodeTitles array list
    public void setEpisodeNumber(int num){
        System.out.println("Setting episode number");
        if (num >= 1 && num <= episodeTitles.size()){
            currentEpisode = num - 1;
        }
    }

    // get the episode length from the episodeLengths array list
    public int getEpisodeLength(int index){
        return episodeLengths.get(index-1);
    }

    // get an episode with its title and contents
    public String getEpisode(int index){
        System.out.println("In setEpisode in Season");
        if (!contains(index)){
            return "Episode does not exist";
        }
        setEpisodeNumber(index);
		return episodeTitles.get(currentEpisode) + ".\n" + episodeFiles.get(currentEpisode);
    }

    public boolean contains(int index){
		return index >= 1 && index <= episodeTitles.size();
	}

    // get an episode title
    public String episodeTitle(int index){
        return episodeTitles.get(index);
    }

    // episodes are equal if they have the same title and contents
    public boolean equals(Object other){
        Season otherPod = (Season) other;
        // getEpisode returns a string with title and contents, so if the strings are the same,
        //  then so are the episodes
        return this.getEpisode(currentEpisode).equals(otherPod.getEpisode(currentEpisode));
    }
}
