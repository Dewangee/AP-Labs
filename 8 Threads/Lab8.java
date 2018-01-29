package lab8;


import java.util.concurrent.*;
import java.math.BigInteger;
import java.util.*;


public class Lab8 extends RecursiveTask<BigInteger>{
	
	public BigInteger n;
	public BigInteger k;
	

	public Lab8(BigInteger n,BigInteger k){
		this.n=n;this.k=k;
	}
	
	
	BigInteger recurse(BigInteger n,BigInteger k){
		if(n.compareTo(new BigInteger("0"))==0||k.compareTo(new BigInteger("0"))==0||n.compareTo(k)==0){
			return new BigInteger("1");
		}
		
		BigInteger left=recurse(n.subtract(new BigInteger("1")),k.subtract(new BigInteger("1")));
		BigInteger right=recurse(n.subtract(new BigInteger("1")),k);
		
		return (left.add(right));
	}
	
	
	
	public BigInteger compute() {
		if(n.compareTo(new BigInteger("0"))==0||k.compareTo(new BigInteger("0"))==0||n.compareTo(k)==0){
			return new BigInteger("1");
		}
		Lab8 left=new Lab8(this.n.subtract(new BigInteger("1")),this.k.subtract(new BigInteger("1")));
		Lab8 right=new Lab8(this.n.subtract(new BigInteger("1")),this.k);
		left.fork();
		
		return right.compute().add(left.join());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BigInteger n = new BigInteger("25");
		BigInteger k = new BigInteger("6");
		Lab8 task3=new Lab8(n,k);
		double stt = System.currentTimeMillis();
		BigInteger rrr =task3.recurse(n, k);
		double eee   = System.currentTimeMillis();
		double totalTime3 = eee- stt;
		//System.out.println(totalTime3);
		ForkJoinPool pool=new ForkJoinPool(1);
		Lab8 task=new Lab8(n,k);
		double estart = System.currentTimeMillis();
		BigInteger gg=pool.invoke(task);
		double elast = System.currentTimeMillis();
		double finallyy =elast-estart;
		System.out.println(finallyy/totalTime3);
		//System.out.println(gg);
		ForkJoinPool poo=new ForkJoinPool(2);
		Lab8 tas=new Lab8(n,k);
		double estar = System.currentTimeMillis();
		BigInteger g=poo.invoke(tas);
		double elas = System.currentTimeMillis();
		double finallyyy =elas-estar;
		System.out.println(finallyyy/totalTime3);
		//System.out.println(g);
		ForkJoinPool po=new ForkJoinPool(3);
		Lab8 ta=new Lab8(n,k);
		double esta = System.currentTimeMillis();
		BigInteger gk=po.invoke(ta);
		double ela = System.currentTimeMillis();
		double finallyyyy =ela-esta;
		System.out.println(finallyyyy/totalTime3);
		//System.out.println(gk);
		System.out.println("Ratio of time for 2nd by 1st thread pool " );
		System.out.println(finallyyy/finallyy);
		System.out.println("Ratio of time for 3rd by 1st thread pool " );
		System.out.println(finallyyyy/finallyy);
	}

}