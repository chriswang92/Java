

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PairProducer {

	
	static File pairsOutputFile;
	
	static List<String> getId(String file) {
		List<String> idList = new LinkedList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));

			String line = null;
			line = reader.readLine(); //skip the first line
			int count = 0;
			while ((line = reader.readLine()) != null) {
				String item[] = line.split(":");

				String last = item[item.length - 1];
				if (last != null && item.length > 1) {
					count++;
					System.out.println(count + ": " + last);
					idList.add(last);
				}

			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idList;
	}
	
	static List<String> getId_ByRegex(String file) {
		List<String> idList = new LinkedList<String>();
		Pattern p = Pattern.compile("(&quot;id&quot;:)(\\d\\d\\d\\d\\d\\d)");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));

			String line = null;
			line = reader.readLine(); //skip the first line
			while ((line = reader.readLine()) != null) {
				
				//. represents single character
				Matcher m = p.matcher(line);
				
				while(m.find()) {
					idList.add(m.group(2));
					
				}

			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return idList;
	}

	static void producePairs(List<String> idList, BufferedWriter writer, BufferedReader resultsReader)
			throws Exception {
		String in = null;
		String out = null;
		int index = 0;
		int result;
		List<String> resultList = new LinkedList<String>();
		while ((in = resultsReader.readLine()) != null) {
			resultList.add(in);
		}
		for (String s : resultList) {
			if (s.equals("true")) {
				result = 1;
			} else {
				result = 5;
			}
			out = idList.get(index) + "," + result;
			index++;
			if (index < resultList.size())
				out += "\n";
			writer.write(out);
		}

	}
	static void run() throws Exception{
		//change this:
		String directoryHead = "/Users/jibestreamadmin/Documents/POI automation/";
		
		String testNumberFilePath = directoryHead + "POI_4.4.0_TestNumbers.csv";
		File testNumberFile = new File(testNumberFilePath);
		if(testNumberFile.isFile() && testNumberFile.exists()) testNumberFile.delete();
		testNumberFile.getParentFile().mkdirs();
		testNumberFile.createNewFile();
		
		
		List<String> idList = getId_ByRegex(testNumberFilePath);
		testNumberFile.delete();
		
		System.out.println("idlist size ======= "+idList.size());
		
		
		String resultFilePath = directoryHead + "POI_4.4.0_TestPlan_Results.csv";
		BufferedReader resultsReader = new BufferedReader(new FileReader(resultFilePath)); 
		
		
		
		String pairsOutputFilePath = directoryHead + "testId_testResult_Pairs.csv";
		pairsOutputFile = new File(pairsOutputFilePath);
		if(pairsOutputFile.isFile() && pairsOutputFile.exists()) pairsOutputFile.delete();
		pairsOutputFile.getParentFile().mkdirs();
		pairsOutputFile.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(pairsOutputFilePath));
		producePairs(idList, writer, resultsReader);
		
		writer.close();
	}
	
	public static void main(String[] args) throws Exception {
		
		
		 
	}

}
