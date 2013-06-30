package mp3;

import java.io.File;
import java.util.List;

public class RemoveOriginals {

	public static void remove(String path) {
		List<File> jpgFiles = FileListing.ls(path, "jpg");
		for (File jpgFile : jpgFiles) {
			jpgFile.delete();
		}

		List<File> mp3Files = FileListing.ls(path, "mp3");
		for (File mp3File : mp3Files) {
			if (mp3File.getName().contains("original")) {
				mp3File.delete();
			}
		}
	}

}
