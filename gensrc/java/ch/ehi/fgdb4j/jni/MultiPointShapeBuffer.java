/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package ch.ehi.fgdb4j.jni;

public class MultiPointShapeBuffer extends ShapeBuffer {
  private transient long swigCPtr;

  protected MultiPointShapeBuffer(long cPtr, boolean cMemoryOwn) {
    super(fgbd4jJNI.MultiPointShapeBuffer_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(MultiPointShapeBuffer obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        fgbd4jJNI.delete_MultiPointShapeBuffer(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public int GetExtent(SWIGTYPE_p_p_double extent) {
    return fgbd4jJNI.MultiPointShapeBuffer_GetExtent(swigCPtr, this, SWIGTYPE_p_p_double.getCPtr(extent));
  }

  public int GetNumPoints(int[] numPoints) {
    return fgbd4jJNI.MultiPointShapeBuffer_GetNumPoints(swigCPtr, this, numPoints);
  }

  public int GetPoints(SWIGTYPE_p_p_FileGDBAPI__Point points) {
    return fgbd4jJNI.MultiPointShapeBuffer_GetPoints(swigCPtr, this, SWIGTYPE_p_p_FileGDBAPI__Point.getCPtr(points));
  }

  public int GetZExtent(SWIGTYPE_p_p_double zExtent) {
    return fgbd4jJNI.MultiPointShapeBuffer_GetZExtent(swigCPtr, this, SWIGTYPE_p_p_double.getCPtr(zExtent));
  }

  public int GetZs(SWIGTYPE_p_p_double zArray) {
    return fgbd4jJNI.MultiPointShapeBuffer_GetZs(swigCPtr, this, SWIGTYPE_p_p_double.getCPtr(zArray));
  }

  public int GetMExtent(SWIGTYPE_p_p_double mExtent) {
    return fgbd4jJNI.MultiPointShapeBuffer_GetMExtent(swigCPtr, this, SWIGTYPE_p_p_double.getCPtr(mExtent));
  }

  public int GetMs(SWIGTYPE_p_p_double mArray) {
    return fgbd4jJNI.MultiPointShapeBuffer_GetMs(swigCPtr, this, SWIGTYPE_p_p_double.getCPtr(mArray));
  }

  public int GetIDs(SWIGTYPE_p_p_int ids) {
    return fgbd4jJNI.MultiPointShapeBuffer_GetIDs(swigCPtr, this, SWIGTYPE_p_p_int.getCPtr(ids));
  }

  public int Setup(ShapeType shapeType, int numPoints) {
    return fgbd4jJNI.MultiPointShapeBuffer_Setup(swigCPtr, this, shapeType.swigValue(), numPoints);
  }

  public int CalculateExtent() {
    return fgbd4jJNI.MultiPointShapeBuffer_CalculateExtent(swigCPtr, this);
  }

  public MultiPointShapeBuffer() {
    this(fgbd4jJNI.new_MultiPointShapeBuffer(), true);
  }

}