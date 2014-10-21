
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		
//			int a[] = {1,2};
//			Candidate c = new Candidate("c", false, true, a);
//			c.set_pref("a_aa_aaa");
//			
//		for (int i=0; i<3; i++){
//			System.out.println(c.getPeekPref());
//		}
//		c.resestIterator();
//		for (int i=0; i<3; i++){
//			System.out.println(c.getPeekPref());
//		}
//		
//		a[0] = 3;
//		a[1] = 0;
//		Candidate c1 = new Candidate("c1", false, true, a);
//		
//		a[0] = 5;
//		a[1] = 1;
//		Candidate c2 = new Candidate("c2", false, true, a);
//		
//		a[0] = 0;
//		a[1] = 1;
//		VirtualProgramme vp = new VirtualProgramme("B1",a);
//		
//		a[0] = 1;
//		a[1] = 1;
//		VirtualProgramme vp1 = new VirtualProgramme("B2",a);
//		
//		a[0] = 2;
//		a[1] = 1;
//		VirtualProgramme vp2 = new VirtualProgramme("D1",a);
		
//		
		
			String csvFile3 = "res/choices.csv";
			String csvFile2 = "res/ranklist.csv";
			String csvFile1 = "res/programs.csv";
			String outputFile = "res/output.csv";
			MeritOrderAdmission M = new MeritOrderAdmission(csvFile1,csvFile2,csvFile3,outputFile);
			M.print();
			
		
		
	}

}
