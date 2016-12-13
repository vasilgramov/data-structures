package p02_directories;

public class File {
    private String name;

    private long size;

    //--------------------------------------------------------
    public File(String name, long size) {
        this.setName(name);
        this.setSize(size);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    private void setSize(long size) {
        this.size = size;
    }
    //---------------------------------------------------------


}
