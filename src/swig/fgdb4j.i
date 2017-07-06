%module fgbd4j
%{
#include <ctime>
#include "FileGDBAPI.h"
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

%include "struct_tm.i"
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
%include "Util.h"
%include "Raster.h"


