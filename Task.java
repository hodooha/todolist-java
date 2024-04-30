package model;

public class Task {
    private String content;
    private boolean isComplete; // task 완료 여부

    public Task(String content) {
        this.content = content;
        isComplete = false;
    }

    public String getContent() {
        return content;
    }

    public boolean getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }
}
