package ru.ancndz;

import ru.ancndz.environment.IOUtils;
import ru.ancndz.environment.Menu;
import ru.ancndz.objects.Task;
import ru.ancndz.objects.TaskList;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IOUtils<Task> taskIOUtils = new IOUtils<>();
        TaskList taskList = new TaskList(new LinkedList<>());

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                taskList.add(new Task(j, "Name" + i, "some desc"));
            }
        }
        taskIOUtils.saveAll(taskList.getTaskList());

        Menu menu = new Menu(scanner, taskIOUtils);
        menu.mainMenu();
    }
}
