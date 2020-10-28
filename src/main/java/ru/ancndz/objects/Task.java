package ru.ancndz.objects;

import java.util.Objects;

public class Task implements Recordable {
    private long code;
    private final String name;
    private final String desc;
    private String executor;
    private String status;

    public Task(String name, String desc) {
        this.code = 0L;
        this.name = name;
        this.desc = desc;
        this.status = "Created";
        this.executor = "Nobody";
    }

    public Task(long code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
        this.status = "Created";
        this.executor = "Nobody";
    }

    public void updateCode() {
        this.code += 1;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getExecutor() {
        return executor;
    }

    public String getStatus() {
        return status;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return code == task.code &&
                name.equals(task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name);
    }

    @Override
    public String toString() {
        return this.name + ": " + this.desc +
                "\n" +
                "Status: " + this.status +
                ", Executor: " + this.executor +
                "\n\n";
    }
}
