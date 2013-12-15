package deezer;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler
{

    ArtistFinder artistFinder    = new ArtistFinder();
    boolean      albumFound      = false;
    boolean      songTitleFound  = false;
    boolean      songUnavailable = false;

    SongEntity   lastSongEntity  = null;
    SongList     songList        = null;

    public XMLHandler(SongList songList)
    {
        this.songList = songList;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {

        if (qName.equalsIgnoreCase("tr"))
        {
            String clazz = attributes.getValue("class");
            if (clazz.contains("song unavailable"))
            {
                songUnavailable = true;
            }
        }

        if (qName.equalsIgnoreCase("td"))
        {
            String clazz = attributes.getValue("class");
            if (clazz.contains("artist"))
            {
                artistFinder.blockArtistFound();
            }
        }

        if (qName.equalsIgnoreCase("a"))
        {
            String href = attributes.getValue("href");
            if (href.contains("artist"))
            {
                artistFinder.artistFound();
            }
            else if (href.contains("album"))
            {
                albumFound = true;
            }
            else
            {
                String dataTarget = attributes.getValue("data-target");
                if (dataTarget != null && dataTarget.equalsIgnoreCase("track"))
                {
                    songTitleFound = true;
                }
            }
        }

    }

    public void endElement(String uri, String localName, String qName) throws SAXException
    {
    }

    public void characters(char ch[], int start, int length) throws SAXException
    {

        if (artistFinder.artistIsFound())
        {
            String artistName = new String(ch, start, length);
            callbackArtistFound(artistName);
            artistFinder.reset();
        }
        else if (albumFound)
        {
            if (!songUnavailable)
            {
                String albumTitle = new String(ch, start, length);
                callbackAlbumTitle(albumTitle);
            }
            else
            {
                songUnavailable = false;
            }
            albumFound = false;
        }
        else if (songTitleFound)
        {
            String songTitle = new String(ch, start, length);
            callbackSongTitle(songTitle);
            songTitleFound = false;
        }

    }

    private void callbackSongTitle(String songTitle)
    {
        lastSongEntity = new SongEntity();
        lastSongEntity.setSongTitle(songTitle);

    }

    private void callbackArtistFound(String artistName)
    {
        if (lastSongEntity != null)
        {
            lastSongEntity.setAuthor(artistName);
        }
    }

    private void callbackAlbumTitle(String albumTitle)
    {
        if (lastSongEntity != null)
        {
            lastSongEntity.setAlbum(albumTitle);
            songList.push(lastSongEntity);
        }
    }
}
