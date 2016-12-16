package util.id3.t2.ia.ufscar;

import java.io.File;
import java.util.Scanner;
import data_structure.id3.t2.ia.ufscar.Attribute;
import data_structure.id3.t2.ia.ufscar.Data;
import data_structure.id3.t2.ia.ufscar.DataSet;

public class InputReader {
	
	public InputReader(){
		
	}
	
	public static DataSet readFileAsSet(String filepath) throws Exception{
		
		DataSet dataSet = new DataSet();
		File f = new File(filepath);
		Scanner s = new Scanner(f);
		
		while(s.hasNextLine()){
			Data data = new Data();
			String line = s.nextLine();
			String []attributes = line.split("[,;]");
			int i = 0;
			for(String attributeValue : attributes){
				String key;
				if(i == attributes.length - 1){
					key = "Class";
				}else{
					key = "A" + i++; 
				}
				
				data.addAttribute(new Attribute(key, attributeValue));
			}
			
			dataSet.add(data);
		}
		
		s.close();
				
		return dataSet;
			
	}
}
