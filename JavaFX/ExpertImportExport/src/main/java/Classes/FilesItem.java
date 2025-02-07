package Classes;

import javafx.scene.control.Button;

import java.util.List;

public class FilesItem {
    public Integer idFile = null;
    public String nameFile = null;

    public FilesItem(Integer idFile, String name) {
        this.idFile = idFile;
        this.nameFile = name;
    }

    public Integer getIdFile() {
        return idFile;
    }

    public void setIdFile(Integer id) {
        this.idFile = id;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String name) {
        this.nameFile = name;
    }
}
