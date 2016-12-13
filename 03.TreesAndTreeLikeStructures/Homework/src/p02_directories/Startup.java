package p02_directories;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Startup {
    public static String folderPath = "/home/vladix/SoftUni/DataStructureAndAlgorithm/03.TreesAndTreeLikeStructures/p02Folder";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String folderName = folderPath.substring(folderPath.lastIndexOf('/') + 1);
        Folder folder = new Folder(folderName);

        File file = new File(folderPath);
        File[] direction = file.listFiles();
        addFiles(direction, folder);

        System.out.println();

        printFolders(-1, folder);
    }

    private static void addFiles(File[] directions, Folder folder) {
        for (File file : directions) {
            if (file.isFile()) {
                folder.addFile(new p02_directories.File(file.getName(), file.length()));
            } else {
                folder.addChildFolder(new Folder(file.getName()));
                addFiles(file.listFiles(), folder.getChildFolder().get(folder.getChildFolder().size() - 1));
            }
        }
    }

    private static void printFolders(int indent, Folder folder) {
        System.out.println(newString(" ", indent) + folder.getName() + "---------------------------------------");
        printFiles(indent + 3, folder.getFiles());

        if (folder.getChildFolder().size() > 0) {
            for (Folder folder1 : folder.getChildFolder()) {
                printFolders(indent + 3, folder1);
            }
        }
    }

    private static void printFiles(int indent, ArrayList<p02_directories.File> files) {
        for (p02_directories.File file : files) {
            System.out.println(newString(" ", indent + 1) + file.getName() + " " + file.getSize());
        }
    }

    private static String newString(String delimeter, int count) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < count; i++) {
            builder.append(delimeter);
        }

        return builder.toString();
    }
//    public void preOrderPrint(int indent) {
//        System.out.println(newString(" ", indent) + this.getValue());
//
//        if (this.leftChild != null) {
//            this.leftChild.preOrderPrint(indent + 1);
//        }
//
//        if (this.rightChild != null) {
//            this.rightChild.preOrderPrint(indent + 1);
//        }
//    }
}

