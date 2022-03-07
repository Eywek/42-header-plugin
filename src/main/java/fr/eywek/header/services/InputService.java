package fr.eywek.header.services;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.wm.impl.status.widget.StatusBarWidgetWrapper;
import fr.eywek.header.settings.AppSettingsComponent;
import fr.eywek.header.settings.AppSettingsState;

import javax.swing.*;

public class InputService {
    private AppSettingsState appSettingsState;

    public InputService()
    {
        appSettingsState = AppSettingsState.getInstance();
    }
    public void setUsernameFromInput(Project project)
    {
        String message = "No username, please provide one:";
        String title = "username";
        Icon icon = Messages.getQuestionIcon();

        String inputDialog = Messages.showInputDialog(project, message, title, icon);
        appSettingsState.setUsername(inputDialog);
    }
}
