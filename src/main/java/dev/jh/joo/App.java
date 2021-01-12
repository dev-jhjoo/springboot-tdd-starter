package dev.jh.joo;

public class App {

    private AppStatus status = AppStatus.DRAFT;
    private int limit;

    public App(int limit) {
        if(limit <= 0){
            throw new IllegalArgumentException("limit은 0보다 커야한다.");
        }
        this.limit = limit;
    }

    public int getLimit(){
        return this.limit;
    }

    public AppStatus getStatus() {
        return this.status;
    }
}
