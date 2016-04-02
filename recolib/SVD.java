package recolib;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;

import reco2.Writer;

public class SVD {
	
	
	
	
	static DataModel getU(double[][] data) throws IOException
	{
		int rlimit = ModelToMatrix.users_size;
		int climit = ModelToMatrix.items_size;
		RealMatrix m=MatrixUtils.createRealMatrix(data);
		 System.out.println("1");
		 if(rlimit>100)
			 rlimit = 100;
		 if(climit>100)
			 climit = 100;
		 RealMatrix mm = m.getSubMatrix(0,rlimit, 0, climit);
		 System.out.println("2");
		 SingularValueDecomposition s =new SingularValueDecomposition(mm);
		 System.out.println("3");
		 RealMatrix u =s.getU();
		 
		 File file = new File("data/tempo.csv");
		 file.createNewFile(); 
		
		 System.out.println(rlimit + " and " + climit);
		  
		 FileWriter writer = new FileWriter(file); 
		 for(int i=0;i<rlimit;i++)
		 {
			 String svdDataString = "";
			 
			 for(int j=0;j<climit;j++)
			 {
				System.out.println(i + ", " + j + "\n");
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
		
		DataModel SVDmodel = new FileDataModel(new File("data/tempo.csv"), ",");
		return SVDmodel;
	}

}
