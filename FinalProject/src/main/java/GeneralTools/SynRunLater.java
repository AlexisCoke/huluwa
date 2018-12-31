package GeneralTools;

import javafx.application.Platform;

public class SynRunLater{
    public static synchronized void runLater(Runnable runnable) {
        Platform.runLater(runnable);
    }
}

