import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import javax.swing.JTextArea;

import jodd.log.LoggerFactory;


public class ShellUtil {
	
	private static int getPassRate(File file) throws Exception {
		String line;
		int passCount = 0,total = 0;
		BufferedReader br = new BufferedReader(new FileReader(file));
		while((line = br.readLine()) != null) {
			total++;
			if(line.equals("true")) passCount ++;
		}
		br.close();
		return (int) (( passCount / (double) total) * 100);
	}
	
	private static final String binDirectory = "/Users/jibestreamadmin/Documents/apache-jmeter-3.3/apache-jmeter-3.3/bin/";
	private static final String jtlPath = "/Users/jibestreamadmin/Documents/POI\\ automation/result.jtl";
	static final String outputDirectory = "/Users/jibestreamadmin/Documents/POI\\ automation/output111";
	private static final String changeDirectoryCommand = "cd "+binDirectory;
	private static final String generateReportDashboardAndOutputToFileCommand = "./jmeter -g "+jtlPath+" -o "+outputDirectory; 
	
    public static void runShell(JTextArea jta) throws Exception {
    		jta.append("\nStart running Shell commands...");
    		
    		
    		
    		String shStr = changeDirectoryCommand + ";" + generateReportDashboardAndOutputToFileCommand;
    		String[] cmd = new String[] {"sh","-c",shStr};
        Process process = Runtime.getRuntime().exec(cmd);
        int exitValue = process.waitFor();
        if(exitValue != 0) System.out.println("failed :" + shStr);
        else System.out.println("Success :" + shStr);
        
        BufferedReader read = new BufferedReader(new InputStreamReader(process.getInputStream()));
        System.out.println("111");
        String line = null;
        while ((line = read.readLine())!=null){
            System.out.println(line);
        }
        process.destroy();
        read.close();
        jta.append("\nFinish running Shell commands...");
        String dashBoardURL = ShellUtil.outputDirectory + "/index.html" ;
		//URL url = new URL(dashBoardURL);
		jta.append("\nIn this test run : " + getPassRate(resultFile) +" % tests are Passed. For more info, please check Jmeter dashboard: " + dashBoardURL);
		
    }
   
    
    public static void main(String[] args) throws Exception {
    		
    }
    
}