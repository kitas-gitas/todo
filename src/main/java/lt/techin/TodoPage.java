package lt.techin;

public interface TodoPage {
    void addTodo(String text);

    boolean isTodoPresent(String todoText);

    int getNumberOfTodos();

    void clickTodoWithText(String todoText);

    boolean isTodoCompleted(String todoText);

    void deleteTodo(String todoText);


    void visit();
}
