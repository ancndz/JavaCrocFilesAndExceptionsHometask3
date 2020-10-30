package ru.ancndz.objects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

class TaskListTest {

    TaskList taskListProvider = new TaskList(new LinkedList<>());
    List<Task> testTaskSet = new LinkedList<>();
    Task testTask = new Task(10, "Name10", "desc");

    @BeforeEach
    void initTasks() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                testTaskSet.add(new Task(j, "Name" + i, "some desc"));
            }
        }
    }

    @Test
    void add() {
        Assertions.assertTrue(taskListProvider.getTaskList().isEmpty());
        taskListProvider.add(testTask);
        Assertions.assertTrue(taskListProvider.getTaskList().contains(testTask));
        taskListProvider = new TaskList(new LinkedList<>());
    }

    @Test
    void addAll() {
        Assertions.assertTrue(taskListProvider.getTaskList().isEmpty());
        taskListProvider.addAll(testTaskSet);
        Assertions.assertEquals(testTaskSet.size(), taskListProvider.getTaskList().size());
        taskListProvider = new TaskList(new LinkedList<>());
    }

    @Test
    void findByNameAndCode() {
        taskListProvider = new TaskList(testTaskSet);
        taskListProvider.add(testTask);
        Assertions.assertEquals(testTask, taskListProvider.findByNameAndCode("Name10", 10).get(0));
    }

    @Test
    void deleteByNameAndCode() {
        taskListProvider = new TaskList(testTaskSet);
        taskListProvider.add(testTask);
        taskListProvider.deleteByNameAndCode("Name10", 10);
        Assertions.assertFalse(taskListProvider.getTaskList().contains(testTask));
    }


    @Test
    void findLastByName() {
        taskListProvider = new TaskList(testTaskSet);
        taskListProvider.add(testTask);
        Task testTask = taskListProvider.findLastByName("Name1");
        Assertions.assertEquals(4, testTask.getCode());

    }

    @Test
    void findAllByName() {
        taskListProvider = new TaskList(testTaskSet);
        taskListProvider.add(testTask);
        List<Task> testList = taskListProvider.findAllByName("Name1");
        for (Task eachTask: testList) {
            Assertions.assertEquals("Name1", eachTask.getName());
        }
    }
}