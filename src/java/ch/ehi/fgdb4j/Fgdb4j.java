package ch.ehi.fgdb4j;

import java.io.*;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import ch.ehi.fgdb4j.impl.LibProxyImpl;

public class Fgdb4j {
	public static final String HARDLIB_PROPERTY_WRAPPER = "ch.ehi.fgdb4j.wrapper";

	public static final String HARDLIB_PROPERTY_ESRIAPI = "ch.ehi.fgdb4j.esriapi";

	private static final String NATIVETMPFILE_PREFIX = "fgdb4j-";
	public static final int FGDB_E_TABLE_NOT_FOUND=-2147220655;

	private Fgdb4j(){};


	    private static File extractFolder = null;
		private static int refc=0;

	    public static void initialize() throws Fgdb4jException {
			refc++;
			if(refc==1){
		        loadNativeLibraries();
			}
	    }
	    public static void close() throws Fgdb4jException {
			refc--;
			if(refc==0){
			}
	    }

	    public static void deleteFileGdb(File fgdbFile) {
	    	if(fgdbFile!=null && fgdbFile.exists()){
	    		if(fgdbFile.isDirectory()){
			        File[] filesInFolder = fgdbFile.listFiles();
			        if(filesInFolder != null) {
			            for(File file : filesInFolder) {
			                if(file.exists()) {
		                        file.delete();
			                }
			            }
			        }
	    			
	    		}
	    		fgdbFile.delete();
	    	}
	    }
	    public static void cleanup() {
	        if(extractFolder!=null && extractFolder.exists()){

		        File[] nativeLibFiles = extractFolder.listFiles();
		        if(nativeLibFiles != null) {
		            for(File nativeLibFile : nativeLibFiles) {
		                if(nativeLibFile.exists()) {
		                    try {
		                        boolean ret=nativeLibFile.delete();
		                    }
		                    catch(SecurityException e) {
		                        System.err.println("Failed to delete native lib" + e.getMessage());
		                    }
		                }
		            }
		        }
                try {
    	        	boolean ret=extractFolder.delete();
                }
                catch(SecurityException e) {
                    System.err.println("Failed to delete native lib folder" + e.getMessage());
                }
	        }
	    }
	    /**
	     * Loads native library using given path and name of the library.
	     *
	     * @throws
	     */
	    private static void loadNativeLibraries() throws Fgdb4jException {
	        if(extractFolder!=null) {
	            return;
	        }
	        // temporary library folder
	        extractFolder=getExtractDir().getAbsoluteFile();
            if(!extractFolder.exists()) {
                extractFolder.mkdirs();
            }
            // Delete the extracted folder file on JVM exit.
            extractFolder.deleteOnExit();

	        final String[] file=new String[]{"FileGDBAPI.dll","fgdb4j.dll"}; 
	        final String[] hardlibPropertyName=new String[]{HARDLIB_PROPERTY_ESRIAPI,HARDLIB_PROPERTY_WRAPPER}; 
	        for(int filei=0;filei<file.length;filei++){
		        String hardlibPath = System.getProperty(hardlibPropertyName[filei]);

		        if(hardlibPath != null) {
		            loadNativeLibrary(new File(hardlibPath));
		        }else{
		        	String nativeLibraryName=file[filei];
			        // Load the os-dependent library from the jar file
			        String packagePath = Fgdb4j.class.getPackage().getName().replaceAll("\\.", "/");
			        String nativeLibraryPath = String.format("/%s/native/%s", packagePath, getNativeLibFolderPathForCurrentOS());
			        boolean hasNativeLib = hasResource(nativeLibraryPath + "/" + nativeLibraryName);

			        if(!hasNativeLib) {
			            extractFolder = null;
			            throw new Fgdb4jException(String.format("native library not found: "+ nativeLibraryPath + "/" + nativeLibraryName));
			        }

			        // Try extracting the library from jar
			        extractAndLoadLibraryFile(nativeLibraryPath, nativeLibraryName, extractFolder);
		        }
	        }
	    }


	    private static void loadNativeLibrary(File file) throws Fgdb4jException {
		    if(!file.exists()) {
		        throw new Fgdb4jException("no native library " + file.getAbsolutePath());
		    }
		    try {
		        System.load(file.getAbsolutePath());
		    }
		    catch(UnsatisfiedLinkError e) {
		        throw new Fgdb4jException("Failed to load native library " + file.getAbsolutePath(),e);
		    }
		    /*
	        Class ca=null;
	        LibProxy a=null;
			try {
				URLClassLoader x=new URLClassLoader(((URLClassLoader)Fgdb4j.class.getClassLoader()).getURLs());
				ca = x.loadClass("ch.ehi.fgdb4j.impl.LibProxyImpl");
		        a = (LibProxy)ca.newInstance();
			} catch (ClassNotFoundException e) {
				throw new Fgdb4jException(e);
			} catch (InstantiationException e) {
				throw new Fgdb4jException(e);
			} catch (IllegalAccessException e) {
				throw new Fgdb4jException(e);
			}
			a.loadNativeLibrary(file);
	        loadedLibs.add(a);
	        */
		}

		/**
	     * Extracts and loads the specified library file to the target folder
	     *
	     * @param libFolderForCurrentOS Library path.
	     * @param libraryFileName       Library name.
	     * @param targetFolder          Target folder.
	     * @return
	     */
	    private static void extractAndLoadLibraryFile(String libFolderForCurrentOS, String libraryFileName,
	                                                     File targetFolder) throws Fgdb4jException {
	        String nativeLibraryFilePath = libFolderForCurrentOS + "/" + libraryFileName;
            
	        String extractedLibFileName = libraryFileName;

	        File extractedLibFile = new File(targetFolder, extractedLibFileName);

	        try {
	        	if(!extractedLibFile.exists()){
		            // Extract a native library file into the target directory
		            InputStream reader = Fgdb4j.class.getResourceAsStream(nativeLibraryFilePath);
		            FileOutputStream writer = new FileOutputStream(extractedLibFile);
		            try {
		                byte[] buffer = new byte[8192];
		                int bytesRead = 0;
		                while((bytesRead = reader.read(buffer)) != -1) {
		                    writer.write(buffer, 0, bytesRead);
		                }
		            }
		            finally {
		                // Delete the extracted lib file on JVM exit.
		                extractedLibFile.deleteOnExit();


		                if(writer != null) {
		                    writer.close();
		                }
		                if(reader != null) {
		                    reader.close();
		                }
		            }
		            // Set executable (x) flag to enable Java to load the native library
		            extractedLibFile.setReadable(true);
		            extractedLibFile.setWritable(true, true);
		            extractedLibFile.setExecutable(true);
	        		
	        	}


	            // Check whether the contents are properly copied from the resource folder
	            {
	                InputStream nativeIn = Fgdb4j.class.getResourceAsStream(nativeLibraryFilePath);
	                InputStream extractedLibIn = new FileInputStream(extractedLibFile);
	                try {
	                    if(!contentsEquals(nativeIn, extractedLibIn)) {
	                        throw new IOException("extracted file content different");
	                    }
	                }
	                finally {
	                    if(nativeIn != null) {
	                        nativeIn.close();
	                    }
	                    if(extractedLibIn != null) {
	                        extractedLibIn.close();
	                    }
	                }
	            }
	        }
	        catch(IOException e) {
                throw new Fgdb4jException("Failed to write a native library file at "+extractedLibFile);
	        }
            loadNativeLibrary(new File(targetFolder, extractedLibFileName));
	    }
	    private static boolean contentsEquals(InputStream in1, InputStream in2) throws IOException {
	        if(!(in1 instanceof BufferedInputStream)) {
	            in1 = new BufferedInputStream(in1);
	        }
	        if(!(in2 instanceof BufferedInputStream)) {
	            in2 = new BufferedInputStream(in2);
	        }

	        int ch = in1.read();
	        while(ch != -1) {
	            int ch2 = in2.read();
	            if(ch != ch2) {
	                return false;
	            }
	            ch = in1.read();
	        }
	        int ch2 = in2.read();
	        return ch2 == -1;
	    }

	    private static File getExtractDir() {
	        String tempFolder = new File(System.getProperty("java.io.tmpdir")).getAbsolutePath();
	        return new File(tempFolder, NATIVETMPFILE_PREFIX+getArchName()+"-"+getVersion());

	    }


	    private static String getArchName() {
	    	String osArch = System.getProperty("os.arch");
            String lc = osArch.toLowerCase(Locale.US);
            if(archMapping.containsKey(lc)){
                return archMapping.get(lc);
            }
	        return translateArchNameToFolderName(osArch);
	    }
	    private static HashMap<String, String> archMapping = new HashMap<String, String>();

	    public static final String X86 = "x86";
	    public static final String X86_64 = "x86_64";
	    public static final String IA64_32 = "ia64_32";
	    public static final String IA64 = "ia64";
	    public static final String PPC = "ppc";
	    public static final String PPC64 = "ppc64";

	    static {
	        // x86 mappings
	        archMapping.put(X86, X86);
	        archMapping.put("i386", X86);
	        archMapping.put("i486", X86);
	        archMapping.put("i586", X86);
	        archMapping.put("i686", X86);
	        archMapping.put("pentium", X86);

	        // x86_64 mappings
	        archMapping.put(X86_64, X86_64);
	        archMapping.put("amd64", X86_64);
	        archMapping.put("em64t", X86_64);
	        archMapping.put("universal", X86_64); // Needed for openjdk7 in Mac

	        // Itenium 64-bit mappings
	        archMapping.put(IA64, IA64);
	        archMapping.put("ia64w", IA64);

	        // Itenium 32-bit mappings, usually an HP-UX construct
	        archMapping.put(IA64_32, IA64_32);
	        archMapping.put("ia64n", IA64_32);

	        // PowerPC mappings
	        archMapping.put(PPC, PPC);
	        archMapping.put("power", PPC);
	        archMapping.put("powerpc", PPC);
	        archMapping.put("power_pc", PPC);
	        archMapping.put("power_rs", PPC);

	        // PowerPC 64bit mappings
	        archMapping.put(PPC64, PPC64);
	        archMapping.put("power64", PPC64);
	        archMapping.put("powerpc64", PPC64);
	        archMapping.put("power_pc64", PPC64);
	        archMapping.put("power_rs64", PPC64);
	    }


		private static String getOSName() {
			return translateOSNameToFolderName(System.getProperty("os.name"));
		}
	    static String translateOSNameToFolderName(String osName) {
	        if (osName.contains("Windows")) {
	            return "Windows";
	        }
	        else if (osName.contains("Mac") || osName.contains("Darwin")) {
	            return "Mac";
	        }
	        else if (osName.contains("Linux")) {
	            return "Linux";
	        }
	        else if (osName.contains("AIX")) {
	            return "AIX";
	        }

	        else {
	            return osName.replaceAll("\\W", "");
	        }
	    }

	    static String translateArchNameToFolderName(String archName) {
	        return archName.replaceAll("\\W", "");
	    }
		private static String getNativeLibFolderPathForCurrentOS() {
			return getOSName() + "/" + getArchName();
		}

		private static boolean hasResource(String path) {
	        return Fgdb4j.class.getResource(path) != null;
	    }


	    /**
	     * @return The major version of the library.
	     */
	    public static int getMajorVersion() {
	        String[] c = getVersion().split("\\.");
	        return (c.length > 0) ? Integer.parseInt(c[0]) : 1;
	    }

	    /**
	     * @return The minor version of the library.
	     */
	    public static int getMinorVersion() {
	        String[] c = getVersion().split("\\.");
	        return (c.length > 1) ? Integer.parseInt(c[1]) : 0;
	    }


		private static String version=null;
	    /**
	     * @return The version of the library.
	     */
		public static String getVersion() {
			  if(version==null){
			java.util.ResourceBundle resVersion = java.util.ResourceBundle.getBundle("ch/ehi/fgdb4j/Version");
				StringBuffer ret=new StringBuffer(20);
				String majorVersion=resVersion.getString("versionMajor");
			ret.append(majorVersion);
				ret.append('.');
			ret.append(resVersion.getString("versionMinor"));
				ret.append('.');
			ret.append(resVersion.getString("versionMicro"));
			ret.append('-');
			if(resVersion.containsKey("versionBranch")){
	            String branch=resVersion.getString("versionBranch");
	            if(branch!=null){
	            	branch=branch.trim();
	            	if(branch.length()>0){
	 	               ret.append(branch);
		               ret.append('-');
	            	}
	            }
			}
			ret.append(resVersion.getString("versionDate"));
				version=ret.toString();
			  }
			  return version;
		}
}
