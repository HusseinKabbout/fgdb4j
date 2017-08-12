package ch.ehi.fgdb4j;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.ByteOrder;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.ehi.fgdb4j.jni.*;

public class ReadTableTest {

	@BeforeClass
	static public void setup() throws Fgdb4jException{
		Fgdb4j.initialize();
	}
	@AfterClass
	static public void end()
	{
		Fgdb4j.cleanup();
	}
	@Test
	public void querySimple() {
		Geodatabase geodatabase=new Geodatabase();
		File fgdbFile=new File("src\\test\\data\\Querying.gdb");
		int ret=0;
		ret=fgbd4j.OpenGeodatabase(fgdbFile.getPath(), geodatabase);
		if(ret!=0){
			StringBuffer errorDescription=new StringBuffer();
			fgbd4j.GetErrorDescription(ret, errorDescription);
			System.out.println("GetErrorDescription("+errorDescription.toString()+")");
		}

		// Open the Cities table.
		  Table table=new Table();
		  ret = geodatabase.OpenTable("Cities", table);
			if(ret!=0){
				StringBuffer errorDescription=new StringBuffer();
				fgbd4j.GetErrorDescription(ret, errorDescription);
				System.out.println("GetErrorDescription("+errorDescription.toString()+")");
			}
		 		
		  // Perform a simple attribute query: find the names of every city with a 'TERM'
		  // value equal to 'City'.  Return the NAME, Pop1996 and the X, Y coordinates of
		  // each feature.
		  EnumRows attrQueryRows=new EnumRows();
		  ret= table.Search("Shape, CITY_NAME, POP1990", "TYPE = 'city' AND OBJECTID < 10", true, attrQueryRows);
			if(ret!=0){
				StringBuffer errorDescription=new StringBuffer();
				fgbd4j.GetErrorDescription(ret, errorDescription);
				System.out.println("GetErrorDescription("+errorDescription.toString()+")");
			}
		  
		// Iterate through the returned rows.
		  Row              attrQueryRow=new Row();
		  while (attrQueryRows.Next(attrQueryRow) == 0)
		  {
			  StringBuffer cityName=new StringBuffer();
		    ret=attrQueryRow.GetString("CITY_NAME", cityName);
			if(ret!=0){
				StringBuffer errorDescription=new StringBuffer();
				fgbd4j.GetErrorDescription(ret, errorDescription);
				System.out.println("GetErrorDescription("+errorDescription.toString()+")");
			}
		    //attrQueryRow.GetInteger("POP1990", cityPop);
			
		    ShapeBuffer geometry=new ShapeBuffer();
			attrQueryRow.GetGeometry(geometry);
			byte[] buffer=geometry.getBuffer();
			java.nio.ByteBuffer b=java.nio.ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN);
			System.out.println("geometryType "+b.getInt());
		    //geometry.GetPoint(point);
		    System.out.println(cityName.toString());
		  }
		  attrQueryRows.Close(); // Close the EnumRows

		  // Close the table
		  ret= geodatabase.CloseTable(table);
			if(ret!=0){
				StringBuffer errorDescription=new StringBuffer();
				fgbd4j.GetErrorDescription(ret, errorDescription);
				System.out.println("GetErrorDescription("+errorDescription.toString()+")");
			}
		 		  
		  
		ret=fgbd4j.CloseGeodatabase(geodatabase);
		if(ret!=0){
			StringBuffer errorDescription=new StringBuffer();
			fgbd4j.GetErrorDescription(ret, errorDescription);
			System.out.println("GetErrorDescription("+errorDescription.toString()+")");
		}
	}
	@Test
	public void querySQL() {
		Geodatabase geodatabase=new Geodatabase();
		File fgdbFile=new File("src\\test\\data\\Querying.gdb");
		int ret=0;
		ret=fgbd4j.OpenGeodatabase(fgdbFile.getPath(), geodatabase);
		if(ret!=0){
			StringBuffer errorDescription=new StringBuffer();
			fgbd4j.GetErrorDescription(ret, errorDescription);
			System.out.println("GetErrorDescription("+errorDescription.toString()+")");
		}

		 		
		  EnumRows attrQueryRows=new EnumRows();
		  ret=geodatabase.ExecuteSQL("SELECT Shape, CITY_NAME, POP1990 FROM Cities WHERE TYPE = 'city' AND OBJECTID < 10", true, attrQueryRows);
			if(ret!=0){
				StringBuffer errorDescription=new StringBuffer();
				fgbd4j.GetErrorDescription(ret, errorDescription);
				System.out.println("GetErrorDescription("+errorDescription.toString()+")");
			}
		  
		// Iterate through the returned rows.
		  Row              attrQueryRow=new Row();
		  while (attrQueryRows.Next(attrQueryRow) == 0)
		  {
			  StringBuffer cityName=new StringBuffer();
		    ret=attrQueryRow.GetString("CITY_NAME", cityName);
			if(ret!=0){
				StringBuffer errorDescription=new StringBuffer();
				fgbd4j.GetErrorDescription(ret, errorDescription);
				System.out.println("GetErrorDescription("+errorDescription.toString()+")");
			}
		    //attrQueryRow.GetInteger("POP1990", cityPop);
		    //attrQueryRow.GetGeometry(geometry);
		    //geometry.GetPoint(point);
		    System.out.println(cityName.toString());
		  }
		  attrQueryRows.Close(); // Close the EnumRows
		 		  
		  
		ret=fgbd4j.CloseGeodatabase(geodatabase);
		if(ret!=0){
			StringBuffer errorDescription=new StringBuffer();
			fgbd4j.GetErrorDescription(ret, errorDescription);
			System.out.println("GetErrorDescription("+errorDescription.toString()+")");
		}
	}

}
