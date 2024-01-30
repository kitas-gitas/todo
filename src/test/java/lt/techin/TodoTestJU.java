package lt.techin;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class TodoTestJU {


    WebDriver driver;

    TodoPage todoPage;


    @BeforeEach
    void setup(){
        driver = new ChromeDriver();
        //driver.get("http://webdriveruniversity.com/To-Do-List/index.html");
        todoPage = new TodoPageMVC(driver);
        todoPage.visit();
    }


    @Test
    void testAddTodo(){

        String todo = "aaaa";
        int sizeBefore = todoPage.getNumberOfTodos();
        todoPage.addTodo(todo);
        assertTrue(todoPage.isTodoPresent(todo));
        assertEquals(sizeBefore + 1, todoPage.getNumberOfTodos());
    }

    @Test
    void testDeleteTodo(){
        String todoToDelete = "to be deleted";
        todoPage.addTodo("to be deleted");
        assertTrue(todoPage.isTodoPresent(todoToDelete));
        todoPage.deleteTodo(todoToDelete);
        System.out.println(todoPage.getNumberOfTodos());
        assertFalse(todoPage.isTodoPresent(todoToDelete));
    }

    @Test
    void testMarkTodoCompleted(){
        String todoToMark = "to be marked";
        todoPage.addTodo("to be marked");
        assertTrue(todoPage.isTodoPresent(todoToMark));
        assertFalse(todoPage.isTodoCompleted(todoToMark));
        todoPage.clickTodoWithText(todoToMark);
        assertTrue(todoPage.isTodoCompleted(todoToMark));
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }


}
