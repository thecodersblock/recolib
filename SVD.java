package reco2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;


public class SVD {

	public static String getUMat(String file_path, String split, double evaluate_percent) throws IOException {
		// TODO Auto-generated method stub
		int climit = 100;
		int rlimit=2000;
		 
		if(evaluate_percent<0.9)
		{
			climit = 100; rlimit=3000;
		}
		else
		{
			climit= 10; rlimit=10;
		}
		 
			
			 
			 double[][] smallData = CSVParser.do_it(file_path, split, evaluate_percent);
			 Writer.write("h1");
			 RealMatrix m=MatrixUtils.createRealMatrix(smallData);
			 //printRM(m);
			 Writer.write("h2");
			 RealMatrix mm = m.getSubMatrix(0,rlimit, 0,climit);
			 Writer.write("h3");
			 SingularValueDecomposition s =new SingularValueDecomposition(mm);
			 Writer.write("h4");
			 RealMatrix u =s.getU();
			 Writer.write("h5");
			 
			 File file = new File("data/22.csv");
			 file.createNewFile(); 
			
			  
			 
			 FileWriter writer = new FileWriter(file); 
			 for(int i=0;i<rlimit-1;i++)
			 {
				 String svdDataString = "";
				 
				 for(int j=0;j<climit;j++)
				 {
					 
					svdDataString += (Integer.toString(i) + ",");
					svdDataString += (Integer.toString(j) + ",");
					svdDataString +=  BigDecimal.valueOf(u.getEntry(i, j)).toPlainString();// (Double.toString( u.getEntry(i, j) ));
					svdDataString += ("\n");
					 
				 }
				 writer.append(svdDataString); 
				
			 }
			 writer.flush();
			 writer.close();
			 
			 Writer.print("Writing on file completed.");
		
		return "data/22.csv";
	}
	
	static void printRM(RealMatrix x)
	{
		System.out.println("\n---\n");
		int rows = x.getRowDimension();
		int cols = x.getColumnDimension();
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				System.out.print(x.getEntry(i, j) + "\t");
			}
			System.out.print("\n");
		}
		System.out.println("\n---\n");
	}
	

}
