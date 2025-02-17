package Ser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoList implements Serializable {
	
	//*************Attributes
    private static final long serialVersionUID = 1L;
    private List<Task> tasks;
    
  //*************Constructor
    public ToDoList() {
        tasks = new ArrayList<>();
    }
    
  //*************Methods
    public void addTask(String description) {//*************Adds a task to the list
        tasks.add(new Task(description));
        System.out.println("Task added: " + description);
    }

    public void deleteTask(int index) {//*************Deletes a task of the list if theres any in the list
        if (index >= 0 && index < tasks.size()) {
            Task removedTask = tasks.remove(index);
            System.out.println("Task deleted: " + removedTask.getDescription());
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void markTaskAsCompleted(int index) {//*************Marks it as completed if it exists
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setCompleted(true);
            System.out.println("Task marked as completed: " + tasks.get(index).getDescription());
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void showTasks() {//*************Show the list of tasks
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public void saveToFile(String filename) {//*************Save to a file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
            System.out.println("To-Do List saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ToDoList loadFromFile(String filename) {//*************Loads from a file
        ToDoList list = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            list = (ToDoList) ois.readObject();
            System.out.println("To-Do List loaded from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {//*************Main
        ToDoList list = new ToDoList();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {//***********Options of the Menu
            System.out.println("\n--- To-Do List Menu ---");
            System.out.println("1. Show Tasks");
            System.out.println("2. Add Task");
            System.out.println("3. Delete Task");
            System.out.println("4. Mark Task as Completed");
            System.out.println("5. Save To-Do List");
            System.out.println("6. Load To-Do List");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {//*************Menu loop
                case 1:
                    list.showTasks();
                    break;
                case 2:
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    list.addTask(description);
                    break;
                case 3:
                    System.out.print("Enter task index to delete: ");
                    int deleteIndex = scanner.nextInt() - 1; // Convert to 0-based index
                    list.deleteTask(deleteIndex);
                    break;
                case 4:
                    System.out.print("Enter task index to mark as completed: ");
                    int completeIndex = scanner.nextInt() - 1; // Convert to 0-based index
                    list.markTaskAsCompleted(completeIndex);
                    break;
                case 5:
                    System.out.print("Enter filename to save: ");
                    String saveFilename = scanner.nextLine();
                    list.saveToFile(saveFilename);
                    break;
                case 6:
                    System.out.print("Enter filename to load: ");
                    String loadFilename = scanner.nextLine();
                    ToDoList loadedList = ToDoList.loadFromFile(loadFilename);
                    if (loadedList != null) {
                        list = loadedList;
                    }
                    break;
                case 7:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}