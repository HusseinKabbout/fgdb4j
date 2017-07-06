package ch.ehi.fgdb4j;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.ehi.fgdb4j.jni.Geodatabase;
import ch.ehi.fgdb4j.jni.fgbd4j;

public class GeodatabaseTest {

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
	public void create() {
		Geodatabase geodatabase=new Geodatabase();
		File fgdbFile=new File("tmp\\testdb.gdb");
		Fgdb4j.deleteFileGdb(fgdbFile);
		int ret=fgbd4j.CreateGeodatabase("tmp\\testdb.gdb", geodatabase);
		fgbd4j.CloseGeodatabase(geodatabase);
		if(ret!=0){
			fail();
		}
	}
	@Test
	public void open_fail() {
		Geodatabase geodatabase=new Geodatabase();
		File fgdbFile=new File("tmp\\testdb.gdb");
		Fgdb4j.deleteFileGdb(fgdbFile);
		int ret=fgbd4j.OpenGeodatabase(fgdbFile.getPath(), geodatabase);
		if(ret!=0){
			StringBuffer errorDescription=new StringBuffer();
			fgbd4j.GetErrorDescription(ret, errorDescription);
			System.out.println("GetErrorDescription("+errorDescription.toString()+")");
			fgbd4j.CloseGeodatabase(geodatabase);
		}else{
			fail();
		}
	}

}
