import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class MeritOrderAdmission {
	private ArrayList<Candidate> cml = new ArrayList<Candidate>();
	private MeritList mlist[]= new MeritList[8];
	HashMap<String,VirtualProgramme> progList = new HashMap<String,VirtualProgramme>();
	public int ds[] = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
	public int dscr[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	
	public MeritOrderAdmission (String csvFile1,String csvFile2,String csvFile3){
		//read using bufferreader
		int lineno;
		BufferedReader br = null;
		String line;
		String splitBy=",";
		ArrayList<VirtualProgramme> a0 = new ArrayList<VirtualProgramme>();
		ArrayList<Candidate> a = new ArrayList<Candidate>();
		ArrayList<Candidate> b1 = new ArrayList<Candidate>();;
        
        
		try {    //input from programs.csv
			lineno = 0;
			br = new BufferedReader(new FileReader(csvFile1));
			while ((line = br.readLine()) != null) {  
				if(lineno!=0)
				{
					String[] info = line.split(splitBy);
					int[] r = {0,0,0,0,0,0,0,0};
					for(int i=0;i<8;i++){
						r[i]=Integer.parseInt(info[3+i]);
					}
					a0.add(new VirtualProgramme(info[1],r));
				}
				lineno++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		} finally {  
			if (br != null) {  
				try {  
					br.close();  
				} 
				catch (IOException e) {  
					e.printStackTrace();  
				}  
			}  		
		}
		//a0 comes from program.csv
        
        
		try {    //input from ranklist.csv
			lineno = 0;
			br = new BufferedReader(new FileReader(csvFile2));  
			while ((line = br.readLine()) != null) {  
				if(lineno!=0)
				{
					String[] info = line.split(splitBy);
					int[] r = {0,0,0,0,0,0,0,0};
					for(int i=0;i<4;i++){
						r[i]=Integer.parseInt(info[3+i]);
					}
					for(int i=0;i<4;i++){
						r[i+4]=Integer.parseInt(info[8+i]);						
					}
					Candidate c = new Candidate(info[0]);
					c.set_rank(r);
					a.add(c);
				}
				lineno++;
			}  
		} catch (FileNotFoundException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		} finally {  
			if (br != null) {  
				try {  
					br.close();  
				} 
				catch (IOException e) {  
					e.printStackTrace();  
				}  
			}  		
		}
		//a comes from meritlist.csv
        
        
        
		try {  //input from choices.csv
			lineno=0;
			br = new BufferedReader(new FileReader(csvFile3));  
			while ((line = br.readLine()) != null) {  
				if(lineno!=0)
				{
					String[] info = line.split(splitBy);
					boolean b2;
					
					if(info[1].equals("DS"))
						b2=true;
					else
						b2=false;
					Candidate ad1 = new Candidate(info[0]);
					ad1.set_pref(info[3]);
					ad1.set_ds(b2);
					//System.out.print(ad1.gds());
					b1.add(ad1);
				}
				
				
				lineno++;

			}  

		} catch (FileNotFoundException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		} finally {  
			if (br != null) {  
				try {  
					br.close();  
				} 
				catch (IOException e) {  
					e.printStackTrace();  
				}  
			}  		
		}
		//b comes from choices.csv
        
        this.intialize(a0, a);
		this.update(b1);
		this.allot();
	}
	
	private void intialize(ArrayList<VirtualProgramme> a0, ArrayList<Candidate> a){
		for(int i=0; i<a0.size(); i++){
    		progList.put(a0.get(i).gcode(), a0.get(i));
    	}
		mlist[0] = new MeritList(a,0);
    	mlist[1] = new MeritList(a,1);
    	mlist[2] = new MeritList(a,2);
    	mlist[3] = new MeritList(a,3);
    	mlist[4] = new MeritList(a,4);
    	mlist[5] = new MeritList(a,5);
    	mlist[6] = new MeritList(a,6);
    	mlist[7] = new MeritList(a,7);
    	Collections.sort(a, new Comparator<Candidate>() {
            public int compare(Candidate p1, Candidate p2) {
                return p1.guid().compareTo(p2.guid());
            }
        });
    	cml = a;
	}
	
	private void update(ArrayList<Candidate> a){ // 
		Collections.sort(a, new Comparator<Candidate>() {
            public int compare(Candidate p1, Candidate p2) {
                return p1.guid().compareTo(p2.guid());
            }
        });
		int i=0,j=0;
		Candidate c1,c2;
		while(i<cml.size()&&j<a.size()){
			c1 = cml.get(i);
			c2 = a.get(j);
			if ( c1.guid().compareTo( c2.guid() ) < 0) i++;
			else if( c1.guid().compareTo( c2.guid() ) > 0) j++;
			else {
				c1.set_ds(c2.gds());//System.out.println(c2.gds());
				c1.set_pref(c2.getPrefString());
			}
			i++;j++;
		}
    }
	
	private void allot(){
		int c;
		do{
			c=0;
			for(int i=0; i<8; i++){
				for(int j=0; j<mlist[i].size(); j++){
					if(mlist[i].element(j).gbid() =="-2") {
						if(mlist[i].element(j).isPrefEmpty()) mlist[i].element(j).set_bid("-1");
						else{
							progList.get(mlist[i].element(j).getPeekPref()).apply(mlist[i].element(j),i);
						}
						c++;
					}
				}
			}
			// ds allocation.
			for (int i=0; i<cml.size(); i++){
				Candidate c1 = cml.get(i);
				if(!c1.isPrefEmpty()){
					int a = c1.getPeekPref().charAt(0)-65;
					if(c1.gds()&&c1.gbid()=="-2"&&(ds[a]>0||dscr[a]==c1.grank(0))){
						c1.set_bid(c1.getPeekPref());
						ds[a]--;
						dscr[a]=c1.grank(0);
					}
					c1.removePeek();
				}
			}
		}while(c!=0);
	}
	
	public void print(String outputFile){
		try
		{
		    FileWriter writer = new FileWriter(outputFile);
		    writer.write("CandidateUniqueId,ProgramID\n");
		    for(int i=0; i<cml.size(); i++)
		    {
		    	writer.append(cml.get(i).guid()+","+cml.get(i).gbid()+"\n");
		    }
		    writer.flush();
		    writer.close();
		}
		catch(IOException e)
		{
		     e.printStackTrace();
		}
    }
}
