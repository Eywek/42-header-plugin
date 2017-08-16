package fr.eywek.header;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileEvent;
import com.intellij.openapi.vfs.VirtualFileListener;
import com.intellij.openapi.vfs.VirtualFileManager;
import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Save implements ApplicationComponent {
    public Save() {
    }

    @Override
    public void initComponent() {
        VirtualFileManager.getInstance().addVirtualFileListener(new VirtualFileListener() {
            public void contentsChanged(@NotNull VirtualFileEvent event) {
                VirtualFile file = event.getFile();
                if (!file.getExtension().equals("c"))
                    return;
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String filename = file.getName();
                while (filename.length() < 51)
                    filename += ' ';
                String header = "/*   Updated: " + dateFormat.format(date) + " by vtouffet         ###   ########.fr       */\n";

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        FileDocumentManager.getInstance().getDocument(file).replaceString(648, 648 + 81, header);
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