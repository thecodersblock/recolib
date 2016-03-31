package reco2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.apache.mahout.cf.taste.common.TasteException;

public class MainActivity {
	
	public static PrintWriter writer;

	public static void main(String[] args) throws IOException, TasteException {
		// TODO Auto-generated method stub
		
		writer = new PrintWriter("reco-output.txt", "UTF-8");
		
		
		//Writer.write("\nLess Data");
		//DataProcessor.init("data/paperData.csv", ",", 1);
		

		Writer.write("\n---\nMore Data");
		DataProcessor.init("data/ratings.dat", "::", 0.01);
		
		
		writer.close();
		
	}

}
