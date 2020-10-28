package ru.ancndz.environment;

import ru.ancndz.objects.Task;
import ru.ancndz.objects.TaskSet;

import java.util.Scanner;

public class Menu {

    Scanner scanner;
    TaskSet taskSet;
    IOUtils<Task> taskIOUtils;

    public Menu(TaskSet taskSet, Scanner scanner, IOUtils<Task> taskIOUtils) {
        this.taskSet = taskSet;
        this.scanner = scanner;
        this.taskIOUtils = taskIOUtils;
    }

    public void mainMenu() {
        System.out.println("\n#############\nOOO ROGA I KOPITA\n"+
                "LOADING...\n"+
                "MAIN MENU\n"+
                "CHOOSE OPTION:\n"+
                "1 - GET ALL TASKS\n"+
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
        StringBuilder allTasks = new StringBuilder("All tasks:\n");
        for (Task eachTask: taskSet.getAllLastTasks()) {
            allTasks.append(eachTask.toString());
        }
        System.out.println(allTasks.toString());
        mainMenu();
    }

    public void getTaskInfo() {
        String temp = scanner.nextLine();
        System.out.println("Enter task name:");
        String name = scanner.nextLine();
        System.out.println(taskSet.findLastByName(name).toString());
        mainMenu();
    }

    public void newTask() {
        String temp = scanner.nextLine();
        System.out.println("Enter task name:");
        String name = scanner.nextLine();
        System.out.println("Enter task description:");
        String desc = scanner.nextLine();
        Task newTask = new Task(name, desc);
        taskSet.add(newTask);
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

        Task task = taskSet.findLastByName(name);
        task.setStatus(status);
        task.updateCode();
        taskSet.add(task);
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

        Task task = taskSet.findLastByName(name);
        task.setExecutor(executor);
        task.updateCode();
        taskSet.add(task);
        taskIOUtils.save(task);

        System.out.println("Done!");
        System.out.println("Current task version is " + task.getCode());
        mainMenu();
    }

    public void deleteLastUpdate() {
        String temp = scanner.nextLine();
        System.out.println("Enter task name:");
        String name = scanner.nextLine();

        Task task = taskSet.findLastByName(name);
        taskSet.delete(task);
        taskIOUtils.delete(task);

        System.out.println("Done!");
        System.out.println("Current task version is " + taskSet.findLastByName(name).getCode());
        mainMenu();
    }

    /**
     * delete whole folder
     */
    public void deleteTask() {
        String temp = scanner.nextLine();
        System.out.println("Enter task name:");
        String name = scanner.nextLine();

        taskSet.deleteByName(name);
        taskIOUtils.deleteAllByName(name);

        System.out.println("Done!");
        mainMenu();
    }
}
