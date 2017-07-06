package ch.ehi.fgdb4j.impl;

import java.io.File;

import ch.ehi.fgdb4j.Fgdb4jException;
import ch.ehi.fgdb4j.LibProxy;

public class LibProxyImpl implements LibProxy {

	@Override
	public synchronized void loadNativeLibrary(File libPath) throws Fgdb4jException {
	    if(!libPath.exists()) {
	        throw new Fgdb4jException("no native library " + libPath.getAbsolutePath());
	    }
	    try {
	        System.load(libPath.getAbsolutePath());
	    }
	    catch(UnsatisfiedLinkError e) {
	        throw new Fgdb4jException("Failed to load native library " + libPath.getAbsolutePath(),e);
	    }
	}

}
