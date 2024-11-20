import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

class Task {
    private String title;
    private String description;
    private String creationDate;
    private int deadlineDays;
    private static final String FILE_NAME = "tasks.txt";

    public Task(String title, String description, int deadlineDays) {
        this.title = title;
        this.description = description;
        this.creationDate = getCurrentDate();
        this.deadlineDays = deadlineDays;
    }

    public Task() {
    }

    private String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(new Date());
    }

    @Override
    public String toString() {
        return title + " " + description + " " + creationDate + " " + deadlineDays;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public int getDeadlineDays() {
        return deadlineDays;
    }

    // Метод для создания/открытия файла
    public void createFile() {
        try {
            File file = new File(FILE_NAME);
            if (file.createNewFile()) {
                System.out.println("Файл " + FILE_NAME + " был создан.");
            } else {
                System.out.println("Файл " + FILE_NAME + " уже существует.");
            }
        } catch (IOException e) {
            System.err.println("Ошибка создания файла: " + e.getMessage());
        }
    }

    // Метод для добавления задачи в файл
    public void addTask(Task task) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(task.toString());
            writer.newLine();
            System.out.println("Задача добавлена!");
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    // Метод для чтения задач из файла
    public void listTasks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            int id = 1;
            System.out.println("Задачи:");
            while ((line = reader.readLine()) != null) {
                System.out.println(id + ": " + line);
                id++;
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    // Метод для удаления задачи из файла
    public void deleteTask(int id) {
        List<String> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(line);
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
            return;
        }

        if (id < 1 || id > tasks.size()) {
            System.out.println("Неверный ID задачи!");
            return;
        }

        tasks.remove(id - 1); // Удаляем задачу с указанным ID

        // Перезаписываем оставшиеся задачи в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
            for (String task : tasks) {
                writer.write(task);
                writer.newLine();
            }
            System.out.println("Задача удалена!");
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }
    }
}
