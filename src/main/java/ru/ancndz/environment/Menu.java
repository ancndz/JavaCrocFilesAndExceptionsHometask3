package ru.ancndz.environment;

import ru.ancndz.objects.Recordable;
import ru.ancndz.objects.Task;
import ru.ancndz.objects.TaskList;

import java.util.List;
import java.util.Scanner;

public class Menu {

    Scanner scanner;
    TaskList taskList;
    IOUtils<Task> taskIOUtils;

    public Menu(Scanner scanner, IOUtils<Task> taskIOUtils) {
        this.scanner = scanner;
        this.taskIOUtils = taskIOUtils;
        this.taskList = new TaskList(taskIOUtils.loadAll());
    }

    public void mainMenu() {
        System.out.println("\n#############\nOOO ROGA I KOPITA\n"+
                "LOADING...\n"+
                "MAIN MENU\n"+
                "CHOOSE OPTION:\n"+
                "1 - LOAD ALL TASKS\n"+
                "2 - GET TASK INFO\n"+
                "3 - UPDATE TASK STATUS\n"+
                "4 - UPDATE TASK EXECUTOR\n"+
                "5 - DELETE LAST UPDATE\n" +
                "6 - DELETE WHOLE TASK\n" +
                "7 - NEW TASK\n");
        switch (scanner.nextInt()) {
            case 1:
                getAllTask();
                break;
            case 2:
                getTaskInfo();
                break;
            case 3:
                updateTaskStatus();
                break;
            case 4:
                updateTaskExecutor();
                break;
            case 5:
                deleteLastUpdate();
                break;
            case 6:
                deleteTask();
                break;
            case 7:
                newTask();
                break;
            default:
                System.out.println("Please, enter 1-7!");
        }
    }

    public void getAllTask() {
        String temp = scanner.nextLine();
        StringBuilder allTasksInfo = new StringBuilder("All tasks:\n");
        List<Task> allTasksList = taskIOUtils.loadAll();
        taskList = new TaskList(allTasksList);
        for (Task eachTask: allTasksList) {
            allTasksInfo.append(eachTask.toString());
        }
        System.out.println(allTasksInfo.toString());
        mainMenu();
    }

    public void getTaskInfo() {
        String temp = scanner.nextLine();
        System.out.println("Enter task name:");
        String name = scanner.nextLine();
        System.out.println(taskList.findLastByName(name).toString());
        mainMenu();
    }

    public void newTask() {
        String temp = scanner.nextLine();
        System.out.println("Enter task name:");
        String name = scanner.nextLine();
        System.out.println("Enter task description:");
        String desc = scanner.nextLine();
        Task newTask = new Task(name, desc);
        taskList.add(newTask);
        taskIOUtils.save(newTask);
        System.out.println("Done!");
        mainMenu();
    }

    public void updateTaskStatus() {
        String temp = scanner.nextLine();
        System.out.println("Enter task name:");
        String name = scanner.nextLine();
        System.out.println("Enter task status:");
        String status = scanner.nextLine();

        Task task = taskList.findLastByName(name);
        task.setStatus(status);
        task.updateCode();
        taskList.add(task);
        taskIOUtils.save(task);

        System.out.println("Done!");
        System.out.println("Current task version is " + task.getCode());
        mainMenu();
    }

    public void updateTaskExecutor() {
        String temp = scanner.nextLine();
        System.out.println("Enter task name:");
        String name = scanner.nextLine();
        System.out.println("Enter task status:");
        String executor = scanner.nextLine();

        Task task = taskList.findLastByName(name);
        task.setExecutor(executor);
        task.updateCode();
        taskList.add(task);
        taskIOUtils.save(task);

        System.out.println("Done!");
        System.out.println("Current task version is " + task.getCode());
        mainMenu();
    }

    public void deleteLastUpdate() {
        String temp = scanner.nextLine();
        System.out.println("Enter task name:");
        String name = scanner.nextLine();

        Task task = taskList.findLastByName(name);
        taskList.delete(task);
        taskIOUtils.delete(task);

        System.out.println("Done!");
        System.out.println("Current task version is " + taskList.findLastByName(name).getCode());
        mainMenu();
    }

    /**
     * delete whole folder
     */
    public void deleteTask() {
        String temp = scanner.nextLine();
        System.out.println("Enter task name:");
        String name = scanner.nextLine();

        taskList.deleteByName(name);
        taskIOUtils.deleteAllByName(name);

        System.out.println("Done!");
        mainMenu();
    }
}
