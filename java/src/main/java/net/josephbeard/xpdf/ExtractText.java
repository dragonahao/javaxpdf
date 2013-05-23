package net.josephbeard.xpdf;

import java.io.File;

import net.josephbeard.xpdf.GlobalParameters.EndOfLine;

public class ExtractText {

	public static void main(String[] args) throws InstantiationException {
		File libraryFile = new File(args[0]);
		System.load(libraryFile.getAbsolutePath());
		// System.loadLibrary("xpdf-jni");

		GlobalParameters parameters = GlobalParameters.getInstance();
		parameters.setBreakBetweenPages(true);
		parameters.setTextEncoding("UTF-8");
		parameters.setEndOfLine(EndOfLine.UNIX);
		parameters.setErrorOutputSuppressed(false);

		for (int i = 1; i < args.length; i++) {
			String fileName = args[i];
			File file = new File(fileName);
			if (!file.exists()) {
				System.err.println(fileName + " does not exist!");
				continue;
			}

			PDFDocument document = PDFDocument.createInstance(file);

			for (PDFPage page : document.getPages()) {
				System.out.println(page.getText());
			}
		}
	}

}
