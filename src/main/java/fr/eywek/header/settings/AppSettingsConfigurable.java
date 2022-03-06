package fr.eywek.header.settings;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Provides controller functionality for application settings.
 */
public class AppSettingsConfigurable implements Configurable {

    private AppSettingsComponent appSettingsComponent;

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "42 Header configuration";
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return appSettingsComponent.getPreferredFocusedComponent();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        appSettingsComponent = new AppSettingsComponent();
        return appSettingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        AppSettingsState settings = AppSettingsState.getInstance();
        boolean modified = !appSettingsComponent.getUserNameText().equals(settings.username);
        modified |= !appSettingsComponent.getMailText().equals(settings.mail);
        return modified;
    }

    @Override
    public void apply() {
        AppSettingsState settings = AppSettingsState.getInstance();
        settings.username = appSettingsComponent.getUserNameText();
        settings.mail = appSettingsComponent.getMailText();
    }

    @Override
    public void reset() {
        AppSettingsState settings = AppSettingsState.getInstance();
        appSettingsComponent.setUserNameText(settings.username);
        appSettingsComponent.setMailText(settings.mail);
    }

    @Override
    public void disposeUIResources() {
        appSettingsComponent = null;
    }
}
