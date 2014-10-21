
public class VirtualProgramme {
	private String code;
	private int[] seatAvailable = new int[8];
	private int[] cr = new int[8];
	
	//constructor
	public VirtualProgramme(String cd, int[] capacity){
		code = cd;
		seatAvailable = capacity;
	}
    
	//getter
	public String gcode(){
		return code;
	}
	public boolean apply(Candidate c, int category){
		if(seatAvailable[category]>0){
			seatAvailable[category]--;
			c.set_bid(code);
			cr[category]=c.grank(category);
			return true;
		}
		else if(c.grank(category)==cr[category]){//for students with same rank
			c.set_bid(code);
			return true;
		}
		else return false;
	}
	public int gcr(int i){
		return cr[i];
	}

}
