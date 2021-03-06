/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package ch.ehi.fgdb4j.jni;

public class EllipticArcCurve extends Curve {
  private transient long swigCPtr;

  protected EllipticArcCurve(long cPtr, boolean cMemoryOwn) {
    super(fgbd4jJNI.EllipticArcCurve_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(EllipticArcCurve obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        fgbd4jJNI.delete_EllipticArcCurve(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public void setCenterPoint(Point value) {
    fgbd4jJNI.EllipticArcCurve_centerPoint_set(swigCPtr, this, Point.getCPtr(value), value);
  }

  public Point getCenterPoint() {
    long cPtr = fgbd4jJNI.EllipticArcCurve_centerPoint_get(swigCPtr, this);
    return (cPtr == 0) ? null : new Point(cPtr, false);
  }

  public void setVs(SWIGTYPE_p_double value) {
    fgbd4jJNI.EllipticArcCurve_vs_set(swigCPtr, this, SWIGTYPE_p_double.getCPtr(value));
  }

  public SWIGTYPE_p_double getVs() {
    long cPtr = fgbd4jJNI.EllipticArcCurve_vs_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_double(cPtr, false);
  }

  public void setRotation(double value) {
    fgbd4jJNI.EllipticArcCurve_rotation_set(swigCPtr, this, value);
  }

  public double getRotation() {
    return fgbd4jJNI.EllipticArcCurve_rotation_get(swigCPtr, this);
  }

  public void setFromV(double value) {
    fgbd4jJNI.EllipticArcCurve_fromV_set(swigCPtr, this, value);
  }

  public double getFromV() {
    return fgbd4jJNI.EllipticArcCurve_fromV_get(swigCPtr, this);
  }

  public void setSemiMajor(double value) {
    fgbd4jJNI.EllipticArcCurve_semiMajor_set(swigCPtr, this, value);
  }

  public double getSemiMajor() {
    return fgbd4jJNI.EllipticArcCurve_semiMajor_get(swigCPtr, this);
  }

  public void setMinorMajorRatio(double value) {
    fgbd4jJNI.EllipticArcCurve_minorMajorRatio_set(swigCPtr, this, value);
  }

  public double getMinorMajorRatio() {
    return fgbd4jJNI.EllipticArcCurve_minorMajorRatio_get(swigCPtr, this);
  }

  public void setDeltaV(double value) {
    fgbd4jJNI.EllipticArcCurve_deltaV_set(swigCPtr, this, value);
  }

  public double getDeltaV() {
    return fgbd4jJNI.EllipticArcCurve_deltaV_get(swigCPtr, this);
  }

  public void setBits(int value) {
    fgbd4jJNI.EllipticArcCurve_bits_set(swigCPtr, this, value);
  }

  public int getBits() {
    return fgbd4jJNI.EllipticArcCurve_bits_get(swigCPtr, this);
  }

}
