package Classes;

import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public List<FilesItem> filesList;
    public int countFiles;

    public FileManager() {
        filesList = new ArrayList<>();
    }

    public void addFiles(FilesItem filesItem) {
        filesList.add(filesItem);
        countFiles += 1;
    }

    public void removeFiles(FilesItem filesItem) {
        for (int i = filesItem.idFile; i < countFiles; i++) {
            filesItem.idFile = i - 1;
            filesItem.nameFile = "file" + (i - 1);
        }

        filesList.remove(filesItem);
        countFiles -= 1;
    }
}
