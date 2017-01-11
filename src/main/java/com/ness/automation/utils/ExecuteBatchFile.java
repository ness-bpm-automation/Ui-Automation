package com.ness.automation.utils;

import java.io.BufferedReader;
import java.io.FileReader;

public class ExecuteBatchFile {
	
	public static void main(final String[] args) throws Exception {
		
		String filePath = "C:\\Anil\\Nagesh\\pega-tests\\TestExecutionResult.html";
		
		FileReader filer = new FileReader(filePath);
		BufferedReader buffr = new BufferedReader(filer);
		  
		boolean eof = false;
		while (!eof)
		  {
		  String s = buffr.readLine();
		  if(s == null)
		    {
		    eof = true;
		    }
		  else
		    {
		    System.out.println(s);
		    }
		  }
		
	   /* // The batch file to execute
	    final File batchFile = new File("C:\\Anil\\Nagesh\\pega-tests\\pega-devprocess.bat");

	    // The output file. All activity is written to this file
	    final File outputFile = new File(String.format("C:\\Anil\\Nagesh\\pega-tests\\executebatch.txt",
	        System.currentTimeMillis()));

	    // The argument to the batch file. 
	    //final String argument = "Albert Attard";

	    // Create the process
	    final ProcessBuilder processBuilder = new ProcessBuilder(batchFile.getAbsolutePath());
	    // Redirect any output (including error) to a file. This avoids deadlocks
	    // when the buffers get full. 
	    processBuilder.redirectErrorStream(true);
	    processBuilder.redirectOutput(outputFile);

	    // Add a new environment variable
	    processBuilder.environment().put("message", "Example of process builder");

	    // Set the working directory. The batch file will run as if you are in this
	    // directory.
	    processBuilder.directory(new File("work"));

	    // Start the process and wait for it to finish. 
	    final Process process = processBuilder.start();
	    final int exitStatus = process.waitFor();*/
		
		/*final String dir = System.getProperty("user.dir");
			
        System.out.println("current dir = " + dir + "/pega-tests");
		
		String commandArray[] = {"cmd", "/c", "dir", "C:\\Anil\\Nagesh\\pega-tests\\pega-devprocess.bat"};
		 
		Process process = Runtime.getRuntime().exec(commandArray);*/

		
		//Process p = Runtime.getRuntime().exec("cmd /c start cd  C:\\Anil\\Nagesh\\pega-tests\\pega-devprocess.bat");
		
		//Process p =Runtime.getRuntime().exec("cmd /c start C:\\Anil\\Nagesh\\pega-tests\\pega-devprocess.bat");
		
	     // Process process = Runtime.getRuntime ().exec ("C://Anil//Nagesh//pega-tests//pega-devprocess.bat");
		
		/*try {
            Runtime rt = Runtime.getRuntime();
            //Process pr = rt.exec("cmd /c dir");
            Process pr = rt.exec("C:\\Anil\\Nagesh\\pega-tests\\pega-devprocess.bat");

            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

            String line=null;

            while((line=input.readLine()) != null) {
                System.out.println(line);
            }

            int exitVal = pr.waitFor();
            System.out.println("Exited with error code "+exitVal);

        } catch(Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }*/
		
		
		 /*String[] command = {"CMD", "/C", "dir"};
	        ProcessBuilder probuilder = new ProcessBuilder( command );
	        //You can set up your work directory
	        probuilder.directory(new File("C:\\Anil\\Nagesh\\pega-tests"));
	        
	        Process process = probuilder.start();
	        
	        //Read out dir output
	        InputStream is = process.getInputStream();
	        InputStreamReader isr = new InputStreamReader(is);
	        BufferedReader br = new BufferedReader(isr);
	        String line;
	        System.out.printf("Output of running %s is:\n",
	                Arrays.toString(command));
	        while ((line = br.readLine()) != null) {
	            System.out.println(line);
	        }
	        
	        //Wait to get exit value
	        try {
	            int exitValue = process.waitFor();
	            System.out.println("\n\nExit Value is " + exitValue);
	        } catch (InterruptedException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }*/
		
		/*File file=new File(".");
	      System.out.println("Current Working Directory: " + file.getAbsolutePath());
	      System.setProperty("user.dir", "C://Anil//Nagesh//pega-tests");
	      System.out.println("New Current Working Directory: " + file.getAbsolutePath());
	      
	      String path = System.getProperty("user.dir");
	      
	      //String realtivePath="\\ ..\\..\\Temp";
	      
	      //path+=realtivePath;
			
	      System.out.println("current dir = " + path);
	      
	      //Process p = Runtime.getRuntime().exec("", null, new File(path));
	      
	      Runtime.getRuntime().exec("cmd /C start pega-devprocess.bat");*/
	      
	    /*  try
	        {
	            Process p;
	            String cmd=System.getProperty("user.dir");
	            File f=new File(path+"/pega-devprocess.bat");

	            p = Runtime.getRuntime().exec(cmd, null, f);

	            BufferedReader in = new BufferedReader
	                (new InputStreamReader(p.getInputStream()));

	            String s = in.readLine();
	            if(s.compareTo("/")!=0)
	                  System.out.println("Error: directory differes from expected:"+s);
	            else System.out.println("Passed.");
	        }
	        catch(Exception e)
	        {
	            System.out.println(e);
	        }*/
	    //}
	      
	      /*Process p = Runtime.getRuntime().exec(command.toString(), null, 
                  new File("D:\\Cognity"));*/

		
	 //   System.out.println("Processed finished with status: " );
	  }
}

//}
