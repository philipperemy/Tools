package mp3;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FileListing
{

    public static List<File> ls(String path)
    {
        List<File> list = new ArrayList<>();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++)
        {
            if (listOfFiles[i].isFile())
            {
                list.add(listOfFiles[i]);
            }
        }

        return list;
    }

    public static String getExtension(File file)
    {
        int i = file.getName().lastIndexOf('.');
        if (i > 0)
        {
            return file.getName().substring(i + 1);
        }
        return "";
    }

    public static List<File> ls(String path, String extension)
    {
        List<File> list = new ArrayList<>();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        Arrays.sort(listOfFiles, new Comparator<File>()
        {
            public int compare(File f1, File f2)
            {
                return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
            }
        });

        for (int i = 0; i < listOfFiles.length; i++)
        {
            if (listOfFiles[i].isFile())
            {
                list.add(listOfFiles[i]);
            }
        }

        List<File> resultsFiles = new ArrayList<>();
        if (extension != null)
        {
            for (File file : list)
            {
                if (getExtension(file).equalsIgnoreCase(extension))
                {
                    resultsFiles.add(file);
                }
            }
        }
        else
        {
            resultsFiles.addAll(list);
        }

        return resultsFiles;
    }

}
