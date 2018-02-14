import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;

import javax.swing.JTextArea;

public class Run {

	
	
	
	public static void run(String testPlanPath, String getTestIdPlanPath
			, String postResultPlanPath, JTextArea jta) throws Exception {
		
		
		
		
		if(filesAndFoldersHandler.cleanFilesAndDirectories()) {
			jta.append("\ncleanFilesAndDirectories succceed, start createFilesAndDirectories...");
			filesAndFoldersHandler.createFilesAndDirectories();
		}else {
			jta.append("\ncleanFilesAndDirectories failed, quit system...");
			System.exit(0);
		}
		//create result file
		
		
		
		
		JMeterFromExistingJMX.runTestPlan(testPlanPath, jta);
		
	
		JMeterFromExistingJMX.runTestPlan(getTestIdPlanPath, jta);
	
		jta.append("\nStart running javaclass: PairProducer");
		
		PairProducer.run();
		
		JMeterFromExistingJMX.runTestPlan(postResultPlanPath, jta);
		
		
		
		ShellUtil.runShell(jta);
		
		if(filesAndFoldersHandler.cleanFilesAndDirectories()) {
			jta.append("\ncleanFilesAndDirectories succceed, start createFilesAndDirectories...");
		}else {
			jta.append("\ncleanFilesAndDirectories failed, quit system...");
			System.exit(0);
		}
		PairProducer.pairsOutputFile.delete();
		System.exit(0);
	}
	
	
	public static void main(String[] args) throws Exception{
			
	}

}
