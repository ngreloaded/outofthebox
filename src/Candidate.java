//import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Candidate {
	
	//functions
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Candidate other = (Candidate) obj;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}
	
	
	
	//variables
	private String uid;	//unique id
	private String bid;	//id of allocated branch
	private boolean ds; // no gender no indian
	private int[] rank = new int[8];
	private Queue<String> prefList;
	private String pref;//only repeated variable in the project
	
	
    
	//Constructor
	public Candidate(String id){
		uid = id;
		bid = "-2";
		ds = false;
	}
	
	
	//setter functions
	public void set_rank(int rnk[]){
		rank=rnk;
	}
	public void set_ds(boolean b){
		ds = b;
	}
	
	public void set_bid(String s){
		bid = s;
	}
	
	public void set_pref(String str){
		pref = str;
		prefList = new LinkedList<String>();
		int prev = 0;
		int a = str.indexOf('_');
		while(a!=-1){
			prefList.add(str.substring(prev,a));
			prev = a+1;
			a = str.indexOf('_',prev);
		}
		prefList.add(str.substring(prev));
	}
    
    
	
	//getter functions
	public String guid(){
		return uid;
	}
	public String gbid(){
		return bid;
	}
	public boolean gds(){
		return ds;
	}
	public String getPeekPref(){
		return prefList.peek();
	}
	public void removePeek(){
		prefList.remove();
	}
	public boolean isPrefEmpty(){
		return prefList.isEmpty();
	}
	public String getPrefString(){
		return pref;
	}
	public int grank(int i){
		return rank[i];
	}
};
