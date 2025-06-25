import ui.TaskManagerUI;
import javax.swing.SwingUtilities;

public class Main {
    // Запускаємо UI в Event Dispatch Thread
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TaskManagerUI::new);
    }
}