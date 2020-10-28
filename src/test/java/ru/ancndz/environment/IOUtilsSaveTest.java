package ru.ancndz.environment;

import org.junit.jupiter.api.*;
import ru.ancndz.objects.Task;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IOUtilsSaveTest {

    IOUtils<Task> taskIOUtils = new IOUtils<>();
    List<Task> taskList;

    @BeforeEach
    void initTasks() {
        this.taskList = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                taskList.add(new Task(j, "Name" + i, "some desc"));
            }
        }
    }

    @AfterEach
    void clearTask() throws IOException {
        File testDir = new File("src/main/resources/");
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
    void save() {
        taskIOUtils.save(taskList.get(0));
        assertTrue(new File("src/main/resources/Name0/0").exists());
    }

    @Test
    void saveAll() {
        taskIOUtils.saveAll(taskList);
    }

}