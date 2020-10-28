package ru.ancndz.objects;

import java.util.*;
import java.util.stream.Collectors;

public class TaskSet {
    private final Set<Task> taskSet;

    public TaskSet(Set<Task> taskList) {
        this.taskSet = taskList;
    }

    public void add(Task task) {
        this.taskSet.add(task);
    }

    public void addAll(Set<Task> taskSet) {
        this.taskSet.addAll(taskSet);
    }

    public Task findLastByName(String name) {
        List<Task> taskList = findAllByName(name);
        return taskList.stream().max(Comparator.comparing(Task::getCode)).orElse(taskList.get(0));
    }

    public boolean deleteByNameAndCode(String name, long code) {
        return taskSet.removeAll(findByNameAndCode(name, code));
    }

    public List<Task> findAllByName(String name) {
        return taskSet.stream().filter(task -> task.getName().equals(name)).collect(Collectors.toList());
    }

    public List<Task> findByNameAndCode(String name, long code) {
        return taskSet.stream().filter(task -> task.getName().equals(name) && task.getCode() == code).collect(Collectors.toList());
    }

    public List<Task> getAllLastTasks() {
        Set<String> allNames = new HashSet<>();
        taskSet.forEach(task -> allNames.add(task.getName()));
        List<Task> allLasts = new LinkedList<>();
        for (String name: allNames) {
            allLasts.add(findLastByName(name));
        }
        return allLasts;
    }

    public boolean delete(Task task) {
        return this.taskSet.remove(task);
    }

    public boolean deleteByName(String name) {
        return taskSet.removeAll(findAllByName(name));
    }

    public Set<Task> getTaskSet() {
        return taskSet;
    }
}
