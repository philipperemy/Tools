package deezer;

public class SongEntity
{

    private String songTitle;
    private String author;
    private String album;

    public SongEntity()
    {

    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getSongTitle()
    {
        return songTitle;
    }

    public void setSongTitle(String songTitle)
    {
        this.songTitle = songTitle;
    }

    public String getAlbum()
    {
        return album;
    }

    public void setAlbum(String album)
    {
        this.album = album;
    }

}
