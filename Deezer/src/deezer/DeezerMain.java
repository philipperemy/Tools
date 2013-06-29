package deezer;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class DeezerMain {

	public static void main(String[] args) {
		XMLParser xmlParser = new XMLParser();
		SongList songList = new SongList();
		try {
			xmlParser.parse("C:\\Users\\Philippe Rémy\\Desktop\\musics.xml", songList);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		
		songList.print();
	}

}
