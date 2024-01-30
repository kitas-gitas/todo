package lt.techin;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class TodoTestAJ {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoTestAJ.class);

    private WebDriver driver;
    private TodoPage todoPage;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        todoPage = new TodoPageWebUni(driver);
        todoPage.visit();
    }

    @Test
    void shouldAddTodoItemSuccessfully() {
        String newItem = "Complete Selenium Test";
        int initialTodoCount = todoPage.getNumberOfTodos();

        todoPage.addTodo(newItem);
        assertThat(todoPage.isTodoPresent(newItem))
            .withFailMessage("Todo item should be present after adding.")
            .isTrue();
        assertThat(todoPage.getNumberOfTodos())
            .withFailMessage("Number of todos should increase after adding a new item.")
            .isEqualTo(initialTodoCount + 1);
    }

    @Test
    void shouldDeleteTodoItemSuccessfully() {
        String todoToDelete = "to be deleted";
        todoPage.addTodo(todoToDelete);
        assertThat(todoPage.isTodoPresent(todoToDelete))
            .withFailMessage("Todo item should be present after adding.")
            .isTrue();
        todoPage.deleteTodo(todoToDelete);
        assertThat(todoPage.isTodoPresent(todoToDelete))
            .withFailMessage("Todo item should be deleted.")
            .isFalse();
    }

    @Test
    void shouldMarkTodoItemAsCompleted() {
        String todoToMark = "to be marked";
        todoPage.addTodo(todoToMark);
        assertThat(todoPage.isTodoPresent(todoToMark))
            .withFailMessage("Todo item should be present after adding.")
            .isTrue();
        assertThat(todoPage.isTodoCompleted(todoToMark))
            .withFailMessage("Newly added todo item should not be marked as completed.")
            .isFalse();
        todoPage.clickTodoWithText(todoToMark);
        assertThat(todoPage.isTodoCompleted(todoToMark))
            .withFailMessage("Todo item should be marked as completed after clicking.")
            .isTrue();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}