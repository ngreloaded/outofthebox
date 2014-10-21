

public class Main {
	
	public static void main(String args[]){
		String folder = "res";
		String csvFile3 = folder.concat("/choices.csv");
		String csvFile2 = folder.concat("/ranklist.csv");
		String csvFile1 = folder.concat("/programs.csv");
		String outputFile = folder.concat("/output.csv");
		MeritOrderAdmission M = new MeritOrderAdmission(csvFile1,csvFile2,csvFile3);
		M.print(outputFile);
		
	}
}
