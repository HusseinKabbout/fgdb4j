/* Define the types to use in the generated JNI C code and Java code */

%typemap(jni) const tm&   "jlong"
%typemap(jtype) const tm&  "long"
%typemap(jstype) const tm&  "long"

/* How to convert Java(JNI) type to requested C type */
%typemap(in) const tm& {

  $1 = NULL;
  if($input != NULL) {
    /* Get the String from the StringBuffer */
    jmethodID setLengthID;
    jclass sbufClass = jenv->GetObjectClass($input);
    jmethodID toStringID = jenv->GetMethodID(sbufClass, "toString", "()Ljava/lang/String;");
    jstring js = (jstring) jenv->CallObjectMethod($input, toStringID);

    /* Convert the String to a C string */
    const char *pCharStr = jenv->GetStringUTFChars(js, 0);

    /* Take a copy of the C string as the typemap is for a non const C string */
    //jmethodID capacityID = jenv->GetMethodID(jenv, sbufClass, "capacity", "()I");
    //jint capacity = jenv->CallIntMethod(jenv, $input, capacityID);
    std::string $1_str(pCharStr);
    $1=&$1_str;

    /* Release the UTF string we obtained with GetStringUTFChars */
    jenv->ReleaseStringUTFChars(js, pCharStr);

    /* Zero the original StringBuffer, so we can replace it with the result */
    setLengthID = jenv->GetMethodID(sbufClass, "setLength", "(I)V");
    jenv->CallVoidMethod($input, setLengthID, (jint) 0);
  }
}

/* How to convert the C type to the Java(JNI) type */
%typemap(argout) const tm&  {

  if($1 != NULL) {
    /* Append the result to the empty StringBuffer */
    jstring newString = jenv->NewStringUTF($1->data());
    jclass sbufClass = jenv->GetObjectClass($input);
    jmethodID appendStringID = jenv->GetMethodID(sbufClass, "append", "(Ljava/lang/String;)Ljava/lang/StringBuffer;");
    jenv->CallObjectMethod($input, appendStringID, newString);

    /* Clean up the string object, no longer needed */
    $1 = NULL;
  }  
}
/* Prevent the default freearg typemap from being used */
%typemap(freearg) const tm&  ""

/* Convert the jstype to jtype typemap type */
%typemap(javain) const tm&  "$javainput"

