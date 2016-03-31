package reco2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVParser {
	
	static int  maxU=0, maxM=0;

	public static double[][] do_it(String path, String split, double evaluate_percent) throws IOException{
		
		double[][] data;
		
		if(evaluate_percent<0.9)
			data = new double[6550][4000];
		else
			data = new double[100][100];
		
		//System.out.println("Here 1");
		
		String csvFile = path;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = split;

		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {

		    //use comma as separator
			String[] a = line.split(cvsSplitBy);
			
			int userID = Integer.parseInt(a[0]);
			int movieID = Integer.parseInt(a[1]);
			float ratings = Float.parseFloat(a[2]);
			
			data[userID][movieID] = ratings;
			
			if(userID>maxU)
				maxU = userID;
			if(movieID>maxM)
				maxM = movieID;
			
			
			//System.out.println(" " + a[0]+ ", "+ a[1] + ", " + a[2]);
			
		}
		//System.out.println("Here 2");
		br.close();
		maxM++;
		maxU++;
		//maxU++;
		
		//double[][] smallData = new double[maxU][maxM];
		//smallData = smallify(data);
		/*
		for(int i=0;i<maxM;i++)
			smallData[maxU-1][i] = app.userInputs[i]; */
		
		//return smallData;
		

		//System.out.println(smallData[1][3408]);
		return data;
		
	}
	
	static void display(double[][] data)
	{
		System.out.print("\n---\nUsers\t");
		for(int i=0;i<CSVParser.maxM;i++)
			System.out.print("Movie"+Integer.toString(i)+"\t");
		System.out.print("\n");
		
		for(int i=0;i<CSVParser.maxU;i++)
		{
			System.out.print("User"+Integer.toString(i)+"\t");
			for(int j=0;j<CSVParser.maxM;j++)
			{
				System.out.print( data[i][j] + "\t" );
			}
			System.out.print("\n");
		}
	}

	public static double[][] smallify(double[][] data) {
		// TODO Auto-generated method stub
		double[][] smallData = new double[maxU][maxM];
		for(int i=0;i<maxU-1;i++)
			for(int j=0;j<maxM;j++)
				smallData[i][j] = data[i][j];
		return smallData;
	}
	
	
}
