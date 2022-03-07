package fr.eywek.header.services;

import com.intellij.openapi.editor.Document;
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
        if (extension == null) return (false);
        return filename.contains("Makefile") || extension.equals("c") || extension.equals("h");
    }

    public boolean checkIfHasHeader(VirtualFile file)
    {
        Document document = FileDocumentManager.getInstance().getDocument(file);
        if (document == null) return (false);
        if (document.getText().length() < 5) return (false);

        String start = document.getText(new TextRange(0, 5));
        return start.equals("/* **") || start.equals("# ***");
    }

    public boolean checkIfHasUsername(AppSettingsState state)
    {
        return !state.username.isEmpty() && !state.username.isBlank() && state.username != null;
    }
}
