package fr.eywek.header.services;

import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import fr.eywek.header.settings.AppSettingsState;

public class CheckerService {

    public boolean checkIsRightFileType(VirtualFile file)
    {
        String filename = file.getName();
        String extension = file.getExtension();
        if (extension == null && !filename.contains("Makefile"))
            return (false);
        if (!filename.contains("Makefile") && !extension.equals("c") && !extension.equals("h"))
            return (false);

        return (true);
    }

    public boolean checkIfHasHeader(VirtualFile file)
    {
        String start = FileDocumentManager.getInstance().getDocument(file).getText(new TextRange(0, 5));
        if (start.equals("/* **") || start.equals("# ***"))
            return (true);

        return (false);
    }

    public boolean checkIfHasUsername(AppSettingsState state)
    {
        if (state.username.isEmpty() || state.username.isBlank() || state.username == null)
            return (false);

        return (true);
    }
}
