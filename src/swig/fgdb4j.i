%module fgbd4j
%{
#include <ctime>
#include "FileGDBAPI.h"
#include "ce_time.h"
%}
 
/* Parse the header file to generate wrappers */
%include <windows.i>
#define _declspec(WINDOWS_EXTENDED_ATTRIBUTE)

%include "std_vector.i"
%include "std_string.i"
%include "std_wstring.i"
%include "OutString.i"
%include "OutWString.i"
%include "std_vector.i"

%include <typemaps.i>
%apply bool &OUTPUT { bool &};
%apply short &OUTPUT { short &};
%apply int &OUTPUT { int &};
%apply int &OUTPUT { FileGDBAPI::FieldType &};
%apply int &OUTPUT { FileGDBAPI::CurveType &};
%apply int &OUTPUT { FileGDBAPI::ShapeType &};
%apply int &OUTPUT { FileGDBAPI::GeometryType &};
%apply long &OUTPUT { long &};
%apply float &OUTPUT { float &};
%apply double &OUTPUT { double &};

//%include "struct_tm.i"


//%clear const struct tm& value;
//%clear struct tm& value;
//%apply structtm&     { struct tm& value };

%include "enums.swg"
%javaconst(1);

namespace std {
  %template(FieldDefs) vector<FileGDBAPI::FieldDef>;
  %template(IndexDefs) vector<FileGDBAPI::IndexDef>;
};
//%include "InVectorFieldDef.i"

%rename(equal) FileGDBAPI::Guid::operator==;
%rename(notEqual) FileGDBAPI::Guid::operator!=;

/*%newobject createGeodatabase;
%newobject openGeodatabase;
%inline {
   
  FileGDBAPI::Geodatabase* createGeodatabase(const std::wstring& path) {
    FileGDBAPI::Geodatabase* value = new FileGDBAPI::Geodatabase();
    checkResult(FileGDBAPI::CreateGeodatabase(path, *value));
    return value;
  }
  FileGDBAPI::Geodatabase* openGeodatabase(const std::wstring& path) {
    FileGDBAPI::Geodatabase* value = new FileGDBAPI::Geodatabase();
    checkResult(FileGDBAPI::OpenGeodatabase(path, *value));
    return value;
  }
  
}*/

%include "FileGDBCore.h"
%include "GeodatabaseManagement.h"

%ignore FileGDBAPI::CreateGeodatabase;
%ignore FileGDBAPI::OpenGeodatabase;
%ignore FileGDBAPI::CloseGeodatabase;
%ignore FileGDBAPI::DeleteGeodatabase;
%include "Geodatabase.h"
%include "Table.h"
%include "Row.h"

%ignore FileGDBAPI::ByteArray::allocatedLength;
%ignore FileGDBAPI::ByteArray::inUseLength;
%ignore FileGDBAPI::ByteArray::Allocate;
%ignore FileGDBAPI::ByteArray::byteArray;

%ignore FileGDBAPI::ShapeBuffer::allocatedLength;
%ignore FileGDBAPI::ShapeBuffer::inUseLength;
%ignore FileGDBAPI::ShapeBuffer::Allocate;
%ignore FileGDBAPI::ShapeBuffer::shapeBuffer;

// JNI C types. These provide the default mapping of types from C/C++ to JNI for use in the JNI (C/C++) code.
%typemap(jni) byte* "jbyteArray"
// Java intermediary types. These provide the default mapping of types from C/C++ to Java for use in the 
// native functions in the intermediary JNI class. The type must be the equivalent Java type for 
// the JNI C type specified in the "jni" typemap.
%typemap(jtype) byte* "byte[]"
// Java types. These provide the default mapping of types from C/C++ to Java for use in the Java 
// module class, proxy classes and type wrapper classes.
%typemap(jstype) byte* "byte[]"
// Conversion from jstype to jtype. 
%typemap(javain) byte* "$javainput"
// Conversion from jtype to jstype.
%typemap(javaout) byte* {
    return $jnicall;
  }
// The "in" typemap is used to convert function arguments from the target language to C
%typemap(in) byte*ByteArrayIN {
  if ($input) {
    jsize size = (jsize) JCALL1(GetArrayLength, jenv, $input);
    if((arg1)->allocatedLength<size){
    	    (arg1)->Allocate(size);
    }
    JCALL4(GetByteArrayRegion, jenv, $input, 0, size, (jbyte *)(arg1)->byteArray);
    (arg1)->inUseLength=size;
  }else{
    (arg1)->inUseLength=0;
  }
}
%typemap(in) byte*ShapeBufferIN {
  if ($input) {
    jsize size = (jsize) JCALL1(GetArrayLength, jenv, $input);
    if((arg1)->allocatedLength<size){
    	    (arg1)->Allocate(size);
    }
    JCALL4(GetByteArrayRegion, jenv, $input, 0, size, (jbyte *)(arg1)->shapeBuffer);
    (arg1)->inUseLength=size;
  }else{
    (arg1)->inUseLength=0;
  }
}
// The "out" typemap is used to convert function/method return values from C into the target language
%typemap(out)      byte*FileGDBAPI::ByteArray::getBuffer() {
    jresult = JCALL1(NewByteArray, jenv, (jsize)arg1->inUseLength);
    JCALL4(SetByteArrayRegion, jenv, jresult, 0, (jsize)arg1->inUseLength, (jbyte *)arg1->byteArray);
}
%typemap(out)      byte*FileGDBAPI::ShapeBuffer::getBuffer() {
    jresult = JCALL1(NewByteArray, jenv, (jsize)arg1->inUseLength);
    JCALL4(SetByteArrayRegion, jenv, jresult, 0, (jsize)arg1->inUseLength, (jbyte *)arg1->shapeBuffer);
}
// The "argout" typemap is used to return values from arguments.
// The "freearg" typemap is used to cleanup argument data.
// The "ret" typemap is not used very often, but can be useful for anything associated with the return type, such as resource management, return value error checking, etc. 
%extend FileGDBAPI::ByteArray {
	void setBuffer(byte *ByteArrayIN){
	}
	byte *getBuffer(){
		return self->byteArray;
	}
}
%extend FileGDBAPI::ShapeBuffer {
	void setBuffer(byte *ShapeBufferIN){
	}
	byte *getBuffer(){
		return self->shapeBuffer;
	}
}


%ignore FileGDBAPI::Row::GetDate;
%ignore FileGDBAPI::Row::SetDate;

%extend FileGDBAPI::Row {
	int setDateTime(const std::wstring& field,const struct ce_time &in){
		struct tm tmp;
		tmp.tm_sec=in.tm_sec;
		tmp.tm_min=in.tm_min;	
		tmp.tm_hour=in.tm_hour;	
		tmp.tm_mday=in.tm_mday;	
		tmp.tm_mon=in.tm_mon;	
		tmp.tm_year=in.tm_year;		
		tmp.tm_wday=in.tm_wday;	
		tmp.tm_yday=in.tm_yday;	
		tmp.tm_isdst=in.tm_isdst;	
		return self->SetDate(field,tmp);
	}
	int setDateTime(int fieldNumber,const struct ce_time &in){
		struct tm tmp;
		tmp.tm_sec=in.tm_sec;
		tmp.tm_min=in.tm_min;	
		tmp.tm_hour=in.tm_hour;	
		tmp.tm_mday=in.tm_mday;	
		tmp.tm_mon=in.tm_mon;	
		tmp.tm_year=in.tm_year;		
		tmp.tm_wday=in.tm_wday;	
		tmp.tm_yday=in.tm_yday;	
		tmp.tm_isdst=in.tm_isdst;	
		return self->SetDate(fieldNumber,tmp);
	}
	int getDateTime(const std::wstring& field,struct ce_time &out){
		struct tm tmp;
		int ret=self->GetDate(field,tmp);
		out.tm_sec=tmp.tm_sec;
		out.tm_min=tmp.tm_min;	
		out.tm_hour=tmp.tm_hour;	
		out.tm_mday=tmp.tm_mday;	
		out.tm_mon=tmp.tm_mon;	
		out.tm_year=tmp.tm_year;		
		out.tm_wday=tmp.tm_wday;	
		out.tm_yday=tmp.tm_yday;	
		out.tm_isdst=tmp.tm_isdst;	
		return ret;
	}
	int getDateTime(int fieldNumber,struct ce_time &out){
		struct tm tmp;
		int ret=self->GetDate(fieldNumber,tmp);
		out.tm_sec=tmp.tm_sec;
		out.tm_min=tmp.tm_min;	
		out.tm_hour=tmp.tm_hour;	
		out.tm_mday=tmp.tm_mday;	
		out.tm_mon=tmp.tm_mon;	
		out.tm_year=tmp.tm_year;		
		out.tm_wday=tmp.tm_wday;	
		out.tm_yday=tmp.tm_yday;	
		out.tm_isdst=tmp.tm_isdst;	
		return ret;
	}
}

%include "Util.h"
%include "Raster.h"
%include "ce_time.h"


