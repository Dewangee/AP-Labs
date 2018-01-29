package lab8;



import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.math.BigInteger;

public class Lab8_fly extends RecursiveTask<BigInteger> {
	
	private static volatile Map<String, Lab8_fly> instances=new HashMap<String,Lab8_fly>();

	private final BigInteger n,k;
	
	private Lab8_fly(BigInteger n,BigInteger k){
		this.n=n;
		this.k=k;
	}
	
	public BigInteger compute() {
		if(n.compareTo(new BigInteger("0"))==0||k.compareTo(new BigInteger("0"))==0||n.compareTo(k)==0){
			return  new BigInteger("1");
		}
		Lab8_fly left=getInstance(this.n.subtract(new BigInteger("1")),this.k.subtract(new BigInteger("1")));
		Lab8_fly right=getInstance(this.n.subtract(new BigInteger("1")),this.k);
		left.fork();
		return right.compute().add(left.join());
	}
	
	public static synchronized Lab8_fly getInstance(BigInteger n,BigInteger k){
		String key=n+","+k;
		if(!instances.containsKey(key)){
			instances.put(key, new Lab8_fly(n,k));
		}
		return instances.get(key);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BigInteger n = new BigInteger("250");
		BigInteger k = new BigInteger("6");
		ForkJoinPool pool=new ForkJoinPool(1);
		Lab8_fly task=new Lab8_fly(n,k);
		double estart = System.currentTimeMillis();
		BigInteger gg=pool.invoke(task);
		double elast = System.currentTimeMillis();
		double finallyy =elast-estart;
		System.out.println("Time for 1st thread pool " + finallyy);
		//System.out.println(gg);
		ForkJoinPool poo=new ForkJoinPool(2);
		Lab8_fly tas=new Lab8_fly(n,k);
		double estar = System.currentTimeMillis();
		BigInteger g=poo.invoke(tas);
		double elas = System.currentTimeMillis();
		double finallyyy =elas-estar;
		System.out.println("Time for 2nd thread pool " + finallyyy);
		//System.out.println(g);
		ForkJoinPool po=new ForkJoinPool(3);
		Lab8_fly ta=new Lab8_fly(n,k);
		double esta = System.currentTimeMillis();
		BigInteger gk=po.invoke(ta);
		double ela = System.currentTimeMillis();
		double finallyyyy =ela-esta;
		System.out.println("Time for 3rd thread pool " + finallyyyy);
		//System.out.println(gk);
		System.out.println("Ratio of time for 2nd by 1st thread pool " );
		System.out.println(finallyyy/finallyy);
		System.out.println("Ratio of time for 3rd by 1st thread pool " );
		System.out.println(finallyyyy/finallyy);
		
	}

}