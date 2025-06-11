package com.kaiburr;

import com.kaiburr.model.Task;
import com.kaiburr.service.TaskService;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        TaskService service = new TaskService();
        ObjectMapper mapper = new ObjectMapper();

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/tasks", (exchange -> {
            String method = exchange.getRequestMethod();
            String response = "";

            switch (method) {
                case "GET":
                    List<Task> tasks = service.getAllTasks();
                    response = mapper.writeValueAsString(tasks);
                    break;
                case "PUT":
                    Task task = mapper.readValue(exchange.getRequestBody(), Task.class);
                    service.addTask(task);
                    response = "Task added.";
                    break;
                default:
                    response = "Unsupported method.";
            }

            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }));

        System.out.println("Server started on port 8080");
        server.start();
    }
}
