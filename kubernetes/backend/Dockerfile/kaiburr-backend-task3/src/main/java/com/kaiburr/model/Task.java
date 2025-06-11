package com.kaiburr.model;

import java.util.ArrayList;
import java.util.List;

public class Task {
    public String id;
    public String name;
    public String owner;
    public String command;
    public List<TaskExecution> taskExecutions = new ArrayList<>();
}
