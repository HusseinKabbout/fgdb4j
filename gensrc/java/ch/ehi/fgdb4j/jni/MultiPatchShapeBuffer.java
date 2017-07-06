/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package ch.ehi.fgdb4j.jni;

public class MultiPatchShapeBuffer extends ShapeBuffer {
  private transient long swigCPtr;

  protected MultiPatchShapeBuffer(long cPtr, boolean cMemoryOwn) {
    super(fgbd4jJNI.MultiPatchShapeBuffer_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(MultiPatchShapeBuffer obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        fgbd4jJNI.delete_MultiPatchShapeBuffer(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public int GetExtent(SWIGTYPE_p_p_double extent) {
    return fgbd4jJNI.MultiPatchShapeBuffer_GetExtent(swigCPtr, this, SWIGTYPE_p_p_double.getCPtr(extent));
  }

  public int GetNumParts(int[] numParts) {
    return fgbd4jJNI.MultiPatchShapeBuffer_GetNumParts(swigCPtr, this, numParts);
  }

  public int GetNumPoints(int[] numPoints) {
    return fgbd4jJNI.MultiPatchShapeBuffer_GetNumPoints(swigCPtr, this, numPoints);
  }

  public int GetParts(SWIGTYPE_p_p_int parts) {
    return fgbd4jJNI.MultiPatchShapeBuffer_GetParts(swigCPtr, this, SWIGTYPE_p_p_int.getCPtr(parts));
  }

  public int GetPartDescriptors(SWIGTYPE_p_p_int partDescriptorArray) {
    return fgbd4jJNI.MultiPatchShapeBuffer_GetPartDescriptors(swigCPtr, this, SWIGTYPE_p_p_int.getCPtr(partDescriptorArray));
  }

  public int GetPoints(SWIGTYPE_p_p_FileGDBAPI__Point points) {
    return fgbd4jJNI.MultiPatchShapeBuffer_GetPoints(swigCPtr, this, SWIGTYPE_p_p_FileGDBAPI__Point.getCPtr(points));
  }

  public int GetZExtent(SWIGTYPE_p_p_double zExtent) {
    return fgbd4jJNI.MultiPatchShapeBuffer_GetZExtent(swigCPtr, this, SWIGTYPE_p_p_double.getCPtr(zExtent));
  }

  public int GetZs(SWIGTYPE_p_p_double zArray) {
    return fgbd4jJNI.MultiPatchShapeBuffer_GetZs(swigCPtr, this, SWIGTYPE_p_p_double.getCPtr(zArray));
  }

  public int GetMExtent(SWIGTYPE_p_p_double mExtent) {
    return fgbd4jJNI.MultiPatchShapeBuffer_GetMExtent(swigCPtr, this, SWIGTYPE_p_p_double.getCPtr(mExtent));
  }

  public int GetMs(SWIGTYPE_p_p_double mArray) {
    return fgbd4jJNI.MultiPatchShapeBuffer_GetMs(swigCPtr, this, SWIGTYPE_p_p_double.getCPtr(mArray));
  }

  public int GetIDs(SWIGTYPE_p_p_int ids) {
    return fgbd4jJNI.MultiPatchShapeBuffer_GetIDs(swigCPtr, this, SWIGTYPE_p_p_int.getCPtr(ids));
  }

  public int GetNormals(SWIGTYPE_p_p_float normals) {
    return fgbd4jJNI.MultiPatchShapeBuffer_GetNormals(swigCPtr, this, SWIGTYPE_p_p_float.getCPtr(normals));
  }

  public int GetTextures(int[] numTextures, int[] textureDimension, SWIGTYPE_p_p_int textureParts, SWIGTYPE_p_p_float textureCoords) {
    return fgbd4jJNI.MultiPatchShapeBuffer_GetTextures(swigCPtr, this, numTextures, textureDimension, SWIGTYPE_p_p_int.getCPtr(textureParts), SWIGTYPE_p_p_float.getCPtr(textureCoords));
  }

  public int GetMaterials(int[] numMaterials, int[] compressionType, SWIGTYPE_p_p_int materialParts, SWIGTYPE_p_p_unsigned_char materials) {
    return fgbd4jJNI.MultiPatchShapeBuffer_GetMaterials(swigCPtr, this, numMaterials, compressionType, SWIGTYPE_p_p_int.getCPtr(materialParts), SWIGTYPE_p_p_unsigned_char.getCPtr(materials));
  }

  public int Setup(ShapeType shapeType, int numParts, int numPoints, int numTextures, int textureDimension, int numMaterials, long materialsLength) {
    return fgbd4jJNI.MultiPatchShapeBuffer_Setup__SWIG_0(swigCPtr, this, shapeType.swigValue(), numParts, numPoints, numTextures, textureDimension, numMaterials, materialsLength);
  }

  public int Setup(ShapeType shapeType, int numParts, int numPoints, int numTextures, int textureDimension, int numMaterials) {
    return fgbd4jJNI.MultiPatchShapeBuffer_Setup__SWIG_1(swigCPtr, this, shapeType.swigValue(), numParts, numPoints, numTextures, textureDimension, numMaterials);
  }

  public int Setup(ShapeType shapeType, int numParts, int numPoints, int numTextures, int textureDimension) {
    return fgbd4jJNI.MultiPatchShapeBuffer_Setup__SWIG_2(swigCPtr, this, shapeType.swigValue(), numParts, numPoints, numTextures, textureDimension);
  }

  public int Setup(ShapeType shapeType, int numParts, int numPoints, int numTextures) {
    return fgbd4jJNI.MultiPatchShapeBuffer_Setup__SWIG_3(swigCPtr, this, shapeType.swigValue(), numParts, numPoints, numTextures);
  }

  public int Setup(ShapeType shapeType, int numParts, int numPoints) {
    return fgbd4jJNI.MultiPatchShapeBuffer_Setup__SWIG_4(swigCPtr, this, shapeType.swigValue(), numParts, numPoints);
  }

  public int CalculateExtent() {
    return fgbd4jJNI.MultiPatchShapeBuffer_CalculateExtent(swigCPtr, this);
  }

  public MultiPatchShapeBuffer() {
    this(fgbd4jJNI.new_MultiPatchShapeBuffer(), true);
  }

}
