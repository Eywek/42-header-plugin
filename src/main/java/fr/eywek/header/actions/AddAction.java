package fr.eywek.header.actions;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileEvent;
import com.intellij.openapi.vfs.VirtualFileListener;
import com.intellij.openapi.vfs.VirtualFileManager;
import fr.eywek.header.services.CheckerService;
import fr.eywek.header.services.GeneratorService;
import fr.eywek.header.settings.AppSettingsState;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class AddAction implements ApplicationComponent {

    protected AppSettingsState state;
    protected GeneratorService generatorService;
    protected CheckerService checkerService;

    public AddAction()
    {
        state = AppSettingsState.getInstance().getState();
        generatorService = new GeneratorService();
        checkerService = new CheckerService();
    }

    @Override
    public void initComponent() {
        VirtualFileManager.getInstance().addVirtualFileListener(new VirtualFileListener() {
            @Override
            public void fileCreated(@NotNull VirtualFileEvent event) {
                VirtualFile file = event.getFile();

                if (!checkerService.checkIsRightFileType(file)) return;
                if (!state.automaticAdd) return;

                String filename = file.getName();
                String username = state.username;
                String mail = state.mail;

                String header = generatorService.generateHeader(filename, username, mail);
                if (header == null) return;

                Runnable runnable = () -> Objects.requireNonNull(FileDocumentManager.getInstance().getDocument(file)).insertString(0, header);
                WriteCommandAction.runWriteCommandAction(ProjectManager.getInstance().getOpenProjects()[0], runnable);
            }
        });
    }

    @Override
    public void disposeComponent() {
    }

    @Override
    @NotNull
    public String getComponentName() {
        return "Save";
    }
}