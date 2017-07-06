/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package ch.ehi.fgdb4j.jni;

public class Curve {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected Curve(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(Curve obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        fgbd4jJNI.delete_Curve(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setStartPointIndex(int value) {
    fgbd4jJNI.Curve_startPointIndex_set(swigCPtr, this, value);
  }

  public int getStartPointIndex() {
    return fgbd4jJNI.Curve_startPointIndex_get(swigCPtr, this);
  }

  public void setCurveType(int value) {
    fgbd4jJNI.Curve_curveType_set(swigCPtr, this, value);
  }

  public int getCurveType() {
    return fgbd4jJNI.Curve_curveType_get(swigCPtr, this);
  }

  public int GetCurveType(int[] curvetype) {
    return fgbd4jJNI.Curve_GetCurveType(swigCPtr, this, curvetype);
  }

}
