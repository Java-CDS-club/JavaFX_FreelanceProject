package sample.controllers;

public class ToolbarSingle {
    private static ToolBar ourInstance;

    public static ToolBar getInstance() {

        if (ourInstance == null) {
            ourInstance = new ToolBar();
        }
        return ourInstance;
    }

    private ToolbarSingle() {
    }
}
