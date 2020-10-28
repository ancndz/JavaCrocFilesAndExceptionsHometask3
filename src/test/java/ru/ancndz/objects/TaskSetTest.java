package ru.ancndz.objects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class TaskSetTest {

    TaskSet taskSetProvider = new TaskSet(new HashSet<>());
    Set<Task> testTaskSet = new HashSet<>();
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
        Assertions.assertTrue(taskSetProvider.getTaskSet().isEmpty());
        taskSetProvider.add(testTask);
        Assertions.assertTrue(taskSetProvider.getTaskSet().contains(testTask));
        taskSetProvider = new TaskSet(new HashSet<>());
    }

    @Test
    void addAll() {
        Assertions.assertTrue(taskSetProvider.getTaskSet().isEmpty());
        taskSetProvider.addAll(testTaskSet);
        Assertions.assertEquals(testTaskSet.size(), taskSetProvider.getTaskSet().size());
        taskSetProvider = new TaskSet(new HashSet<>());
    }

    @Test
    void findByNameAndCode() {
        taskSetProvider = new TaskSet(testTaskSet);
        taskSetProvider.add(testTask);
        Assertions.assertEquals(testTask, taskSetProvider.findByNameAndCode("Name10", 10).get(0));
    }

    @Test
    void deleteByNameAndCode() {
        taskSetProvider = new TaskSet(testTaskSet);
        taskSetProvider.add(testTask);
        taskSetProvider.deleteByNameAndCode("Name10", 10);
        Assertions.assertFalse(taskSetProvider.getTaskSet().contains(testTask));
    }


    @Test
    void findLastByName() {
        taskSetProvider = new TaskSet(testTaskSet);
        taskSetProvider.add(testTask);
        Task testTask = taskSetProvider.findLastByName("Name1");
        Assertions.assertEquals(4, testTask.getCode());

    }

    @Test
    void findAllByName() {
        taskSetProvider = new TaskSet(testTaskSet);
        taskSetProvider.add(testTask);
        List<Task> testList = taskSetProvider.findAllByName("Name1");
        for (Task eachTask: testList) {
            Assertions.assertEquals("Name1", eachTask.getName());
        }
    }
}