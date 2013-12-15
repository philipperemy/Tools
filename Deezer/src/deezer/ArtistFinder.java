package deezer;

public class ArtistFinder
{

    private boolean blockTdArtistFound;

    private boolean aHrefArtistFound;

    public void blockArtistFound()
    {
        blockTdArtistFound = true;
    }

    public void artistFound()
    {
        if (blockTdArtistFound)
        {
            aHrefArtistFound = true;
        }
    }

    public void reset()
    {
        blockTdArtistFound = false;
        aHrefArtistFound = false;
    }

    public boolean artistIsFound()
    {
        return blockTdArtistFound && aHrefArtistFound;
    }

}
