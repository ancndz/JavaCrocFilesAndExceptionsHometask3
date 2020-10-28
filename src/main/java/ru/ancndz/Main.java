package ru.ancndz;

import ru.ancndz.environment.IOUtils;
import ru.ancndz.environment.Menu;
import ru.ancndz.objects.Task;
import ru.ancndz.objects.TaskSet;

import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IOUtils<Task> taskIOUtils = new IOUtils<>();
        TaskSet taskSet = new TaskSet(new HashSet<>());
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                taskSet.add(new Task(j, "Name" + i, "some desc"));
            }
        }
        taskIOUtils.saveAll(taskSet.getTaskSet());

        Menu menu = new Menu(taskSet, scanner, taskIOUtils);
        menu.mainMenu();
    }
}
