package main;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import mp3.FileListing;
import mp3.RemoveOriginals;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.AbstractID3v2;
import org.farng.mp3.id3.ID3v2_2;
import org.xml.sax.SAXException;

import deezer.SongEntity;
import deezer.SongList;
import deezer.XMLParser;

public class Main {

	public static void main(String[] args) throws IOException, TagException {

		XMLParser xmlParser = new XMLParser();
		SongList songList = new SongList();
		try {
			xmlParser.parse("C:\\Users\\Philippe Rémy\\Desktop\\musics.xml",
					songList);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		songList.print();

		String folder = "mp3";
		String newFolder = "mp4";

		List<File> mp3Files = FileListing.ls(folder, "mp3");

		Map<File, String> renameMap = new HashMap<>();

		for (int i = 0; i < mp3Files.size(); i++) {

			File mp3file = mp3Files.get(i);
			SongEntity songEntity = songList.get(i);

			renameMap.put(mp3file, newFolder + "\\" + songEntity.getSongTitle()
					+ ".mp3");

			System.out.println("old = " + mp3file.getName() + " new = "
					+ renameMap.get(mp3file));
			MP3File mp3fileWrapper = new MP3File(mp3file);
			if (mp3fileWrapper.hasID3v1Tag()) {
				mp3fileWrapper.getID3v1Tag().setAlbum(songEntity.getAlbum());
				mp3fileWrapper.getID3v1Tag().setArtist(songEntity.getAuthor());
				mp3fileWrapper.getID3v1Tag().setSongTitle(
						songEntity.getSongTitle());
				mp3fileWrapper.getID3v1Tag()
						.setTitle(songEntity.getSongTitle());
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

		for (File mp3File : mp3Files) {
			mp3File.renameTo(new File(renameMap.get(mp3File)));
		}

		RemoveOriginals.remove(folder);
	}

}
