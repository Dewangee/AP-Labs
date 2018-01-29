import java.util.*;
public class lab1 {
	

	public static void main(String[] args)
	{
		
		
		lab1 obj= new lab1();
		Scanner ob = new Scanner(System.in); //Input Statement
		int n = ob.nextInt();
		int m = ob.nextInt();
		// Variable initialisation
		int countphd=0, countpg=0, countug=0;
		String[][] a = new String[n][4];
		String[][] array = new String[m][4];
		int flag=0;
		int roomspg, roomsphd, roomsug;
		for (int i=0; i<n; i++)
		{
			String s= ob.next();
			s+=ob.nextLine();
			a[i] = s.split(" ");
			//Counting the number of applicants from each program
			if (a[i][2].equalsIgnoreCase("PHD"))
			{
				countphd++;
			}
			else if (a[i][2].equalsIgnoreCase("PG"))
			{
				countpg++;
				
			}
			else if (a[i][2].equalsIgnoreCase("UG"))
			{	
				countug++;
				
			}
		}
		//Forming separate arrays for the applicants of different programs 
		
		String[][] arrug= new String[countug][4];
		String[][] arrpg= new String[countpg][4];
		String[][] arrphd= new String[countphd][4];
		int i1=0,i2=0,i3=0;
		for (int i=0; i<n; i++)
		{
			
			if (a[i][2].equalsIgnoreCase("PHD"))
			{
				arrphd[i1]=a[i];
				i1++;
			}
			else if (a[i][2].equalsIgnoreCase("PG"))
			{
				arrpg[i2]=a[i];
				i2++;
				
			}
			else if (a[i][2].equalsIgnoreCase("UG"))
			{	
				arrug[i3]=a[i];
				i3++;
			}
		}
		arrphd = obj.sort(arrphd);
		arrug= obj.sort(arrug);
		arrpg = obj.sort(arrpg);
		
		//Dividing the number of rooms available for people of different streams
		if (m%2==0)
		{
			roomspg = m/2;
			roomsphd = m/2;
		}
		else
		{
			roomsphd=m/2+1;
			roomspg= m-roomsphd;
		}
		// The persons with maximum distance from PG are given higher preference and appended to a new array
		if (roomspg<countpg)
		{
			for (int i=0; i<roomspg;i++)
			{
				array[flag]=arrpg[i];
				flag++;
			}
			roomspg=0;
		}
		else
		{
			for (int i=0;i<countpg;i++)
			{
				array[flag]= arrpg[i];
				flag++;
			}
			roomspg= roomspg-countpg; //remaining rooms after PG allocation
		}
		// The persons with maximum distance from PHD are given higher preference and appended to a new array
		if (roomsphd<countphd)
		{
			for (int i=0; i<roomsphd;i++)
			{
				array[flag]=arrphd[i];
				flag++;
			}
			roomsphd=0;
		}
		else
		{
			for (int i=0;i<countphd;i++)
			{
				array[flag]= arrphd[i];
				flag++;
			}
			roomsphd= roomsphd-countphd; //remaining rooms after PHD allocation
		}
		
		roomsug = roomspg+roomsphd; //Rooms left for UG allocation
		if (roomsug!=0 && countug!=0)
		{
			for (int i=0; i<roomsug;i++)
			{
				array[flag]= arrug[i];
				flag++;
			}
		}
		//Preference wise printing of the records according to Roll Number which is unique
		for (int i =0; i <a.length; i++)
		{
			for (int j=0; j<array.length;j++)
			{
				if (a[i][1].equalsIgnoreCase(array[j][1]))
				{
					System.out.println(a[i][0]+" " + a[i][1] + " " + a[i][2] + " " + a[i][3]); break;
				}
			}	
		}
		
	}
	// This function will sort all the double String arrays according to the descending order of distance
	public String[][] sort(String[][] arr)
	{
		
		for (int i =0; i < arr.length-1; i++)
		{
			int index=i;
			for (int j=i+1; j < arr.length; j++)
			{
				if (Integer.parseInt(arr[index][3])>=Integer.parseInt(arr[j][3]))
					index=j;
			}
			String[] temp = arr[index];   
            arr[index] = arr[i];  
            arr[i] = temp; 
		}
		// Uses a selection sort algorithm for the same
		String[][] b= new String[arr.length][4];
		
		for (int i =0; i<arr.length; i++)
		{
			b[i]=arr[arr.length-i-1]; 
		}
		
		return b;
	   
		//Returns the array in descending order of distance	
	}
}
