package lab7;

import java.io.*;
import java.util.*;

public class lab7 
{
	
	public static void main(String[] args) throws IOException, ClassNotFoundException 
	{
		Scanner sc = new Scanner(System.in);
		lab7 ob = new lab7();
		
		String playlistname = sc.next();
		playlistname += sc.nextLine();
		
		BufferedReader in	=	null;
		int numberofsongs=0;
		List<song> list = new ArrayList<song>();
		
		try
		{
			in	=	new	BufferedReader(	new FileReader("C:\\Users\\Dewangee\\Desktop\\AdvancedProgramming\\lab7\\src\\" + playlistname + ".txt"));
			String	l;	
			
			while	((l	=in.readLine()) != null)	
			{
				
				numberofsongs++;
				String[] array = l.split(" ");
				
				
				
				String name="" ,art="";
				int dur=0;
				int j=0;
				for (int i =0; i<array.length;i++)
				{
					if (array[i].charAt(0)>=48 && array[i].charAt(0)<=57)
					{
						dur =Integer.parseInt(array[i]);
						j=i+1;
						break;
					}
					else
					{
						name = name + array[i] +" ";
					}
					
				}
				
				while(j<array.length)
				{
					art = art + array[j] + " ";
					j++;
				}
				
				song bleh = new song(name, dur, art);
				list.add(bleh);
				
				
				
				
				
			}
			
		
		}
		
		finally
		{
			if (in!=null)	
				in.close();	
		}
		
		
		System.out.println("Number of songs in the playlist : " + numberofsongs);
		
		System.out.println();
		
		System.out.println("Enter any number to perform the following operations :");
		System.out.println("1. Add a song");
		System.out.println("2. Delete a song");
		System.out.println("3. Search for a song");
		System.out.println("4. Show all the songs");
		System.out.println("5. Back to main menu");
		System.out.println("6. Exit");
		
		int num=1000;
		
		while(num>0)
		{
			int query = sc.nextInt();
			
			if (query==1)
			{
				System.out.println("Enter song details :");
				String songname = sc.next() + sc.nextLine();
				int duration = sc.nextInt();
				String artist = sc.next() + sc.nextLine();
				
				song s = new song(songname, duration, artist);
				
				
				PrintWriter	out1 = null;
				try	
				{
					
					
					out1 = new PrintWriter(	new FileWriter("C:\\Users\\Dewangee\\Desktop\\AdvancedProgramming\\lab7\\src\\" + playlistname + ".txt"));
					
					for (int i=0; i<list.size(); i++)
						out1.println(list.get(i));
					out1.println(s);
				}
				
				finally
				{
					if (out1!=null)	
						out1.close();
				}
				
				list.add(s);
				numberofsongs++;
				System.out.println(numberofsongs);
				
			}
			
			else if (query==2)
			{
				System.out.println("Enter songname :");
				String sname = sc.next() + sc.nextLine();
				int j=0;
				
				for (int i=0; i<list.size(); i++)
				{
					song check = list.get(i);
					
					if (check.songname.trim().equalsIgnoreCase(sname))
					{
						j=i;
					}
				}
				
				list.remove(j);
				
				if (list.size()==numberofsongs)
					System.out.println("Song does not exist");
				else
				{
					PrintWriter	out2 = null;
					try
					{
						out2 = new PrintWriter(	new FileWriter("C:\\Users\\Dewangee\\Desktop\\AdvancedProgramming\\lab7\\src\\" + playlistname + ".txt"));
						
						for (int i=0; i<list.size(); i++)
							out2.println(list.get(i));
					}
					
					finally
					{
						if (out2!=null)
							out2.close();
					}
					
					numberofsongs--;
					System.out.println(numberofsongs);
				}
				
				
			}
			
			else if (query==3)
			{
				System.out.println("Enter songname :");
				String sname = sc.next() + sc.nextLine();
				int flag=0;
				
				for (int i=0; i<list.size(); i++)
				{
					song check = list.get(i);
					
					if (check.songname.trim().equalsIgnoreCase(sname))
					{
						System.out.println(check);
						flag=1;
					}
				}
				
				if (flag==0)
					System.out.println("Song does not exist.");
				
			}
			
			else if (query==4)
			{
				if (numberofsongs==0)
					System.out.println("No songs exist");
				else
				{
					for (int i=0; i<list.size(); i++)
						System.out.println(list.get(i));
				
				}
			
				
			}
			
			else if (query==5)
			{
				String[] files = (new File("./src")).list();
				
				for (String file : files)
				{
					if (file.contains(".txt"))
						System.out.println(file);
				}
			}
			
			else if (query==6)
			{
				 break;
			}
			
			num--;
		}
		
		
	}
	

}

