package lab4;
import java.util.*;

public class lab4 
{
	public static world wobj;
	public static grassland gobj;
	//public static carnivore cobj;
	//public static herbivore hobj;
	
	public static void main(String[] args)
	{
		//Taking Input Values
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Total Final Time for Simulation:");
		int totaltime = sc.nextInt();
		
		System.out.println("Enter x, y centre, radius and Grass Available for First Grassland:");
		double xgrass1 = sc.nextDouble();
		double ygrass1 = sc.nextDouble();
		double radgrass1 = sc.nextDouble();
		int availgrass1 = sc.nextInt();
		
		System.out.println("Enter x, y centre, radius and Grass Available for Second Grassland:");
		double xgrass2 = sc.nextDouble();
		double ygrass2 = sc.nextDouble();
		double radgrass2 = sc.nextDouble();
		int availgrass2 = sc.nextInt();
		
		System.out.println("Enter Health and Grass Capacity for Herbivores:");
		double herbhealth = sc.nextDouble();
		int herbgrasscap = sc.nextInt();
		
		System.out.println("Enter x, y position and timestamp for First Herbivore:");
		double xherb1 = sc.nextDouble();
		double yherb1 = sc.nextDouble();
		int timeherb1 = sc.nextInt();
		
		System.out.println("Enter x, y position and timestamp for Second Herbivore:");
		double xherb2 = sc.nextDouble();
		double yherb2 = sc.nextDouble();
		int timeherb2 = sc.nextInt();
		
		System.out.println("Enter Health for Carnivores:");
		double carnhealth = sc.nextDouble();
		
		System.out.println("Enter x, y position and timestamp for First Carnivore:");
		double xcarn1 = sc.nextDouble();
		double ycarn1 = sc.nextDouble();
		int timecarn1 = sc.nextInt();
		
		System.out.println("Enter x, y position and timestamp for Second Carnivore:");
		double xcarn2 = sc.nextDouble();
		double ycarn2 = sc.nextDouble();
		int timecarn2 = sc.nextInt();
		
		System.out.println("The Simulation Begins -");
		
		//Creating objects of different classes
		
		herbivore herb1 = new herbivore(xherb1, yherb1, timeherb1, herbhealth, herbgrasscap, "First Herbivore.");
		herbivore herb2 = new herbivore(xherb2, yherb2, timeherb2, herbhealth, herbgrasscap, "Second Herbivore.");
		wobj.herblist.add(herb1);
		wobj.herblist.add(herb2);
		
		carnivore carn1 = new carnivore(xcarn1, ycarn1, timecarn1, carnhealth, "First Carnivore.");
		carnivore carn2 = new carnivore(xcarn2, ycarn2, timecarn2, carnhealth, "Second Carnivore.");
		wobj.carnlist.add(carn1); 
		wobj.carnlist.add(carn2);
		
		grassland grass1 = new grassland(xgrass1, ygrass1, radgrass1, availgrass1);
		grassland grass2 = new grassland(xgrass2, ygrass2, radgrass2, availgrass2);
		
		
		//Making priority queue
		
		
		wobj.q.add(herb1);
		wobj.q.add(herb2);
		wobj.q.add(carn1);
		wobj.q.add(carn2);
		
		               
		               
	
	
		
		//Temporary pq to find timestamp  
		PriorityQueue<forest> q2 = new PriorityQueue<forest>();
		forest a1 = wobj.q.remove();
		q2.add(a1);
		forest a2 = wobj.q.remove();
		q2.add(a2);
		forest a3 = wobj.q.remove();
		q2.add(a3);
		forest timestampob = wobj.q.remove();
		q2.add(timestampob);
		int timestamp = timestampob.timestamp;
		int changedtotaltime = totaltime;
		
		while (q2.size()>0)
		{
			wobj.q.add(q2.remove());
			
		}
		
		while(changedtotaltime>0 && wobj.q.size()>0)
		{
			forest beforeturn;
			beforeturn = wobj.q.remove();
			
			forest afterturn = beforeturn.choose(beforeturn,grass1,grass2);
			
			System.out.println("It is "+ beforeturn.animalname);
			if (afterturn.health > 0 && timestamp <= totaltime-1 )
			{
				System.out.println("It's health after taking turn is " + afterturn.health);
				Random rn = new Random();
				timestamp = rn.nextInt(totaltime-timestamp+1)+timestamp;
				afterturn.settimestamp(timestamp);
				wobj.q.add(afterturn);
			}
			else
			{
				System.out.println("It is dead.");
			}
			changedtotaltime--;
		}
		
		
		
		
	}

}

class world
{
	double xcoordinate;
	double ycoordinate;
	int timestamp;
	double health;
	
	world(double xcoordinate, double ycoordinate , int timestamp , double health)
	{
		this.xcoordinate = xcoordinate;
		this.ycoordinate = ycoordinate;
		this.timestamp = timestamp;
		this.health = health;
	}
	
	static PriorityQueue<forest> q = new PriorityQueue<forest>();
	static List<grassland> grassarray = new ArrayList<grassland>();
	static List<carnivore> carnlist = new ArrayList<carnivore>();
	static List<herbivore> herblist = new ArrayList<herbivore>();
	
}
abstract class forest extends world implements Comparable<forest>
{	
	int animal;
	String animalname = "";
	forest(double xcoordinate, double ycoordinate , int timestamp , double health, String animalname)
	{
		super( xcoordinate,  ycoordinate ,  timestamp ,  health);
		this.animalname = animalname;
	}
	
	void setxcoordinate(int xcoordinate)
	{
		this.xcoordinate = xcoordinate;
	}
	
	void setycoordinate(int ycoordinate)
	{
		this.ycoordinate = ycoordinate;
	}
	
	void sethealth(double health)
	{
		this.health = health;
	}

	public int compareTo(forest o)
	{
		if (timestamp > o.timestamp)
			return 1;
		else if (timestamp < o.timestamp)
			return -1;
		else
		{
			if (health < o.health)
				return 1;
			else if (health > o.health)
				return -1;
			else
			{
				if (animal > o.animal)
					return 1;
				else if (animal < o.animal)
					return -1;
				else
				{
					if (Math.sqrt(xcoordinate*xcoordinate+ycoordinate*ycoordinate) > Math.sqrt(o.xcoordinate*o.xcoordinate+o.ycoordinate*o.ycoordinate))
						return 1;
					else if (Math.sqrt(xcoordinate*xcoordinate+ycoordinate*ycoordinate) < Math.sqrt(o.xcoordinate*o.xcoordinate+o.ycoordinate*o.ycoordinate))
						return -1;
					else
						return 0;
				}
				
					
			}
		}
	}
	abstract public forest choose(forest fo, grassland grass1, grassland grass2);
	
	void settimestamp(int timestamp)
	{
		this.timestamp = timestamp;
	}
	
	boolean ingrassland(grassland grass, forest fo)
	{
		double grassdist = Math.sqrt((fo.xcoordinate-grass.xgrass)*(fo.xcoordinate-grass.xgrass)+(fo.ycoordinate-grass.ygrass)*(fo.ycoordinate-grass.ygrass));
		if (grassdist*grassdist <= (grass.radgrass*grass.radgrass))
			return true;
		else
			return false;
	}
	 
	
	grassland nearestgrass(forest fo,grassland grass1, grassland grass2)
	{
		double grass1dist = Math.sqrt((fo.xcoordinate-grass1.xgrass)*(fo.xcoordinate-grass1.xgrass)+(fo.ycoordinate-grass1.ygrass)*(fo.ycoordinate-grass1.ygrass));
		double grass2dist = Math.sqrt((fo.xcoordinate-grass2.xgrass)*(fo.xcoordinate-grass2.xgrass)+(fo.ycoordinate-grass2.ygrass)*(fo.ycoordinate-grass2.ygrass));
		
		if (grass1dist <= grass2dist)
			return grass1;
		else 
			return grass2;
					
	}
	
	void towardgrassland(grassland grass, forest fo, double units)
	{
		double theta = Math.atan2((fo.ycoordinate-grass.ygrass), (fo.xcoordinate-grass.xgrass));
		double tempx = fo.xcoordinate + units*Math.cos(theta);
		double tempy = fo.ycoordinate + units*Math.sin(theta);
		fo.setxcoordinate((int)tempx);
		fo.setycoordinate((int)tempy);
		
	}
}

class herbivore extends forest 
{
	int herbgrasscap;
	int animal ;
	int times=0;

	herbivore(double xcoordinate, double ycoordinate, int timestamp, double health, int herbgrasscab, String animalname) 
	{
		super(xcoordinate, ycoordinate, timestamp, health, animalname);
		this.herbgrasscap = herbgrasscap;
		animal = 100;
	}
	
	
	
	
	public forest choose(forest fo, grassland grass1, grassland grass2)
	{
		Random rn= new Random();
		herbivore herb= (herbivore)fo;
		int checkcarn=0;
		PriorityQueue<forest> checkcarnpq = new PriorityQueue<forest>();
		while (q.size()>0)
		{
			forest f = q.remove();
			checkcarnpq.add(f);
			if (f.animalname.equalsIgnoreCase("First Carnivore.") || f.animalname.equalsIgnoreCase("Second Carnivore."))
				checkcarn=1;
		}
		
		while (checkcarnpq.size()>0)
		{
			q.add(checkcarnpq.remove());
		}
		//If carnivore is not present
		
		if (checkcarn==0)
		{
			if (ingrassland(grass1, fo))       
		    {                                  
		    	towardgrassland(grass2,fo,5);  
		        fo.sethealth(health-25);                               
		    }                                  
		    else if (ingrassland(grass2, fo))  
		    {                                  
		    	towardgrassland(grass1,fo,5);  
		        fo.sethealth(health-25);                              
		    }                                  
		    else
		    {
		         if ((rn.nextInt(1-1+1)+1)==1)
		         {
		        	    grassland nearestgrassland = nearestgrass(fo,grass1,grass2);  
		                 towardgrassland(nearestgrassland,fo,5); 
		         }                            
		         else
		         {
		        	 //stays
		        	 
		              times++;                   
		              if (times>=7)              
		              	fo.sethealth(health-5);
		         }
		    }
		
		
		
		
		}
		
		//If at least one carnivore is present
		else
		{
			//If herb is not inside grassland
			if (!ingrassland(grass1,fo) && !ingrassland(grass2,fo))
			{
				int r1 = rn.nextInt(99)+1;
				
				times++;
				if (times>=7)
					fo.sethealth(health-5);
				
				//95% does not stay
				
				int r2 = rn.nextInt(99)+1;
				if (r2 <= 65)
				{
					//65% moves to nearest grassland
					grassland nearestgrassland = nearestgrass(fo,grass1,grass2); 
					towardgrassland(nearestgrassland,fo,5);
					
				}
				else
				{
					//35% moves away from carnivore
					
					nearestcarn(fo,4);
				}
			}
			
			//If herb is inside grassland
			else 
			{
				grassland g,g1;
				if (ingrassland(grass1,fo))
				{
					g=grass1;
					g1=grass2;
				}
				else
				{
					g=grass2;
					g1=grass1;
				}
					
				times =0;
				
				//if grassavailable is more than capacity
				if (g.grassavail >= herb.herbgrasscap)
				{
					int r3= rn.nextInt(99)+1;
					
					//herb stays and eats upto max capacity
					if (r3 <= 90) // 90% chance 
					{
						fo.sethealth(health + 0.5*health);
						g.setgrassavail(g.grassavail-herb.herbgrasscap);
					}
					
					// herb does not stay 
					else 
					{
						fo.sethealth(health-25);
						
						//moves away from nearest carnivore
						if ((rn.nextInt(1-1+1)+1)==0) //50% chance
						{
							nearestcarn(fo,2);
						}
						
						//moves to next nearest grassland
						else
						{
							towardgrassland(g1,fo,3);
						}
						
					}
				}
				
				// if grassavailable is less than capacity of herbivore
				else
				{
					int r4 = rn.nextInt(99)+1;
					
					//chooses to stay and eat all grass available
					if (r4 <= 20) //20% chance
					{
						fo.sethealth(health + 0.2*health);
						g.setgrassavail(0);
					}
					
					//does not stay 
					else
					{
						fo.sethealth(health-25);
						int r5 = rn.nextInt(99)+1;
						
						//moves away from nearest carnivore
						if (r5 <= 70) //70% chance
						{
							nearestcarn(fo,4);
						}
						
						//towards next grassland
						else
						{
							towardgrassland(g1,fo,2);
						}
					}
				}
			}
			
		}
		return fo;
	}
	
	void nearestcarn(forest fo,double units)
	{
		PriorityQueue<forest> temp = new PriorityQueue<forest>();
		double carn1dist=100000, carn2dist=100000;
		carnivore carn1=carnlist.get(0), carn2=carnlist.get(1);
		while (q.size() > 0)
		{
			forest f=q.remove();
			temp.add(f);
			if (f.animalname.equalsIgnoreCase("First Carnivore."))
			{
				carn1 = (carnivore)f;
				carn1dist = Math.sqrt((fo.xcoordinate-carn1.xcoordinate)*(fo.xcoordinate-carn1.xcoordinate)+(fo.ycoordinate-carn1.ycoordinate)*(fo.ycoordinate-carn1.ycoordinate));
			}
			else if (f.animalname.equalsIgnoreCase("Second Carnivore."))
			{
				carn2 = (carnivore)f;
				carn2dist = Math.sqrt((fo.xcoordinate-carn2.xcoordinate)*(fo.xcoordinate-carn2.xcoordinate)+(fo.ycoordinate-carn2.ycoordinate)*(fo.ycoordinate-carn2.ycoordinate));
			}
		}
		while (temp.size()>0)
		{
			q.add(temp.remove());
		}
		if (carn1dist <= carn2dist)
		{
			if (carn1.health>0)
				awayfromcarn(fo,carn1,units);
		}
		else 
			awayfromcarn(fo,carn2,units);
		
	}
	
	void awayfromcarn(forest fo,carnivore carn,  double units)
	{
		double carndist = Math.sqrt((fo.xcoordinate-carn.xcoordinate)*(fo.xcoordinate-carn.xcoordinate)+(fo.ycoordinate-carn.ycoordinate)*(fo.ycoordinate-carn.ycoordinate));
		double ratio = units/(units+carndist);
		int tempx =(int) ( (fo.xcoordinate-(ratio*carn.xcoordinate))/(1-ratio));
		int tempy =(int) ( (fo.ycoordinate-(ratio*carn.ycoordinate))/(1-ratio));
		fo.setxcoordinate(tempx);
		fo.setycoordinate(tempy);
	}
	
}

class carnivore extends forest
{
	int animal ;
	int times=0;
	
	carnivore(double xcoordinate, double ycoordinate, int timestamp, double health, String animalname) 
	{
		super(xcoordinate, ycoordinate, timestamp, health, animalname);
		animal = 1000;
	}
	
	
	public forest choose(forest fo, grassland grass1, grassland grass2)
	{
		Random rn= new Random();
		int checkherb=0;
		PriorityQueue<forest> checkherbpq = new PriorityQueue<forest>();
		while (q.size()>0)
		{
			forest f=q.remove();
			checkherbpq.add(f);
			if (f.animalname.equalsIgnoreCase("First Herbivore.") || f.animalname.equalsIgnoreCase("Second Herbivore."))
				checkherb=1;
		}
		
		while (checkherbpq.size()>0)
		{
			q.add(checkherbpq.remove());
		}
		//If herbivore is not present
		
		if (checkherb==0)
		{
			//stays at its position
			
			// if carnivore is inside grassland
			
			if (ingrassland(grass1,fo) || ingrassland(grass2,fo))
				fo.health -= 30;
			
			// if it's not inside grassland
			else
				fo.health -= 60; 
			
		}
		
		//At least one herbivore is present
		else
		{
			herbivore h = nearestherb(fo);
			if (h!=null)
			{
				double hcdist = Math.sqrt((fo.xcoordinate-h.xcoordinate)*(fo.xcoordinate-h.xcoordinate)+(fo.ycoordinate-h.ycoordinate)*(fo.ycoordinate-h.ycoordinate));
				
				//within one mile radius
				
				if (hcdist <= 1)
				{
					//kills the herbivore
					fo.sethealth(health+ (2/3)*h.health);
					h.sethealth(0);
				}
				
				//outside one mile radius
				
				else
				{
					// if it is not inside grassland
					if (hcdist <= 5)
						times=0;
					
					times++;
					if (times > 7)
						fo.sethealth(health-6);
					
					if (!ingrassland(grass1, fo) && !ingrassland(grass2, fo))
					{
						int rn1 = rn.nextInt(99)+1;
						
						//move towards nearest herbivore
						if (rn1 <= 92) //92%chance
						{
							movetowardsherb(h ,4,fo);
						}
						
						//stay at current position
						else
						{
							fo.sethealth(health-60);
						}
							
					}
					
					//if it is inside grassland
					
					else
					{
						int rn2 = rn.nextInt(99)+1;
						
						//stay at current position
						if (rn2 <= 25)
						{
							fo.sethealth(health-30);
						}
						
						//does not stay and moves towards nearest herbivore
						else
						{
							movetowardsherb(h ,2,fo);
						}
					}
				}
			}
		}
		return fo;
		
	}
	
	herbivore nearestherb(forest fo)
	{
		PriorityQueue<forest> temp = new PriorityQueue<forest>();
		double herb1dist=100000, herb2dist=100000;
		herbivore herb1=herblist.get(0), herb2=herblist.get(1);
		while (q.size() > 0)
		{
			forest f=q.remove();
			temp.add(f);
			if (f.animalname.equalsIgnoreCase("First Herbivore."))
			{
				herb1 = (herbivore)f;
				herb1dist = Math.sqrt((fo.xcoordinate-herb1.xcoordinate)*(fo.xcoordinate-herb1.xcoordinate)+(fo.ycoordinate-herb1.ycoordinate)*(fo.ycoordinate-herb1.ycoordinate));
			}
			else if (f.animalname.equalsIgnoreCase("Second Herbivore."))
			{
				herb2 = (herbivore)f;
				herb2dist = Math.sqrt((fo.xcoordinate-herb2.xcoordinate)*(fo.xcoordinate-herb2.xcoordinate)+(fo.ycoordinate-herb2.ycoordinate)*(fo.ycoordinate-herb2.ycoordinate));
			}
		}
		while (temp.size()>0)
		{
			q.add(temp.remove());
		}
		if (herb1dist <= herb2dist)
		{
			if (herb1.health>0)
				return herb1;
			else 
				return null;
		}
		else 
			return herb2;
		
	}
	
	void movetowardsherb(herbivore herb,double units, forest fo)
	{
		double theta = Math.atan2((fo.ycoordinate-herb.ycoordinate), (fo.xcoordinate-fo.xcoordinate));
		double tempx = fo.xcoordinate + units*Math.cos(theta);
		double tempy = fo.ycoordinate + units*Math.sin(theta);
		fo.setxcoordinate((int)tempx);
		fo.setycoordinate((int)tempy);
		
	}
	
}

class grassland 
{
	double xgrass;
	double ygrass;
	double radgrass;
	int grassavail;
	
	
	grassland(double xgrass, double ygrass, double radgrass, int grassavail)
	{
		this.xgrass = xgrass;
		this.ygrass = ygrass;
		this.radgrass = radgrass;
		this.grassavail = grassavail;
	}
	
	void setgrassavail(int grassavail)
	{
		this.grassavail = grassavail;
	}
	
}

