import model.Task;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private Task task = null;
    private List<Task> taskList = new ArrayList<>();

    public Task addTask(String str) { // task 추가
        task = new Task(str);
        taskList.add(task);
        return task;
    }

    public Task findTask(String content) { // 내용으로 task 조회
        Task task = null;
        for (int i = 0; i < taskList.size(); i++) {
            Task temp = taskList.get(i);
            if (temp.getContent().equals(content)) {
                task = temp;
            }
        }
        return task;
    }

    public void taskCheck(Task task) { // task 완료 여부 변경 (true면 false로, false면 true로)
        task.setIsComplete(!task.getIsComplete());
    }

    public void deleteTask(Task task) { // task 삭제
        taskList.remove(task);
    }
}
