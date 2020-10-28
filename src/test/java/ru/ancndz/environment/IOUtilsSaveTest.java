package ru.ancndz.environment;

import org.junit.jupiter.api.*;
import ru.ancndz.objects.Task;
import ru.ancndz.objects.TaskSet;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;


class IOUtilsSaveTest {

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
        File testDir = new File(pathToFiles);
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
        taskIOUtils.save(taskSet.findByNameAndCode("Name2", 2).get(0));
        Assertions.assertTrue(new File(pathToFiles + "Name2/2").exists());
    }

    @Test
    void saveAll() {
        taskIOUtils.saveAll(taskSet.getTaskSet());
        for (Task eachTask: this.taskSet.getTaskSet()) {
            Assertions.assertTrue(new File(pathToFiles + eachTask.getName() + "/" + eachTask.getCode()).exists());
        }
    }

}