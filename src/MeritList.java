import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MeritList {
	ArrayList<Candidate> meritList;
	public MeritList(ArrayList<Candidate> a, final int k){
    	Collections.sort(a, new Comparator<Candidate>() {
            public int compare(Candidate p1, Candidate p2) {
                return p1.grank(k) - p2.grank(k);
            }
        });
    	meritList = new ArrayList<Candidate>();
    	for(int i=0; i<a.size(); i++){
    		Candidate c = a.get(i);
    		if (c.grank(k)!=0) meritList.add(c);
    	}
    }
	int size(){
		return meritList.size();
	}
	public Candidate element(int i){
		return meritList.get(i);
	}
}
