package core;

import model.Task;
import shared.Scheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcessScheduler implements Scheduler {

    private List<Task> tasks;

    public ProcessScheduler() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public void add(Task task) {
        this.tasks.add(task);
    }

    @Override
    public Task process() {

        if (this.size() == 0) {
            return null;
        }

        return this.tasks.remove(0);
    }

    @Override
    public Task peek() {

        if (this.size() == 0) {
            return null;
        }

        return this.tasks.get(0);
    }

    @Override
    public Boolean contains(Task task) {

        return this.tasks.indexOf(task) != -1;
    }

    @Override
    public int size() {
        return this.tasks.size();
    }

    @Override
    public Boolean remove(Task task) {

        this.find(task.getId());

        return this.tasks.remove(task);
    }

    @Override
    public Boolean remove(int id) {

        Task task = this.find(id);

        return this.tasks.remove(task);
    }

    @Override
    public void insertBefore(int id, Task task) {

        Task oldTask = find(id);

        int index = this.tasks.indexOf(oldTask);

        this.tasks.add(index, task);
    }

    @Override
    public void insertAfter(int id, Task task) {
        Task oldTask = find(id);

        int index = this.tasks.indexOf(oldTask);

        this.tasks.add(index + 1, task);
    }

    @Override
    public void clear() {
        this.tasks.clear();
    }

    @Override
    public Task[] toArray() {
        Task[] items = new Task[this.size()];

        return this.tasks.toArray(items);
    }

    @Override
    public void reschedule(Task first, Task second) {

        if (!this.contains(first) || !this.contains(second)) {
            throw new IllegalArgumentException("Task not found");
        }

        int firstIndex = this.tasks.indexOf(first);
        int secondIndex = this.tasks.indexOf(second);
        this.tasks.set(firstIndex, second);
        this.tasks.set(secondIndex, first);
    }

    @Override
    public List<Task> toList() {
        return Collections.unmodifiableList(this.tasks);
    }

    @Override
    public void reverse() {
        Collections.reverse(this.tasks);
    }

    @Override
    public Task find(int id) {

        for (Task task : this.tasks) {
            if (task.getId() == id) {
                return task;
            }
        }

        throw new IllegalArgumentException();
    }

    @Override
    public Task find(Task task) {

        return this.find(task.getId());
    }
}
