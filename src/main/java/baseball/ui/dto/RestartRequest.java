package baseball.ui.dto;

public class RestartRequest {
    private boolean isRestarted;

    public RestartRequest(boolean isRestarted) {
        this.isRestarted = isRestarted;
    }

    public boolean isRestarted() {
        return this.isRestarted;
    }
}
