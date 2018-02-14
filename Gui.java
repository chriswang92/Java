

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Timestamp;

import javax.swing.*;


public class Gui extends JFrame{
	
	String[] paths;
	int index;
	String currPath;
	Timestamp timestamp; 
	
	private String chooseFile(JTextArea jta){
		//文件上传
		String path = null;
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Select the file ...");
		fc.setApproveButtonText("OK");
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (JFileChooser.APPROVE_OPTION == fc.showOpenDialog(this)) {
			path = fc.getSelectedFile().getPath();
		    	jta.append("\nFile selected successfully : " + path);
		}
		return  path;
	}
	
	public Gui() {
		
		paths = new String[3];
		index = 0;
		timestamp = new Timestamp(System.currentTimeMillis());
		
		JTextArea jta = new JTextArea();
		JScrollPane jsp = new JScrollPane(jta);
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		jta.append("Please select testplans in this order: getResult -> getTestId -> postResult");
		
		JLabel jl = new JLabel("Select test plans:");
		JButton selectButton = new JButton("SELECT");
		
		selectButton.addActionListener

         (  
              new ActionListener()  
              {  

				@Override
				public void actionPerformed(ActionEvent e) {
					  try {
						  while(index < 3) {
							  if((currPath = chooseFile(jta)) != null){
							  		paths[index] = currPath;
							  		index++;
							  		System.out.println("curr index = "+index+"\n curr paths: \n path0= "+paths[0]+" \n path1= "+paths[1]+"\n path2= "+paths[2]);
							  }
						  }
	           			} catch (Exception e1) {
	           				// TODO Auto-generated catch block
	           				e1.printStackTrace();
	           			}  
					
				}  
              }  
         );
		/*
		JButton fileUploadButton2 = new JButton("getTestId");
		fileUploadButton2.addActionListener

         (  
              new ActionListener()  
              {  

				@Override
				public void actionPerformed(ActionEvent e) {
					  try {
						  path2=doFileUpload(jta);
	           			} catch (Exception e1) {
	           				// TODO Auto-generated catch block
	           				e1.printStackTrace();
	           			}  
					
				}  
              }  
         );
		JButton fileUploadButton3 = new JButton("postResult");
		fileUploadButton3.addActionListener

         (  
              new ActionListener()  
              {  

				@Override
				public void actionPerformed(ActionEvent e) {
					  try {
						  path3=doFileUpload(jta);
	           			} catch (Exception e1) {
	           				// TODO Auto-generated catch block
	           				e1.printStackTrace();
	           			}  
					
				}  
              }  
         );
		*/
		
		JButton runButton = new JButton("RUN!");
		runButton.addActionListener

         (  
              new ActionListener()  
              {  
            	  
          	 	
				@Override
				public void actionPerformed(ActionEvent e) {
					  try {
						    new Thread(new Runnable() {

						           @Override

						            public void run() {
						        	   				
						                     try {
						                    	 	if(paths[0] != null && paths[1] != null &&  paths[2] != null ) {
						                    	 		Run.run(paths[0], paths[1], paths[2], jta);
						                    	 	}
							                    	 else {
							                    		 jta.append("\nNeed select " + (3-index) + " more testplans to run..."+
							                    				 "\nCurrent paths are: \n" + paths[0] + "\n" + paths[1] + "\n" + paths[2]);
							                    	 }
											} catch (Exception e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}

						             }

						          }).start();
	           				
	           				return;
	           			} catch (Exception e1) {
	           				// TODO Auto-generated catch block
	           				e1.printStackTrace();
	           			}  
					
				}  
              }  
         );
		//添加组件
		jp1.add(jl);
		jp1.add(selectButton);
		jp2.add(runButton);
		
		//加入JFrame
		add(jsp, BorderLayout.CENTER);
		
		add(jp2, BorderLayout.SOUTH);
		add(jp1, BorderLayout.NORTH);
		
		//设置窗体
		setTitle("Jibestream-QA test automation GUI");
		setSize(700, 700);
		setLocation(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
    public static void main(String args[]){
       Gui gui = new Gui();
        
    }


}
