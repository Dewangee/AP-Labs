package lab7;

import java.io.Serializable;
public class song implements Serializable
{
	public String songname;
	public String artist;
	public int duration;
	
	public song(String songname,  int duration , String artist)
	{
		this.songname = songname;
		this.duration = duration;
		this.artist = artist;				
	}
	
	public String toString()
	{
		return songname.trim() + " " + Integer.toString(duration) + " " + artist.trim()  ; 
		
	}
}
