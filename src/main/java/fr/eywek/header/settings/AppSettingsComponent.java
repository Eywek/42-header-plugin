package fr.eywek.header.settings;

import com.intellij.ui.components.JBCheckBox;
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
    private final JCheckBox automaticAddCheckbox = new JBCheckBox();

    public AppSettingsComponent() {
        mainPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent(new JBLabel("Username: "), userNameText, 1, false)
                .addLabeledComponent(new JBLabel("Mail: "), mailText, 1, false)
                .addVerticalGap(3)
                .addLabeledComponent(new JBLabel("Automatically add header to .c and .h files"), automaticAddCheckbox, 1, false)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    public JPanel getPanel() {
        return (mainPanel);
    }

    public JComponent getPreferredFocusedComponent() {
        return (userNameText);
    }

    @NotNull
    public String getUserNameText() {
        return (userNameText.getText());
    }

    public void setUserNameText(@NotNull String newText) {
        userNameText.setText(newText);
    }

    @NotNull
    public String getMailText() {
        return (mailText.getText());
    }

    public void setMailText(@NotNull String newText) {
        mailText.setText(newText);
    }

    public boolean getAutomaticAddCheckbox()
    {
        return (automaticAddCheckbox.isSelected());
    }

    public void setAutomaticAddCheckbox(boolean newValue)
    {
       automaticAddCheckbox.setSelected(newValue);
    }
}
