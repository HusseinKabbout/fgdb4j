package ch.ehi.fgdb4j;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.ehi.fgdb4j.jni.*;

public class CreateTableTest {

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
	public void createSimple() {
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
		Table table=new Table();
		FieldDefs fieldDefs=new FieldDefs();
		FieldDef fieldDef1=new FieldDef();
		  fieldDef1.SetName ("OBJECTID");
		  fieldDef1.SetType (FieldType.fieldTypeOID);
		  fieldDef1.SetIsNullable(false);
		  fieldDefs.add(fieldDef1);
		ret=geodatabase.CreateTable("streets", fieldDefs, "", table);
		if(ret!=0){
			StringBuffer errorDescription=new StringBuffer();
			fgbd4j.GetErrorDescription(ret, errorDescription);
			System.out.println("GetErrorDescription("+errorDescription.toString()+")");
		}
		ret=geodatabase.CloseTable(table);
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
	public void tableExists() {
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
		Table table=new Table();
		FieldDefs fieldDefs=new FieldDefs();
		FieldDef fieldDef1=new FieldDef();
		  fieldDef1.SetName ("OBJECTID");
		  fieldDef1.SetType (FieldType.fieldTypeOID);
		  fieldDef1.SetIsNullable(false);
		  fieldDefs.add(fieldDef1);
		ret=geodatabase.CreateTable("streets", fieldDefs, "", table);
		if(ret!=0){
			StringBuffer errorDescription=new StringBuffer();
			fgbd4j.GetErrorDescription(ret, errorDescription);
			System.out.println("GetErrorDescription("+errorDescription.toString()+")");
		}
		ret=geodatabase.OpenTable("streets", table);
		if(ret!=0){
			fail();
		}
		ret=geodatabase.OpenTable("xy", table);
		if(ret!=Fgdb4j.FGDB_E_TABLE_NOT_FOUND){
			fail();
		}
		ret=fgbd4j.CloseGeodatabase(geodatabase);
		if(ret!=0){
			StringBuffer errorDescription=new StringBuffer();
			fgbd4j.GetErrorDescription(ret, errorDescription);
			System.out.println("GetErrorDescription("+errorDescription.toString()+")");
		}
	}
	@Test
	public void createWithGeom() {
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

		  SpatialReference spatialReference=new SpatialReference();
		  spatialReference.SetSpatialReferenceText ("PROJCS[\"World_Robinson\",GEOGCS[\"GCS_WGS_1984\",DATUM[\"D_WGS_1984\",SPHEROID[\"WGS_1984\",6378137.0,298.257223563]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Robinson\"],PARAMETER[\"False_Easting\",0.0],PARAMETER[\"False_Northing\",0.0],PARAMETER[\"Central_Meridian\",0.0],UNIT[\"Meter\",1.0],AUTHORITY[\"ESRI\",54030]]");
		  spatialReference.SetSpatialReferenceID(54030); 
		  //spatialReference.SetXYFalseOrigin(-16987000, -8615900);
		  //spatialReference.SetXYResolution(.0001);
		  //spatialReference.SetXYTolerance(.001);

		  GeometryDef geometryDef=new GeometryDef();
		  geometryDef.SetGeometryType (GeometryType.geometryPolyline);
		  geometryDef.SetSpatialReference(spatialReference);
		  geometryDef.SetHasZ(false); //Set to true if the feature class is to be Z enabled. Defaults to FALSE.
		  geometryDef.SetHasM(false); //Set to true if the feature class is to be M enabled. Defaults to FALSE.

		  
		
		Table table=new Table();
		FieldDefs fieldDefs=new FieldDefs();
		FieldDef fieldDef1=new FieldDef();
		  fieldDef1.SetName ("OBJECTID");
		  fieldDef1.SetType (FieldType.fieldTypeOID);
		  fieldDef1.SetIsNullable(false);
		  fieldDefs.add(fieldDef1);
		  
		  FieldDef fieldDef2=new FieldDef();
		  fieldDef2.SetName ("Shape");
		  fieldDef2.SetType (FieldType.fieldTypeGeometry);
		  fieldDef2.SetIsNullable(true);
		  fieldDef2.SetGeometryDef (geometryDef);
		  fieldDefs.add(fieldDef2);		  
		  
		ret=geodatabase.CreateTable("streets", fieldDefs, "", table);
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
	public void createWithLV95() {
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
		if(!fgbd4j.FindSpatialReferenceBySRID(2056,srsInfo)){
			fail();
		}
		  SpatialReference spatialReference=new SpatialReference();
		  spatialReference.SetSpatialReferenceText (srsInfo.getSrtext());
		  spatialReference.SetSpatialReferenceID(srsInfo.getAuth_srid()); 
		  //spatialReference.SetXYFalseOrigin(-16987000, -8615900);
		  //spatialReference.SetXYResolution(.0001);
		  //spatialReference.SetXYTolerance(.001);

		  GeometryDef geometryDef=new GeometryDef();
		  geometryDef.SetGeometryType (GeometryType.geometryPolyline);
		  geometryDef.SetSpatialReference(spatialReference);
		  geometryDef.SetHasZ(false); //Set to true if the feature class is to be Z enabled. Defaults to FALSE.
		  geometryDef.SetHasM(false); //Set to true if the feature class is to be M enabled. Defaults to FALSE.

		  
		
		Table table=new Table();
		FieldDefs fieldDefs=new FieldDefs();
		FieldDef fieldDef1=new FieldDef();
		  fieldDef1.SetName ("OBJECTID");
		  fieldDef1.SetType (FieldType.fieldTypeOID);
		  fieldDef1.SetIsNullable(false);
		  fieldDefs.add(fieldDef1);
		  
		  FieldDef fieldDef2=new FieldDef();
		  fieldDef2.SetName ("Shape");
		  fieldDef2.SetType (FieldType.fieldTypeGeometry);
		  fieldDef2.SetIsNullable(true);
		  fieldDef2.SetGeometryDef (geometryDef);
		  fieldDefs.add(fieldDef2);		  
		  
		ret=geodatabase.CreateTable("streets", fieldDefs, "", table);
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
	public void createIndex() {
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
		Table streetsTable=new Table();
		FieldDefs fieldDefs=new FieldDefs();
		FieldDef fieldDef1=new FieldDef();
		  fieldDef1.SetName ("OBJECTID");
		  fieldDef1.SetType (FieldType.fieldTypeOID);
		  fieldDef1.SetIsNullable(false);
		  fieldDefs.add(fieldDef1);
			ret=geodatabase.CreateTable("streets", fieldDefs, "", streetsTable);
			if(ret!=0){
				StringBuffer errorDescription=new StringBuffer();
				fgbd4j.GetErrorDescription(ret, errorDescription);
				System.out.println("GetErrorDescription("+errorDescription.toString()+")");
			}
		  
		  FieldDef fieldDefAdd=new FieldDef();
		  fieldDefAdd.SetName ("StreetType");
		  fieldDefAdd.SetType (FieldType.fieldTypeSmallInteger);
		  fieldDefAdd.SetIsNullable(true);
		  ret=streetsTable.AddField(fieldDefAdd);
			if(ret!=0){
				StringBuffer errorDescription=new StringBuffer();
				fgbd4j.GetErrorDescription(ret, errorDescription);
				System.out.println("GetErrorDescription("+errorDescription.toString()+")");
			}
		  
		
		  IndexDef indexDef=new IndexDef();
		  indexDef.SetFields("StreetType");
		  indexDef.SetName("StreeetTypeIdx");
		  indexDef.SetIsUnique(false);
		  ret=streetsTable.AddIndex(indexDef);
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

}
