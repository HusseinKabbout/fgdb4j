package ch.ehi.fgdb4j;

import java.io.File;

public interface LibProxy {

	/**
	 * Loads native library using the given path and name of the library.
	 *
	 * @param path Path of the native library.
	 * @param name Name  of the native library.
	 */
	public void loadNativeLibrary(File libPath) throws Fgdb4jException;

}
