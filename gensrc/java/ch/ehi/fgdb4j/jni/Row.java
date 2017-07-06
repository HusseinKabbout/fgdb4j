/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package ch.ehi.fgdb4j.jni;

public class Row {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected Row(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(Row obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        fgbd4jJNI.delete_Row(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public int IsNull(String field, boolean[] isNull) {
    return fgbd4jJNI.Row_IsNull__SWIG_0(swigCPtr, this, field, isNull);
  }

  public int IsNull(int fieldNumber, boolean[] isNull) {
    return fgbd4jJNI.Row_IsNull__SWIG_1(swigCPtr, this, fieldNumber, isNull);
  }

  public int SetNull(String field) {
    return fgbd4jJNI.Row_SetNull__SWIG_0(swigCPtr, this, field);
  }

  public int SetNull(int fieldNumber) {
    return fgbd4jJNI.Row_SetNull__SWIG_1(swigCPtr, this, fieldNumber);
  }

  public int GetOID(int[] objectID) {
    return fgbd4jJNI.Row_GetOID(swigCPtr, this, objectID);
  }

  public int GetGlobalID(Guid globalID) {
    return fgbd4jJNI.Row_GetGlobalID(swigCPtr, this, Guid.getCPtr(globalID), globalID);
  }

  public int GetGeometry(ShapeBuffer shapeBuffer) {
    return fgbd4jJNI.Row_GetGeometry(swigCPtr, this, ShapeBuffer.getCPtr(shapeBuffer), shapeBuffer);
  }

  public int SetGeometry(ShapeBuffer shapeBuffer) {
    return fgbd4jJNI.Row_SetGeometry(swigCPtr, this, ShapeBuffer.getCPtr(shapeBuffer), shapeBuffer);
  }

  public int GetShort(String field, short[] value) {
    return fgbd4jJNI.Row_GetShort__SWIG_0(swigCPtr, this, field, value);
  }

  public int GetShort(int fieldNumber, short[] value) {
    return fgbd4jJNI.Row_GetShort__SWIG_1(swigCPtr, this, fieldNumber, value);
  }

  public int SetShort(String field, short value) {
    return fgbd4jJNI.Row_SetShort__SWIG_0(swigCPtr, this, field, value);
  }

  public int SetShort(int fieldNumber, short value) {
    return fgbd4jJNI.Row_SetShort__SWIG_1(swigCPtr, this, fieldNumber, value);
  }

  public int GetInteger(String field, int[] value) {
    return fgbd4jJNI.Row_GetInteger__SWIG_0(swigCPtr, this, field, value);
  }

  public int GetInteger(int fieldNumber, int[] value) {
    return fgbd4jJNI.Row_GetInteger__SWIG_1(swigCPtr, this, fieldNumber, value);
  }

  public int SetInteger(String field, int value) {
    return fgbd4jJNI.Row_SetInteger__SWIG_0(swigCPtr, this, field, value);
  }

  public int SetInteger(int fieldNumber, int value) {
    return fgbd4jJNI.Row_SetInteger__SWIG_1(swigCPtr, this, fieldNumber, value);
  }

  public int GetFloat(String field, float[] value) {
    return fgbd4jJNI.Row_GetFloat__SWIG_0(swigCPtr, this, field, value);
  }

  public int GetFloat(int fieldNumber, float[] value) {
    return fgbd4jJNI.Row_GetFloat__SWIG_1(swigCPtr, this, fieldNumber, value);
  }

  public int SetFloat(String field, float value) {
    return fgbd4jJNI.Row_SetFloat__SWIG_0(swigCPtr, this, field, value);
  }

  public int SetFloat(int fieldNumber, float value) {
    return fgbd4jJNI.Row_SetFloat__SWIG_1(swigCPtr, this, fieldNumber, value);
  }

  public int GetDouble(String field, double[] value) {
    return fgbd4jJNI.Row_GetDouble__SWIG_0(swigCPtr, this, field, value);
  }

  public int GetDouble(int fieldNumber, double[] value) {
    return fgbd4jJNI.Row_GetDouble__SWIG_1(swigCPtr, this, fieldNumber, value);
  }

  public int SetDouble(String field, double value) {
    return fgbd4jJNI.Row_SetDouble__SWIG_0(swigCPtr, this, field, value);
  }

  public int SetDouble(int fieldNumber, double value) {
    return fgbd4jJNI.Row_SetDouble__SWIG_1(swigCPtr, this, fieldNumber, value);
  }

  public int GetDate(String field, SWIGTYPE_p_tm value) {
    return fgbd4jJNI.Row_GetDate__SWIG_0(swigCPtr, this, field, SWIGTYPE_p_tm.getCPtr(value));
  }

  public int GetDate(int fieldNumber, SWIGTYPE_p_tm value) {
    return fgbd4jJNI.Row_GetDate__SWIG_1(swigCPtr, this, fieldNumber, SWIGTYPE_p_tm.getCPtr(value));
  }

  public int SetDate(String field, long value) {
    return fgbd4jJNI.Row_SetDate__SWIG_0(swigCPtr, this, field, value);
  }

  public int SetDate(int fieldNumber, long value) {
    return fgbd4jJNI.Row_SetDate__SWIG_1(swigCPtr, this, fieldNumber, value);
  }

  public int GetString(String field, StringBuffer value) {
    return fgbd4jJNI.Row_GetString__SWIG_0(swigCPtr, this, field, value);
  }

  public int GetString(int fieldNumber, StringBuffer value) {
    return fgbd4jJNI.Row_GetString__SWIG_1(swigCPtr, this, fieldNumber, value);
  }

  public int SetString(String field, String value) {
    return fgbd4jJNI.Row_SetString__SWIG_0(swigCPtr, this, field, value);
  }

  public int SetString(int fieldNumber, String value) {
    return fgbd4jJNI.Row_SetString__SWIG_1(swigCPtr, this, fieldNumber, value);
  }

  public int GetGUID(String field, Guid value) {
    return fgbd4jJNI.Row_GetGUID__SWIG_0(swigCPtr, this, field, Guid.getCPtr(value), value);
  }

  public int GetGUID(int fieldNumber, Guid value) {
    return fgbd4jJNI.Row_GetGUID__SWIG_1(swigCPtr, this, fieldNumber, Guid.getCPtr(value), value);
  }

  public int SetGUID(String field, Guid value) {
    return fgbd4jJNI.Row_SetGUID__SWIG_0(swigCPtr, this, field, Guid.getCPtr(value), value);
  }

  public int SetGUID(int fieldNumber, Guid value) {
    return fgbd4jJNI.Row_SetGUID__SWIG_1(swigCPtr, this, fieldNumber, Guid.getCPtr(value), value);
  }

  public int GetXML(String field, StringBuffer value) {
    return fgbd4jJNI.Row_GetXML__SWIG_0(swigCPtr, this, field, value);
  }

  public int GetXML(int fieldNumber, StringBuffer value) {
    return fgbd4jJNI.Row_GetXML__SWIG_1(swigCPtr, this, fieldNumber, value);
  }

  public int SetXML(String field, String value) {
    return fgbd4jJNI.Row_SetXML__SWIG_0(swigCPtr, this, field, value);
  }

  public int SetXML(int fieldNumber, String value) {
    return fgbd4jJNI.Row_SetXML__SWIG_1(swigCPtr, this, fieldNumber, value);
  }

  public int GetRaster(String field, Raster raster) {
    return fgbd4jJNI.Row_GetRaster(swigCPtr, this, field, Raster.getCPtr(raster), raster);
  }

  public int SetRaster(String field, Raster raster) {
    return fgbd4jJNI.Row_SetRaster(swigCPtr, this, field, Raster.getCPtr(raster), raster);
  }

  public int GetBinary(String field, ByteArray binaryBuf) {
    return fgbd4jJNI.Row_GetBinary__SWIG_0(swigCPtr, this, field, ByteArray.getCPtr(binaryBuf), binaryBuf);
  }

  public int GetBinary(int fieldNumber, ByteArray binaryBuf) {
    return fgbd4jJNI.Row_GetBinary__SWIG_1(swigCPtr, this, fieldNumber, ByteArray.getCPtr(binaryBuf), binaryBuf);
  }

  public int SetBinary(String field, ByteArray binaryBuf) {
    return fgbd4jJNI.Row_SetBinary__SWIG_0(swigCPtr, this, field, ByteArray.getCPtr(binaryBuf), binaryBuf);
  }

  public int SetBinary(int fieldNumber, ByteArray binaryBuf) {
    return fgbd4jJNI.Row_SetBinary__SWIG_1(swigCPtr, this, fieldNumber, ByteArray.getCPtr(binaryBuf), binaryBuf);
  }

  public int GetFieldInformation(FieldInfo fieldInfo) {
    return fgbd4jJNI.Row_GetFieldInformation(swigCPtr, this, FieldInfo.getCPtr(fieldInfo), fieldInfo);
  }

  public int GetFields(FieldDefs fieldDefs) {
    return fgbd4jJNI.Row_GetFields(swigCPtr, this, FieldDefs.getCPtr(fieldDefs), fieldDefs);
  }

  public Row() {
    this(fgbd4jJNI.new_Row(), true);
  }

}