/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package ch.ehi.fgdb4j.jni;

public class FieldDef {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected FieldDef(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(FieldDef obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        fgbd4jJNI.delete_FieldDef(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public FieldDef() {
    this(fgbd4jJNI.new_FieldDef(), true);
  }

  public int GetName(StringBuffer name) {
    return fgbd4jJNI.FieldDef_GetName(swigCPtr, this, name);
  }

  public int SetName(String name) {
    return fgbd4jJNI.FieldDef_SetName(swigCPtr, this, name);
  }

  public int GetAlias(StringBuffer alias) {
    return fgbd4jJNI.FieldDef_GetAlias(swigCPtr, this, alias);
  }

  public int SetAlias(String alias) {
    return fgbd4jJNI.FieldDef_SetAlias(swigCPtr, this, alias);
  }

  public int GetType(int[] type) {
    return fgbd4jJNI.FieldDef_GetType(swigCPtr, this, type);
  }

  public int SetType(FieldType type) {
    return fgbd4jJNI.FieldDef_SetType(swigCPtr, this, type.swigValue());
  }

  public int GetLength(int[] length) {
    return fgbd4jJNI.FieldDef_GetLength(swigCPtr, this, length);
  }

  public int SetLength(int length) {
    return fgbd4jJNI.FieldDef_SetLength(swigCPtr, this, length);
  }

  public int GetIsNullable(boolean[] isNullable) {
    return fgbd4jJNI.FieldDef_GetIsNullable(swigCPtr, this, isNullable);
  }

  public int SetIsNullable(boolean isNullable) {
    return fgbd4jJNI.FieldDef_SetIsNullable(swigCPtr, this, isNullable);
  }

  public int GetGeometryDef(GeometryDef geometryDef) {
    return fgbd4jJNI.FieldDef_GetGeometryDef(swigCPtr, this, GeometryDef.getCPtr(geometryDef), geometryDef);
  }

  public int SetGeometryDef(GeometryDef geometryDef) {
    return fgbd4jJNI.FieldDef_SetGeometryDef(swigCPtr, this, GeometryDef.getCPtr(geometryDef), geometryDef);
  }

}
