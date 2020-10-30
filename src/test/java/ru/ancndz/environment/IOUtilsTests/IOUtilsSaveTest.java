package ru.ancndz.environment.IOUtilsTests;

import org.junit.jupiter.api.*;
import ru.ancndz.environment.IOUtils;
import ru.ancndz.objects.Task;
import ru.ancndz.objects.TaskList;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;


class IOUtilsSaveTest {

    private final String pathToFiles = "src/Main/resources/temp/";

    IOUtils<Task> taskIOUtils = new IOUtils<>();
    TaskList taskList = new TaskList(new LinkedList<>());

    @BeforeEach
    void initTasks() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                taskList.add(new Task(j, "Name" + i, "some desc"));
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
        taskIOUtils.save(taskList.findByNameAndCode("Name2", 2).get(0));
        Assertions.assertTrue(new File(pathToFiles + "Name2/2").exists());
    }

    @Test
    void saveAll() {
        taskIOUtils.saveAll(taskList.getTaskList());
        for (Task eachTask: this.taskList.getTaskList()) {
            Assertions.assertTrue(new File(pathToFiles + eachTask.getName() + "/" + eachTask.getCode()).exists());
        }
    }

}