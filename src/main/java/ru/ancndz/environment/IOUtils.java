package ru.ancndz.environment;

import ru.ancndz.objects.Recordable;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class IOUtils<T extends Recordable> {

    private final String pathToFiles = "src/main/resources/";

    public void save(T item) {
        try {
            createPath(item.getName());
            OutputStream fileOutputStream = new FileOutputStream(pathToFiles + item.getName() + "/" + item.getCode());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(item);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void saveAll(List<T> itemList) {
        for (T item: itemList) {
            save(item);
        }
    }

    private void createPath(String path) {
        try {
            File dir = new File(pathToFiles + path);
            dir.mkdirs();
        } catch (SecurityException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public T loadLast(String dirName) {
        String[] filesNameList = getFilesNameList(pathToFiles + dirName);
        long lastCode = 0;

        try {
            lastCode = Long.parseLong(filesNameList[0]);
            for (String file: filesNameList) {
                long fileCode = Long.parseLong(file);
                if (fileCode > lastCode) {
                    lastCode = fileCode;
                }
            }
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        try {
            FileInputStream fileInputStream = new FileInputStream(dirName + "/" + lastCode);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (T) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public List<T> loadAll(String dirName) {
        String[] filesNameList = getFilesNameList(pathToFiles + dirName);
        List<T> filesList = new LinkedList<>();
        try {
            for (String file: filesNameList) {
                FileInputStream fileInputStream = new FileInputStream(dirName + "/" + file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                filesList.add((T) objectInputStream.readObject());
            }
        } catch (NumberFormatException | NullPointerException | IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return filesList;
    }

    private String[] getFilesNameList(String dirName) {
        File workDir = new File(dirName);
        return workDir.list();
    }
}
