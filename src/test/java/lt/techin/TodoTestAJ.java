package lt.techin;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class TodoTestAJ {

    WebDriver driver;
    TodoPage todoPage;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        todoPage = new TodoPageWebUni(driver);
        todoPage.visit();
    }

    @Test
    void testAddTodo() {
        String todo = "aaaa";
        int sizeBefore = todoPage.getNumberOfTodos();
        todoPage.addTodo(todo);
        assertThat(todoPage.isTodoPresent(todo))
            .withFailMessage("Todo item should be present after adding.")
            .isTrue();
        assertThat(todoPage.getNumberOfTodos())
            .withFailMessage("Number of todos should increase after adding a new item.")
            .isEqualTo(sizeBefore + 1);
    }

    @Test
    void testDeleteTodo() {
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
    void testMarkTodoCompleted() {
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