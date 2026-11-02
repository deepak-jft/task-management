package org.jft.controller;

import org.jft.model.Task;
import org.jft.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Task> tasks = taskService.getAllTasks();

        model.addAttribute("tasks", tasks);
        model.addAttribute("taskCount", tasks.size());
        model.addAttribute("currentTime", LocalDateTime.now().toString());
        model.addAttribute("appName", "Task Manager");
        model.addAttribute("task", new Task());

        return "index";
    }

    @PostMapping("/add")
    public String addTask(Task task) {
        taskService.saveTask(task);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteTask(@RequestParam Long id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }

    @GetMapping("about")
    public String about(){
        return "about";
    }
}
