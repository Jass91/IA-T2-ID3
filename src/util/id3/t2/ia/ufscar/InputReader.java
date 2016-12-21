package util.id3.t2.ia.ufscar;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import data_structure.id3.t2.ia.ufscar.Attribute;
import data_structure.id3.t2.ia.ufscar.AttributeDescription;
import data_structure.id3.t2.ia.ufscar.AttributeDescriptor;
import data_structure.id3.t2.ia.ufscar.Data;
import data_structure.id3.t2.ia.ufscar.DataSet;

public class InputReader {

	public InputReader(){

	}

	private static AttributeDescriptor createAttributesDescriptor(Scanner s) throws Exception{

		int currentLine = 1;
		int numberOfAttributes = 0;
		Matcher m = null;
		Pattern p = null;
		AttributeDescriptor attrDescriptor = new AttributeDescriptor();

		// le o numero de atributos
		p = Pattern.compile("Atributos:\\s*(\\d+)");
		m = p.matcher(s.nextLine());
		currentLine++;
		if (m.find()) {
			numberOfAttributes = Integer.parseInt(m.group(1));
		}else {
			System.out.println("NO MATCH");
			throw new Exception("Invalid file format");
		}

		// le cada atributo e seus possiveis valores
		for(int i = 0; i < numberOfAttributes + 1; i++){
			String line = s.nextLine();
			currentLine++;
			p = Pattern.compile("(.*):\\s*(.*)");
			m = p.matcher(line);
			if (m.find()) {
				AttributeDescription attrDescription = new AttributeDescription();
				String label = m.group(1);
				String []values = m.group(2).replace(" ", "").split("[,;]");
				attrDescription.addLabel(label);
				for(String v : values){
					attrDescription.addValue(v);
				}

				attrDescriptor.addAttributeDescription(attrDescription);

			}else {
				s.close();
				System.out.println("NO MATCH");
				throw new Exception();
			}
		}

		// pula a linha vazia
		if(!s.nextLine().equals("")){
			throw new Exception("Invalid file format: expected a blank line at line #" + currentLine);
		}

		return attrDescriptor;
	}

	public static DataSet readTestDataAsSet(String filepath) throws Exception{
		DataSet dataSet = new DataSet();
		File f = new File(filepath);
		Scanner s = new Scanner(f);
		String []attributeList;
		int currentLine = 1;

		// le o numero de atributos
		String line = s.nextLine();
		currentLine++;
		Pattern p = Pattern.compile("Atributos:\\s*(.*)");
		Matcher m = p.matcher(line);
		if (m.find()) {
			attributeList = m.group(1).replace(" ", "").split("[,;]");
		}else {
			s.close();
			System.out.println("NO MATCH");
			throw new Exception("Invalid file format");
		}

		// pula a linha vazia
		if(!s.nextLine().equals("")){
			s.close();
			throw new Exception("Invalid file format: expected a blank line at line #" + currentLine);
		}

		// le os dados
		int label = 1;
		while(s.hasNextLine()){
			Data data = new Data("D" + label++);
			line = s.nextLine();
			String []attributes = line.split("[,;]");
			int i = 0;
			for(String attributeValue : attributes){
				String key;
				if(i < attributeList.length)
					key = attributeList[i++];
				else
					key = "Classe";

				data.addAttribute(new Attribute(key, attributeValue));
			}

			dataSet.add(data);
		}

		s.close();

		return dataSet;
	}

	public static DataSet readTrainingDataAsSet(String filepath) throws Exception{

		File f = new File(filepath);
		Scanner s = new Scanner(f);
		DataSet dataSet = new DataSet();
		
		try{

			AttributeDescriptor ad = createAttributesDescriptor(s);
			List<String> attributeList = ad.getAttributeList();

			// le os dados
			int label = 1;
			while(s.hasNextLine()){
				Data data = new Data("D" + label++);
				String line = s.nextLine();
				String []attributes = line.split("[,;]");
				int i = 0;
				for(String attributeValue : attributes){
					String key = attributeList.get(i++);
					data.addAttribute(new Attribute(key, attributeValue));
				}

				dataSet.add(data);
			}

			dataSet.addAtributeDescriptor(ad);
			dataSet.initialize();

			return dataSet;
			
		}catch(Exception e){
			throw e;
			
		}finally{
			s.close();
		}

	}
}
