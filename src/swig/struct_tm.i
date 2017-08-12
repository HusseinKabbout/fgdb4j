/* Define the types to use in the generated JNI C code and Java code */

/* INPUT */

%typemap(jni) const tm&   "jlong"
%typemap(jtype) const tm&  "long"
%typemap(jstype) const tm&  "long"

/* How to convert Java(JNI) type to requested C type */
%typemap(in) const tm&($*1_ltype temp) {
    const time_t time = (time_t)$input;
  //  struct tm* tm_time_p=localtime(&time);
  //if (tm_time_p ==NULL) {
  if (localtime_s(&temp,&time)) {
    SWIG_JavaThrowException(jenv,SWIG_JavaIllegalArgumentException, "Invalid time_t");
  }
  //  $1 = (struct tm*)alloca(sizeof(struct tm));
  //  memcpy($1,tm_time_p,sizeof(struct tm));
  $1 = &temp; 
}

/* How to convert the C type to the Java(JNI) type */
%typemap(argout) const tm&  {
}
/* Prevent the default freearg typemap from being used */
%typemap(freearg) const tm&  {
}

/* Convert the jstype to jtype typemap type */
%typemap(javain) const tm&  "$javainput"

/* OUTPUT */

%typemap(jni) tm&   "jlongArray"
%typemap(jtype) tm&  "long[]"
%typemap(jstype) tm&  "long[]"

/* How to convert Java(JNI) type to requested C type */
%typemap(in) tm&($*1_ltype temp) {
  if (!$input) {
    SWIG_JavaThrowException(jenv, SWIG_JavaNullPointerException, "array null");
    return $null;
  }
  if (JCALL1(GetArrayLength, jenv, $input) == 0) {
    SWIG_JavaThrowException(jenv, SWIG_JavaIndexOutOfBoundsException, "Array must contain at least 1 element");
    return $null;
  }
  $1 = &temp; 
}

/* How to convert the C type to the Java(JNI) type */
%typemap(argout) tm&  {
  jlong jvalue = (jint)mktime(&temp$argnum);
  JCALL4(SetLongArrayRegion, jenv, $input, 0, 1, &jvalue);
}
/* Prevent the default freearg typemap from being used */
%typemap(freearg) tm&  {
}

/* Convert the jstype to jtype typemap type */
%typemap(javain) tm&  "$javainput"


