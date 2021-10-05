package com.ert.wearables.test.core.utilities;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;
import com.opencsv.CSVReader;

public class CSVUtil {
	
	Logger log = Logger.getLogger(CSVUtil.class);
	
	public void readCSV(){
		
		try (CSVReader csvReader = new CSVReader(new FileReader("src/main/java/com/ert/wearables/test/core/testdata/testdata.csv"))){
			List<List<String>> list1 = readCSV1();
			System.out.println(list1);
			System.out.println(list1.get(2));
			System.out.println(list1.get(2).get(1));
			System.out.println(list1.get(2).get(0));
			System.out.println("------");
			
			String[] values = null;
			csvReader.readNext(); //pass header
			List<List<String>> list = new ArrayList<>();
			while((values=csvReader.readNext()) != null ){
				list.add(Arrays.asList(values));
				//System.out.println(Arrays.asList(values));
				//System.out.println(Arrays.asList(values[0]));
				//System.out.println(Arrays.asList(values[1]));
			}
			System.out.println(list);
			System.out.println(list.get(3).get(0));
		} catch (Exception e) {
			log.error(e.getCause().getMessage());
		}
		
	}
		public List<List<String>> readCSV1(){
			List<List<String>> list = new ArrayList<>();
			
			try (CSVReader csvReader = new CSVReader(new FileReader("src/main/java/com/ert/wearables/test/core/testdata/testdata.csv"))){
				String[] values = null;
				csvReader.readNext(); //pass header
				
				while((values=csvReader.readNext()) != null ){
					list.add(Arrays.asList(values));
				}
			} catch (Exception e) {
				
			}
		return list;
		}
		
}
