/* Define the types to use in the generated JNI C code and Java code */

%typemap(jni) const tm&   "jlong"
%typemap(jtype) const tm&  "long"
%typemap(jstype) const tm&  "long"

/* How to convert Java(JNI) type to requested C type */
%typemap(in) const tm& {
    const time_t time = (time_t)$input;
    struct tm* tm_time_p=localtime(&time);
  if (tm_time_p ==NULL) {
    SWIG_JavaThrowException(jenv,SWIG_JavaIllegalArgumentException, "Invalid time_t");
  }
    $1 = (struct tm*)alloca(sizeof(struct tm));
    memcpy($1,tm_time_p,sizeof(struct tm));
}

/* How to convert the C type to the Java(JNI) type */
%typemap(argout) const tm&  {
}
/* Prevent the default freearg typemap from being used */
%typemap(freearg) const tm&  {
}

/* Convert the jstype to jtype typemap type */
%typemap(javain) const tm&  "$javainput"

