import java.io.File;

import javax.swing.JTextArea;

import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

public class JMeterFromExistingJMX {
	
	static void runTestPlan(String testPlanFile, JTextArea jta)  throws Exception {
		
		jta.append("\n Start running testplan: " + testPlanFile);
		// JMeter Engine
       StandardJMeterEngine jmeter = new StandardJMeterEngine();

       // Initialize Properties, logging, locale, etc.
       JMeterUtils.loadJMeterProperties("/Users/jibestreamadmin/Documents/apache-jmeter-3.3/apache-jmeter-3.3/bin/jmeter.properties");
       JMeterUtils.setJMeterHome("/Users/jibestreamadmin/Documents/apache-jmeter-3.3/apache-jmeter-3.3");
       
       
       
       //JMeterUtils.initLogging();// you can comment this line out to see extra log messages of i.e. DEBUG level
       JMeterUtils.initLocale();
       
       // Initialize JMeter SaveService
       SaveService.loadProperties();

       // Load existing .jmx Test Plan
       HashTree testPlanTree = SaveService.loadTree(new File(testPlanFile));
       // Run JMeter Test
       jmeter.configure(testPlanTree);
       jmeter.run();
       
       
       jta.append("\n Finish running testplan: " + testPlanFile);
	}
	
	public static void main(String[] args){
		

	}

}
