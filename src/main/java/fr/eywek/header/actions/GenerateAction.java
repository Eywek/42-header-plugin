package fr.eywek.header.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.vfs.VirtualFile;
import fr.eywek.header.services.CheckerService;
import fr.eywek.header.services.GeneratorService;
import fr.eywek.header.services.InputService;
import fr.eywek.header.settings.AppSettingsState;

import static com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE;

public class GenerateAction extends AnAction
{
    protected AppSettingsState state;
    protected GeneratorService generatorService;
    protected CheckerService checkerService;
    protected InputService inputService;

    public GenerateAction()
    {
       state = AppSettingsState.getInstance().getState();
       generatorService = new GeneratorService();
       checkerService = new CheckerService();
       inputService = new InputService();
    }

    @Override
    public void actionPerformed(AnActionEvent AnActionEvent)
    {
        VirtualFile file = AnActionEvent.getData(VIRTUAL_FILE);

        if (!this.checkerService.checkIsRightFileType(file)) return;
        if (this.checkerService.checkIfHasHeader(file)) return;

        if (!this.checkerService.checkIfHasUsername(state))
            inputService.setUsernameFromInput(AnActionEvent.getProject());

        String filename = file.getName();
        String username = state.username;
        String mail = state.mail;

        String header = this.generatorService.generateHeader(filename, username, mail);
        Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {
                AnActionEvent.getData(LangDataKeys.EDITOR).getDocument().insertString(0, header);
            }
        };
        WriteCommandAction.runWriteCommandAction(getEventProject(AnActionEvent), runnable);
    }
}