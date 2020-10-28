package ru.ancndz.environment;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ancndz.objects.Task;
import ru.ancndz.objects.TaskSet;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IOUtilsDeleteTest {

    private final String pathToFiles = "src/Main/resources/temp/";

    IOUtils<Task> taskIOUtils = new IOUtils<>();
    TaskSet taskSet = new TaskSet(new HashSet<>());

    @BeforeEach
    void initTasks() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                taskSet.add(new Task(j, "Name" + i, "some desc"));
            }
        }
    }

    @AfterEach
    void clearTask() throws IOException {
        File testDir = new File("src/Main/resources/temp/");
        deleteAll(testDir);
    }

    void deleteAll(File file) throws IOException {
        if (file.isDirectory()) {
            File[] entries = file.listFiles();
            if (entries != null) {
                for (File entry : entries) {
                    deleteAll(entry);
                }
            }
        }
        if (!file.delete()) {
            throw new IOException("Failed to delete " + file);
        }
    }

    @Test
    void deleteItemByNameAndCode() {
        taskIOUtils.saveAll(taskSet.getTaskSet());
        taskIOUtils.deleteItemByNameAndCode("Name2", 1);
        Assertions.assertFalse(new File(pathToFiles + "Name2/1").exists());
    }

    @Test
    void deleteAllByName() {
        taskIOUtils.saveAll(taskSet.getTaskSet());
        taskIOUtils.deleteAllByName("Name1");
        Assertions.assertFalse(new File(pathToFiles + "Name1").exists());
    }
}