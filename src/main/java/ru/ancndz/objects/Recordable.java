package ru.ancndz.objects;

import java.io.Serializable;

public interface Recordable extends Serializable {
    String getName();
    long getCode();
}
