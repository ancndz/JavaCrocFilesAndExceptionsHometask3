package ru.ancndz.objects;

import java.util.*;
import java.util.stream.Collectors;

public class TaskList {
    private final List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void addAll(List<Task> taskList) {
        this.taskList.addAll(taskList);
    }

    public Task findLastByName(String name) {
        List<Task> taskList = findAllByName(name);
        Task maxVerCodeTask = taskList.get(0);
        for (Task eachTask: taskList) {
            if (eachTask.getCode() > maxVerCodeTask.getCode()) {
                maxVerCodeTask = eachTask;
            }
        }
        return maxVerCodeTask;
    }

    public boolean deleteByNameAndCode(String name, long code) {
        return taskList.removeAll(findByNameAndCode(name, code));
    }

    public List<Task> findAllByName(String name) {
        List<Task> taskListWithName = new LinkedList<>();
        for (Task eachTask : taskList) {
            if (eachTask.getName().equals(name)) {
                taskListWithName.add(eachTask);
            }
        }
        return taskListWithName;
    }

    public List<Task> findByNameAndCode(String name, long code) {
        List<Task> taskListWithNameAndCode = new LinkedList<>();
        for (Task eachTask : taskList) {
            if (eachTask.getName().equals(name) && eachTask.getCode() == code) {
                taskListWithNameAndCode.add(eachTask);
            }
        }
        return taskListWithNameAndCode;
    }

    public List<Task> getAllLastTasks() {
        Set<String> allNames = new HashSet<>();
        for (Task eachTask: taskList) {
            allNames.add(eachTask.getName());
        }
        //taskList.forEach(task -> allNames.add(task.getName()));
        List<Task> allLasts = new LinkedList<>();
        for (String name: allNames) {
            allLasts.add(findLastByName(name));
        }
        return allLasts;
    }

    public boolean delete(Task task) {
        return this.taskList.remove(task);
    }

    public boolean deleteByName(String name) {
        return taskList.removeAll(findAllByName(name));
    }

    public List<Task> getTaskList() {
        return taskList;
    }
}
