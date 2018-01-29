package lab5ap;

import java.io.*;
import java.util.*;





public class lab5
{
	
	public static void main(String[] args) throws FileNotFoundException
	{
			
		
			ArrayList<Integer> rlist = new ArrayList<Integer>();
			
			Scanner scan = new Scanner(System.in);
			int x = scan.nextInt();
			int n = scan.nextInt();
			

			//BSTFilesBuilder obj = new BSTFilesBuilder();
			//obj.createBSTFiles(n, x);
			
			String[][] finalarr = new String[x][2];
			
			for (int t=1; t<=x ; t++)
			{
				String sum = "";
			
				Scanner sc = new Scanner(new FileReader("C:\\Users\\Dewangee\\Desktop\\AdvancedProgramming\\lab5ap\\src\\T3\\input\\" + t + ".txt"));
		       
				String s="";
		        while (sc.hasNextLine()) 
		            s = s+ sc.nextLine() + "\n";
		        String[] arr = s.split("\\s+");
		        
		        String type = arr[0];
		     
		        int a = Integer.parseInt(arr[1]);
		        
		        genlist<Integer> intlist = new genlist<Integer>();
		        BST<Integer> intbst = new BST<Integer>();
		        int intsum = 0, pos=0;
		        
		        if (type.equalsIgnoreCase("Integer"))
		        {
		        	for (int i =0 ;i <a ; i++)
			        {
		        		intlist.add(Integer.parseInt(arr[i+2])); 
		        		intbst.insert(intlist.get(i));
		        		intsum += intlist.get(i);
			        }
		        	
		        	
		        	pos = intbst.rootposition();
		        	sum = String.valueOf(intsum);
		        	
		        }
		        
		        genlist<String> strlist = new genlist<String>();
		        BST<String> strbst = new BST<String>();
		        
		        if (type.equalsIgnoreCase("String"))
		        {
		        	for (int i =0 ;i <a ; i++)
			        {
		        		
		        		strlist.add(arr[i+2]);
		        		strbst.insert(strlist.get(i));
			        }
		        	
		        	String strsum =strbst.stringprint();
		        	pos = strbst.rootposition();
		        	sum = strsum;
		        }	
		        
		        genlist<Float> fltlist = new genlist<Float>();
		        BST<Float> fltbst = new BST<Float>();
		        float fltsum=0;
		        
		        if (type.equalsIgnoreCase("Float"))
		        {
		        	for (int i =0 ;i <a ; i++)
			        {
		        		fltlist.add(Float.parseFloat(arr[i+2]));
		        		fltbst.insert(fltlist.get(i));
		        		fltsum += fltlist.get(i);
		        		
			        }
		        	
		        	
		        	pos = fltbst.rootposition();
		        	sum = String.valueOf(fltsum);
		        }
		        
		        rlist.add(pos);
		        finalarr[t-1][0] = String.valueOf(pos);
		        finalarr[t-1][1] = sum;
		        
		        
		        
		         
		       
			}
			
			
			
			
			for (int i =0; i < finalarr.length; i++)
			{
				int index=i;
				for (int j=i+1; j < finalarr.length; j++)
				{
					if (Integer.parseInt(finalarr[index][0])>Integer.parseInt(finalarr[j][0]))
						index=j;
				}
				String[] temp = finalarr[index];   
	            finalarr[index] = finalarr[i];  
	            finalarr[i] = temp; 
			}
		
		
			Set<Integer> hs = new HashSet<>();
			hs.addAll(rlist);
			rlist.clear();
			rlist.addAll(hs);
		
			
			int choc =n-rlist.size() ;
			
			
			try
			{
				String a="";
				PrintWriter w = new PrintWriter("./src/" + "output" + ".txt", "UTF-8");
				for (int i =0 ; i<finalarr.length ; i++)
				{
					if (a.indexOf(finalarr[i][0])==-1)
					{
						w.print(finalarr[i][0] + " " );
						a = a+finalarr[i][0];
					}
					
					w.print(finalarr[i][1] + " ");
				}
				w.print(" " + choc);	
				w.println();
				
				
				w.close();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			
		}
		
	    
	    
	}




class	genlist <T>	
{	
	private	ArrayList <T> llist;	
	T value;
	
	public	genlist()
	{	
				llist	=	new	ArrayList <T>();	
				
	}	
	public	void	add(T	o)	
	{	
				llist.add(o);	
	}	
	public	T	get(int i)	
	{	
				return	llist.get(i);	
	}	
	public int size()
	{
				return llist.size();
	}
	
	
}	

class Node<T extends Comparable<? super T>> 
{
    T val;
    Node<T> left;
    Node<T> right;

    Node(T val) {
      this.val = val;
      left = right = null;
    }
}

class BST<T extends Comparable<? super T>> 
{

	
	  private Node root;
	  

	  public BST() 
	  {
	    setRoot(null);
	  }
	  
	  

	  public void insert(T value)
	  {
	    setRoot(insert(value, getRoot()));
	  }

	  private T valueOf(Node<T> node) 
	  {
	    return node == null ? null : node.val;
	  }

	  private Node<T> insert(T value, Node<T> node)
	  {
	    if (node == null)
	    	node = new Node<T>(value);
	    
	    else if (value.compareTo(node.val) < 0)
	    	node.left = insert(value, node.left);
	      
	    else if (value.compareTo(node.val) > 0)
	    	node.right = insert(value, node.right);
	    
	  
	    
	    return node;
	  }
	  
	  public void printInOrder(Node<T> node)
	  {
		    if (node != null)
		    {
		      printInOrder(node.left);
		      System.out.println(node.val);
		      printInOrder(node.right);
		    }
	  }
	  
	  public void printInOrder()
	  {
		    printInOrder(getRoot());
	  }
	  
	  public int position(T find, Node node, int pos) 
	  {	
		  
		  if(node.left != null)
		       pos = position(find, node.left, pos++);
		  
		  if(node.val.compareTo(find)>=0) 
		       return pos;
		  pos++;

		   if(node.right != null) {
		       pos = position(find, node.right, pos++);
		   }

		   return pos;
		}
	  
	  public int rootposition() 
	  {
		   return position((T) getRoot().val, getRoot(), 1);
	  }
	  
	  
	  public String StringInOrder(Node<T> node)
	  {
		  	String s="";
		  	if (node == null)
		  		return "";
		  	else
		    {
		      s = s.concat(StringInOrder(node.left));
		      s = s.concat(node.val.toString());
		      s = s.concat(StringInOrder(node.right));
		    }
		   
		    return s;
	  }
	  
	  public String stringprint()
	  {
		  return StringInOrder(getRoot());
	  }



	public Node getRoot()
	{
		return root;
	}



	public void setRoot(Node root) 
	{
		this.root = root;
	}
	  
	 
	  
	  
	  
	  
	 
}	  
	  
	 

class BSTFilesBuilder 
{

	public void createBSTFiles(int numStudents, int numTrees) {
		Random rand = new Random();
		for (int i = 1; i <= numTrees; i++) {
		    try {
				PrintWriter w = new PrintWriter("./src/" + i + ".txt", "UTF-8");
				int type = rand.nextInt(3) + 1;
				if(type == 1) {
					w.println("Integer");
					w.println(numStudents);
					for (int j = 1; j <= numStudents; j++) {
						w.print(rand.nextInt(1000));
						w.print(" ");
					}
				}
				else if(type == 2) {
					w.println("Float");
					w.println(numStudents);
					for (int j = 1; j <= numStudents; j++) {
						w.print(rand.nextFloat()*1000);
						w.print(" ");
					}
				}
				else {
					w.println("String");
					w.println(numStudents);
					String alphabet = "abcdefghijklmnopqrstuvwxyz";
					for (int j = 1; j <= numStudents; j++) {
						int len = rand.nextInt(10)+1;
						for (int k = 0; k < len; k++)
							w.print(alphabet.charAt(rand.nextInt(alphabet.length())));
						w.print(" ");
					}
				}
				w.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}




