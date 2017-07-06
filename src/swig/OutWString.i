/* Define the types to use in the generated JNI C code and Java code */
%typemap(jni) std::wstring&  "jobject"
%typemap(jtype) std::wstring&  "StringBuffer"
%typemap(jstype) std::wstring&  "StringBuffer"

/* How to convert Java(JNI) type to requested C type */
%typemap(in) std::wstring& {

  $1 = NULL;
  if($input != NULL) {
    /* Get the String from the StringBuffer */
    jmethodID setLengthID;
    jclass sbufClass = jenv->GetObjectClass($input);
    jmethodID toStringID = jenv->GetMethodID(sbufClass, "toString", "()Ljava/lang/String;");
    jstring js = (jstring) jenv->CallObjectMethod($input, toStringID);

    /* Convert the String to a C string */
    const jchar *$1_pstr = jenv->GetStringChars(js, 0);
    jint $1_len = jenv->GetStringLength(js);
    std::wstring $1_str;
    if ($1_len) {
      $1_str.reserve($1_len);
      for (jsize i = 0; i < $1_len; ++i) {
        $1_str.push_back((wchar_t)$1_pstr[i]);
      }
    }

    $1 = &$1_str;
    /* Release the string we obtained with GetStringChars */
    jenv->ReleaseStringChars(js, $1_pstr);
    
    /* Zero the original StringBuffer, so we can replace it with the result */
    setLengthID = jenv->GetMethodID(sbufClass, "setLength", "(I)V");
    jenv->CallVoidMethod($input, setLengthID, (jint) 0);
  }
}

/* How to convert the C type to the Java(JNI) type */
%typemap(argout) std::wstring&  {

  if($1 != NULL) {
    /* Append the result to the empty StringBuffer */
    jstring newString = jenv->NewString((const jchar *)$1->data(),$1->length());
    jclass sbufClass = jenv->GetObjectClass($input);
    jmethodID appendStringID = jenv->GetMethodID(sbufClass, "append", "(Ljava/lang/String;)Ljava/lang/StringBuffer;");
    jenv->CallObjectMethod($input, appendStringID, newString);
  }  
}
%typemap(argout) const std::wstring&  {
}

/* Prevent the default freearg typemap from being used */
%typemap(freearg) std::wstring&  ""

/* Convert the jstype to jtype typemap type */
%typemap(javain) std::wstring&  "$javainput"

