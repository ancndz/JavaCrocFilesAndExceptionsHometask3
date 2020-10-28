package ru.ancndz.objects;

public class Task implements Recordable {
    private final long code;
    private final String name;
    private final String desc;
    private String executor;
    private String status;

    public Task(long code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
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

}
