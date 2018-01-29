package lab6;
import java.io.*;
import java.util.*;

public class lab6 {
	
	static int queenx;
	static int queeny;

	public static void main(String[] args) throws FileNotFoundException,IOException
	{
		PrintWriter w = new PrintWriter("./src/" + "ress.txt", "UTF-8");
		lab6 lobj = new lab6();
		Scanner scan = new Scanner(System.in);
		int knightnum = scan.nextInt();
		int iter = scan.nextInt();	
		lobj.queenx = scan.nextInt();
		lobj.queeny = scan.nextInt();
		ArrayList<knight> knightlist = new ArrayList<knight>(knightnum);
		
		for (int t=1; t<=knightnum; t++)
		{
			Scanner sc = new Scanner(new FileReader("C:\\Users\\Dewangee\\Desktop\\AdvancedProgramming\\lab6cse\\Lab 6\\Test Case\\Input\\" + t+".txt"));
			String s="";
	        while (sc.hasNextLine()) 
	            s = s+ sc.nextLine() + "\n";
	        String[] arr = s.split("\n");
	        
	        String[] arr1 = arr[1].split(" ");
	        String kname = arr[0];
	        int kx = Integer.parseInt(arr1[0]);
	        int ky = Integer.parseInt(arr1[1]);
	        int m = Integer.parseInt(arr[2]);
	        
	        Stack<Object> stack = new Stack<Object>();
	        
	        
	        for (int t1 =0; t1<m ; t1++)
	        {
	        	String[] arr2 = arr[3+t1].split(" ");
	        	String type = arr2[0];
	        	item itemobj = null;
	        	
	        	if (type.equalsIgnoreCase("String"))
	        	{
	        		String value = arr2[1];
	        		itemobj = new item(arr2[0], value);
	        		stack.push(itemobj);
	        	}
	        	else if (type.equalsIgnoreCase("Integer"))
	        	{
	        		int value = Integer.parseInt(arr2[1]);
	        		itemobj = new item(arr2[0], value);
	        		stack.push(itemobj);
	        	}
	        	else if (type.equalsIgnoreCase("Float"))
	        	{
	        		float value = Float.parseFloat(arr2[1]);
	        		itemobj = new item(arr2[0], value);
	        		stack.push(itemobj);
	        	}
	        	else if (type.equalsIgnoreCase("Coordinate"))
	        	{
	        		int x = Integer.parseInt(arr2[1]);
	        		int y = Integer.parseInt(arr2[2]);
	        		coordinate cobj = new coordinate(arr2[0],x, y);
	        		stack.push(cobj);
	        	}
	        	
	        }
	        knight kobj = new knight(kname, kx , ky, m, stack );
	        knightlist.add(kobj);
	        
	        /*  for (int i=0; i<m; i++)
	        {
	        	System.out.println(stack.pop());
	        } */
		}
		Collections.sort(knightlist);
		/*
		for (int i=0; i<knightnum; i++)
        {
        	System.out.println(knightlist.get(i).kname);
        }
        */
		int iterno =1;
		coordinate o=null;
		item oi = null; 
		while (iterno<=iter && knightlist.size()!=0)
		{
			for (int j=0; j<knightlist.size(); j++)
			{
				try
				{
					oi = (item)knightlist.get(j).stack.pop();
					//System.out.println(oi.type);
					//o = (coordinate)oi;
					checkoverlap(w,iterno, knightlist , oi, knightlist.get(j));
					
					checkcoordinate(w,iterno,knightlist.get(j),oi);
					
					checkqueen (w,iterno,knightlist.get(j));
					
						
				}	
				catch(EmptyStackException e)
				{
					w.println(iterno + " " + knightlist.get(j).kname + " " + knightlist.get(j).kx+ " " + knightlist.get(j).ky);
					w.println("StackEmptyException: Stack Empty exception");
					knightlist.remove(j);
				}
				
				catch(NonCoordinateException e)
				{
					w.println(e.getMessage());	
				}
				
				catch(QueenFoundException e)
				{
					
					w.println(e.getMessage());
					iter=0;
					break;
				}

				catch(OverlapException e)
				{
					
					w.println(e.getMessage());
					
				}
				
			}
			iterno++;
		}
			
		
		
		w.close();
		
	}
	
	static void checkcoordinate (PrintWriter w, int iterno,knight k,item itemobj) throws NonCoordinateException
	{
		if (itemobj.type.equalsIgnoreCase("Coordinate"))
		{
			w.println(iterno + " " + k.kname + " " + k.kx+ " " + k.ky);
			coordinate cobj = (coordinate)itemobj;
			k.setx(cobj.kx);
			k.sety(cobj.ky);
			if (k.kx==queenx && k.ky==queeny)
			{
				
			}
			else
			{
				w.println("No exception" + " " + cobj.kx + " "  + cobj.ky);
			
			}

			
			
			
		
		}
		else
		{
			w.println(iterno + " " + k.kname + " " + k.kx+ " " + k.ky);
			throw new NonCoordinateException("NonCoordinateException: Not a coordinate Exception " + itemobj.value);
		}
	}
	
	static void checkqueen (PrintWriter w,int iter,knight k) throws QueenFoundException
	{
		
		if (k.kx==queenx && k.ky==queeny)
		{
			//w.println(iter + " " + k.kname + " " + k.kx+ " " + k.ky);
			throw new QueenFoundException("QueenFoundException: Queen has been Found. Abort!");
	
		}
		
	}
	
	static void checkoverlap(PrintWriter w,int iter ,ArrayList<knight> knightlist,item itemobj, knight k) throws OverlapException
	{
		
		for (int i=0; i<knightlist.size(); i++)
		{
			if (itemobj.type.equalsIgnoreCase("Coordinate"))
			{
			coordinate cobj = (coordinate)itemobj;
			if (cobj.kx==knightlist.get(i).kx && cobj.ky==knightlist.get(i).ky )
			{
				
				w.println(iter + " " + k.kname + " " + k.kx+ " " + k.ky);
				String s = knightlist.get(i).kname;
				knightlist.remove(i);
				k.setx(cobj.kx);
				k.sety(cobj.ky);
				throw new OverlapException("OverlapException: Knights Overlap Exception " + s );
				
			}
			}
			
		}
		
		
	}
	
	

}

class knight implements Comparable<knight>
{
	String kname ;
	int kx, ky;
	int m;
	Stack<Object> stack = new Stack<Object>();
	//String type ;
	
	public knight(String kname, int kx, int ky, int m, Stack<Object> stack)
	{
		this.kname = kname;
		this.kx = kx;
		this.ky=ky;
		this.m = m;
		this.stack = stack;
				
	}
	
	public int compareTo(knight other)
	{
		return kname.compareToIgnoreCase(other.kname);
	}
	
	void setx (int kx)
	{
		this.kx=kx;
	}
	
	int getx ()
	{
		return this.kx;
	}
	
	void sety (int ky)
	{
		this.ky = ky;
	}
	
	int gety()
	{
		return this.ky;
	}
	
}

class item<T>
{
	
	String type ;
	T value;
	
	item(String type, T value)
	{
		this.type=type;
		this.value=value;
	}
	
	String getitemtype()
	{
		return type;
	}
}

class coordinate extends item
{
	int kx;
	int ky;
	
	coordinate(String type, int kx, int ky )
	{
		super(type, kx);
		this.ky=ky;
		this.kx = kx;
	}
}

class QueenFoundException extends Exception
{
	public QueenFoundException(String string)
	{
		super(string);
	}

	
}

class NonCoordinateException extends Exception
{
	public NonCoordinateException(String string) 
	{
		super(string);
	}

	
}

class OverlapException extends Exception
{
	public OverlapException(String string)
	{
		super(string);
	}
}