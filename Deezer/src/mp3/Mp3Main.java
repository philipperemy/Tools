package mp3;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.AbstractID3v2;
import org.farng.mp3.id3.ID3v2_2;

public class Mp3Main {

	public static void main(String[] args) throws IOException, TagException {

		String folder = "mp3";

		List<File> allFiles = FileListing.ls(folder, "mp3");

		for (File mp3file : allFiles) {
			System.out.println("name = " + mp3file.getName());
			MP3File mp3fileWrapper = new MP3File(mp3file);
			if (mp3fileWrapper.hasID3v1Tag()) {
				mp3fileWrapper.getID3v1Tag().setAlbum("my ass album2");
				mp3fileWrapper.getID3v1Tag().setYear("1993");
				mp3fileWrapper.save();
			} else {
				System.out.println("has no ID3V1 tag");
			}

			if (mp3fileWrapper.hasID3v2Tag()) {
				AbstractID3v2 id3v2 = mp3fileWrapper.getID3v2Tag();
				id3v2 = new ID3v2_2();
				mp3fileWrapper.setID3v2Tag(id3v2);
				mp3fileWrapper.save();
			}

		}

		RemoveOriginals.remove(folder);
	}

}
