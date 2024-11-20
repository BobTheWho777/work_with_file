import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {
        Task manager = new Task();
        manager.createFile(); // Создать файл при запуске
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Создать задачу");
            System.out.println("2. Просмотреть задачи");
            System.out.println("3. Удалить задачу");
            System.out.println("4. Выйти");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Очистка буфера

            switch (choice) {
                case 1:
                    System.out.print("Введите название задачи: ");
                    String title = scanner.nextLine();
                    System.out.print("Введите описание задачи: ");
                    String description = scanner.nextLine();
                    System.out.print("Введите срок выполнения (в днях): ");
                    int deadlineDays = scanner.nextInt();
                    manager.addTask(new Task(title, description, deadlineDays));
                    break;
                case 2:
                    manager.listTasks();
                    break;
                case 3:
                    System.out.print("Введите ID задачи для удаления: ");
                    int idToDelete = scanner.nextInt();
                    manager.deleteTask(idToDelete);
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Неправильный выбор, попробуйте снова.");
            }
        }
    }
}