/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package ch.ehi.fgdb4j.jni;

public class Point {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected Point(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(Point obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        fgbd4jJNI.delete_Point(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setX(double value) {
    fgbd4jJNI.Point_x_set(swigCPtr, this, value);
  }

  public double getX() {
    return fgbd4jJNI.Point_x_get(swigCPtr, this);
  }

  public void setY(double value) {
    fgbd4jJNI.Point_y_set(swigCPtr, this, value);
  }

  public double getY() {
    return fgbd4jJNI.Point_y_get(swigCPtr, this);
  }

  public Point() {
    this(fgbd4jJNI.new_Point(), true);
  }

}
