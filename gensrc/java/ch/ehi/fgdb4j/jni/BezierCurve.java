/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package ch.ehi.fgdb4j.jni;

public class BezierCurve extends Curve {
  private transient long swigCPtr;

  protected BezierCurve(long cPtr, boolean cMemoryOwn) {
    super(fgbd4jJNI.BezierCurve_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(BezierCurve obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        fgbd4jJNI.delete_BezierCurve(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public void setControlPoints(Point value) {
    fgbd4jJNI.BezierCurve_controlPoints_set(swigCPtr, this, Point.getCPtr(value), value);
  }

  public Point getControlPoints() {
    long cPtr = fgbd4jJNI.BezierCurve_controlPoints_get(swigCPtr, this);
    return (cPtr == 0) ? null : new Point(cPtr, false);
  }

}
