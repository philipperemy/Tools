package deezer;

import java.util.ArrayList;
import java.util.List;

public class SongList
{

    private List<SongEntity> list = new ArrayList<>();

    public void push(SongEntity songEntity)
    {
        list.add(songEntity);
    }

    public void print()
    {
        int count = 0;
        for (SongEntity songEntity : list)
        {
            System.out.println(++count + " " + songEntity.getSongTitle() + " | " + songEntity.getAuthor() + " | " + songEntity.getAlbum());
        }
    }

    public SongEntity get(int i)
    {
        return list.get(i);
    }

}
