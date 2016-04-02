package examples;

import java.io.IOException;
import java.io.PrintWriter;
import org.apache.mahout.cf.taste.common.TasteException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import reco2.DataProcessor;
import reco2.Writer;

public class Performanceval {

	public static PrintWriter writer;

	public static void main(String[] args) throws IOException, TasteException {
		// TODO Auto-generated method stub

		writer = new PrintWriter("data/reco-output.txt", "UTF-8");
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		writer.write(""+dateobj);
		Writer.write("\nLess Data");
		DataProcessor.init("data/paperData.csv", ",", 1);

		// Writer.write("\n---\nMore Data");
		// DataProcessor.init("data/ratings.dat", "::", 0.01);

		writer.close();

	}

}
