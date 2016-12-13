package p02_directories;

import java.util.ArrayList;

public class Folder {
    private String name;

    private ArrayList<File> files;

    private ArrayList<Folder> childFolder;

    //---------------------------------------
    public Folder(String name) {
        this.setName(name);

        this.files = new ArrayList<>();
        this.childFolder = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public ArrayList<File> getFiles() {
        return files;
    }

    public ArrayList<Folder> getChildFolder() {
        return this.childFolder;
    }
    //---------------------------------------

    public boolean addFile(File file) {
        return this.files.add(file);
    }

    public boolean addChildFolder(Folder folder) {
        return this.childFolder.add(folder);
    }


}
