package baseball.ui.dto;

public class RestartInput {
    private boolean isRestarted;

    public RestartInput(boolean isRestarted) {
        this.isRestarted = isRestarted;
    }

    public boolean isRestarted() {
        return this.isRestarted;
    }
}
