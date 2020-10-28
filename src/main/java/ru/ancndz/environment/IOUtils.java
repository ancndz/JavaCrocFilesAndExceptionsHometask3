package ru.ancndz.environment;

import ru.ancndz.objects.Recordable;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class IOUtils<T extends Recordable> {

    private final String pathToFiles = "src/Main/resources/temp/";

    public void save(T item) {
        try {
            createPath(item.getName());
            OutputStream fileOutputStream = new FileOutputStream(pathToFiles + item.getName() + "/" + item.getCode());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(item);

            fileOutputStream.close();
            objectOutputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void saveAll(Set<T> itemList) {
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

    public boolean deleteItemByNameAndCode(String name, long code) {
        try {
            File file = new File(pathToFiles + name + "/" + code);
            return file.delete();
        } catch (SecurityException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAllByName(String name) {
        File dir = new File(pathToFiles + name);
        File[] files = dir.listFiles();
        if (files != null) {
            for (File eachFile : files) {
                if (!deleteItemByNameAndCode(name, Long.parseLong(eachFile.getName()))) {
                    return false;
                }
            }
            dir.delete();
        }
        return true;
    }

    public boolean delete(T item) {
        return deleteItemByNameAndCode(item.getName(), item.getCode());
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
            fileInputStream.close();
            objectInputStream.close();
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
                fileInputStream.close();
                objectInputStream.close();
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
