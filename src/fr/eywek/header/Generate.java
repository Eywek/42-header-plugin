package fr.eywek.header;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.command.WriteCommandAction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE;

public class Generate extends AnAction
{
    @Override
    public void actionPerformed(AnActionEvent AnActionEvent)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        VirtualFile file = AnActionEvent.getData(VIRTUAL_FILE);
        String filename = file.getName();
        while (filename.length() < 51)
            filename += ' ';
        String user = "By: " + System.getenv("USER") + " " + "<" + System.getenv("MAIL") + ">";
        while (user.length() < 47)
            user += ' ';
        String user2 = "by " + System.getenv("USER");
        while (user2.length() < 21)
            user2 += ' ';
        String user3 = "by " + System.getenv("USER");
        while (user3.length() < 20)
            user3 += ' ';
        String startComment = "/*";
        String endComment = "*/";
        if (filename.contains("Makefile"))
        {
            startComment = "#";
            endComment = "#";
        }
        String header = startComment + " " + (startComment.length() == 1 ? "*" : "") + "**************************************************************************" + (endComment.length() == 1 ? "*" : "") + " " + endComment + "\n" +
                startComment + (startComment.length() == 1 ? " " : "") + "                                                                            " + (endComment.length() == 1 ? " ": "") + endComment + "\n" +
                startComment + (startComment.length() == 1 ? " " : "") + "                                                        :::      ::::::::   " + (endComment.length() == 1 ? " ": "") + endComment + "\n" +
                startComment + (startComment.length() == 1 ? " " : "") + "   " + filename + ":+:      :+:    :+:   " + (endComment.length() == 1 ? " ": "") + endComment + "\n" +
                startComment + (startComment.length() == 1 ? " " : "") + "                                                    +:+ +:+         +:+     " + (endComment.length() == 1 ? " ": "") + endComment + "\n" +
                startComment + (startComment.length() == 1 ? " " : "") + "   " + user + "+#+  +:+       +#+        " + (endComment.length() == 1 ? " ": "") + endComment + "\n" +
                startComment + (startComment.length() == 1 ? " " : "") + "                                                +#+#+#+#+#+   +#+           " + (endComment.length() == 1 ? ' ': "") + endComment + "\n" +
                startComment + (startComment.length() == 1 ? " " : "") + "   Created: " + dateFormat.format(date) + " " + user2 + "#+#    #+#             " + (endComment.length() == 1 ? " ": "") + endComment + "\n" +
                startComment + (startComment.length() == 1 ? " " : "") + "   Updated: " + dateFormat.format(date) + " " + user3 + "###   ########.fr       " + (endComment.length() == 1 ? " ": "") + endComment + "\n" +
                startComment + (startComment.length() == 1 ? ' ' : "") + "                                                                            " + (endComment.length() == 1 ? " ": "") + endComment + "\n" +
                startComment + " " + (startComment.length() == 1 ? "*" : "") + "**************************************************************************" + (endComment.length() == 1 ? "*" : "") + " " + endComment + "\n";

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
