package ru.ancndz.environment.IOUtilsTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ancndz.environment.IOUtils;
import ru.ancndz.objects.Task;
import ru.ancndz.objects.TaskList;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

class IOUtilsLoadTest {

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
        taskIOUtils.saveAll(taskList.getTaskList());
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
    void loadLast() {
        Task task = (Task) taskIOUtils.loadLast("Name1");
        Assertions.assertEquals(4, task.getCode());
    }

    @Test
    void loadAllByName() {
        List<Task> allTasks = taskIOUtils.loadAllByName("Name1");
        Assertions.assertEquals(this.taskList.findAllByName("Name1"), allTasks);
    }

    @Test
    void loadAll() {
        List<Task> allTasks = taskIOUtils.loadAll();
        Assertions.assertEquals(this.taskList.getTaskList(), allTasks);
    }
}