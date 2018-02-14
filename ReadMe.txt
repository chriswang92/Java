Run Instruction:

1. In Jmeter GUI, open following testplans:
  a) open POI_4.4.0_Test Plan.jmx: goto User Defined Variables at top, change the value of head.
  b) open POI_4.4.0_getTestId_Plan.jmx: goto View Results Tree, change the filename under "write results to file" part
  
2. In eclipse:
  a) Import java project: FunctionalTestAutomation2
  b) add all .jar files in /lib (including all jar files in /lib/ext)

  c). Change Paths in classes:
    1) change directoryhead In Run.java and pairProducer.java
    2) change the two paths for JMeterUtils.loadJMeterProperties(), JMeterUtils.setJMeterHome() in JMeterFromExistingJMX.java

3.Build & Run: Run.java
