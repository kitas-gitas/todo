package lt.techin;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public class TodoPageMVC extends BasePage implements TodoPage{


    @FindBy(css = "#todo-input")
    private WebElement todoInput;

    @FindBy(css = "ul.todo-list li")
    private List<WebElement> todos;


    public TodoPageMVC(WebDriver driver) {
        super(driver);
    }

    @Override
    public void addTodo(String text) {
        todoInput.sendKeys(text);
        todoInput.sendKeys(Keys.ENTER);
    }

    @Override
    public boolean isTodoPresent(String todoText) {
        return todos.stream()
                .anyMatch(todo -> todo.findElement(By.cssSelector("label")).getText().equals(todoText));
    }

    @Override
    public int getNumberOfTodos() {
        return todos.size();
    }

    @Override
    public void clickTodoWithText(String todoText) {
        todos.stream()
                .filter(task -> task.findElement(By.cssSelector("label")).getText().equals(todoText))
                .findFirst()
                .ifPresent(task -> task.findElement(By.cssSelector("input.toggle")).click());
    }

    @Override
    public boolean isTodoCompleted(String todoText) {
        return todos.stream()

                .filter(task -> task.findElement(By.cssSelector("label")).getText().equals(todoText))
                .findFirst()
                .map(task -> task.getAttribute("class"))
                .map(classes -> classes.contains("completed"))
                .orElse(false);
    }

    @Override
    public void deleteTodo(String todoText) {

        Optional<WebElement> todoToDelete = todos.stream()
                .filter(task -> task.findElement(By.cssSelector("label")).getText().equals(todoText))
                .findFirst();

        todoToDelete.ifPresent(task -> {
            Actions action = new Actions(driver);
            action.moveToElement(task).perform();

            WebElement deleteButton = task.findElement(By.cssSelector("button.destroy"));
            deleteButton.click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.invisibilityOf(task));
        });


    }

    @Override
    public void visit() {
            driver.get("https://todomvc.com/examples/react/dist/");
    }
}
