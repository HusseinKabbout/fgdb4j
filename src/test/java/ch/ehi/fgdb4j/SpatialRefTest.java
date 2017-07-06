package ch.ehi.fgdb4j;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.ehi.fgdb4j.jni.*;

public class SpatialRefTest {

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
	public void srsList() {
		Geodatabase geodatabase=new Geodatabase();
		File fgdbFile=new File("tmp\\testdb.gdb");
		Fgdb4j.deleteFileGdb(fgdbFile);
		int ret=0;
		ret=fgbd4j.CreateGeodatabase("tmp\\testdb.gdb", geodatabase);
		if(ret!=0){
			StringBuffer errorDescription=new StringBuffer();
			fgbd4j.GetErrorDescription(ret, errorDescription);
			System.out.println("GetErrorDescription("+errorDescription.toString()+")");
		}
		
		EnumSpatialReferenceInfo srsIt=new EnumSpatialReferenceInfo();
		SpatialReferenceInfo srsInfo = new SpatialReferenceInfo();
		while(srsIt.NextProjectedSpatialReference(srsInfo)){
			int id=srsInfo.getAuth_srid();
			System.out.println(srsInfo.getAuth_name()+":"+srsInfo.getAuth_srid()+":"+srsInfo.getSrname()+":"+srsInfo.getSrtext());
			if(id==2056 || id==21781){
			}
		}
		
		ret=fgbd4j.CloseGeodatabase(geodatabase);
		if(ret!=0){
			StringBuffer errorDescription=new StringBuffer();
			fgbd4j.GetErrorDescription(ret, errorDescription);
			System.out.println("GetErrorDescription("+errorDescription.toString()+")");
		}
	}
	@Test
	public void getSrs2056() {
		Geodatabase geodatabase=new Geodatabase();
		File fgdbFile=new File("tmp\\testdb.gdb");
		Fgdb4j.deleteFileGdb(fgdbFile);
		int ret=0;
		ret=fgbd4j.CreateGeodatabase("tmp\\testdb.gdb", geodatabase);
		if(ret!=0){
			StringBuffer errorDescription=new StringBuffer();
			fgbd4j.GetErrorDescription(ret, errorDescription);
			System.out.println("GetErrorDescription("+errorDescription.toString()+")");
		}
		
		SpatialReferenceInfo srsInfo = new SpatialReferenceInfo();
		if(fgbd4j.FindSpatialReferenceBySRID(2056,srsInfo)){
			System.out.println(srsInfo.getAuth_name()+":"+srsInfo.getAuth_srid()+":"+srsInfo.getSrname()+":"+srsInfo.getSrtext());
		}else{
			fail();
		}
		
		ret=fgbd4j.CloseGeodatabase(geodatabase);
		if(ret!=0){
			StringBuffer errorDescription=new StringBuffer();
			fgbd4j.GetErrorDescription(ret, errorDescription);
			System.out.println("GetErrorDescription("+errorDescription.toString()+")");
		}
	}

}
