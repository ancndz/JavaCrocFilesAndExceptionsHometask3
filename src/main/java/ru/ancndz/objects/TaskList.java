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
        return taskList.stream().max(Comparator.comparing(Task::getCode)).orElse(taskList.get(0));
    }

    public boolean deleteByNameAndCode(String name, long code) {
        return taskList.removeAll(findByNameAndCode(name, code));
    }

    public List<Task> findAllByName(String name) {
        return taskList.stream().filter(task -> task.getName().equals(name)).collect(Collectors.toList());
    }

    public List<Task> findByNameAndCode(String name, long code) {
        return taskList.stream().filter(task -> task.getName().equals(name) && task.getCode() == code).collect(Collectors.toList());
    }

    public List<Task> getAllLastTasks() {
        Set<String> allNames = new HashSet<>();
        taskList.forEach(task -> allNames.add(task.getName()));
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
