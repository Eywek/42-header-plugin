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

public class SaveAction implements ApplicationComponent {

    protected AppSettingsState state;
    protected GeneratorService generatorService;
    protected CheckerService checkerService;

    public SaveAction()
    {
        state = AppSettingsState.getInstance().getState();
        generatorService = new GeneratorService();
        checkerService = new CheckerService();
    }

    @Override
    public void initComponent() {
        VirtualFileManager.getInstance().addVirtualFileListener(new VirtualFileListener() {
            public void contentsChanged(@NotNull VirtualFileEvent event) {
                VirtualFile file = event.getFile();
                String username = state.username;

                if (!checkerService.checkIsRightFileType(file)) return;
                if (!checkerService.checkIfHasHeader(file)) return;

                String header = generatorService.changeHeader(file, username);
                if (header == null) return;;

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        FileDocumentManager.getInstance().getDocument(file).replaceString(648, 648 + header.length(), header);
                    }
                };
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