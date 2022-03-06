package fr.eywek.header.settings;

import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Supports creating and managing a {@link JPanel} for the Settings Dialog.
 */
public class AppSettingsComponent {

    private final JPanel mainPanel;
    private final JBTextField userNameText = new JBTextField();
    private final JBTextField mailText = new JBTextField();

    public AppSettingsComponent() {
        mainPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent(new JBLabel("username: "), userNameText, 1, false)
                .addLabeledComponent(new JBLabel("mail: "), mailText, 1, false)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    public JPanel getPanel() {
        return mainPanel;
    }

    public JComponent getPreferredFocusedComponent() {
        return userNameText;
    }

    @NotNull
    public String getUserNameText() {
        return userNameText.getText();
    }

    public void setUserNameText(@NotNull String newText) {
        userNameText.setText(newText);
    }

    @NotNull
    public String getMailText() {
        return mailText.getText();
    }

    public void setMailText(@NotNull String newText) {
        mailText.setText(newText);
    }
}
