package fr.eywek.header.services;

import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import fr.eywek.header.settings.AppSettingsState;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE;

public class GeneratorService {

    public String generateHeader(String filename, String username, String mail)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        while (filename.length() < 51)
            filename += ' ';
        String user = "By: " + username + " " + "<" + mail + ">";
        while (user.length() < 47)
            user += ' ';
        String user2 = "by " + username;
        while (user2.length() < 21)
            user2 += ' ';
        String user3 = "by " + username;
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

        return (header);
    }

    public String changeHeader(VirtualFile file, String username)
    {
        String filename = file.getName();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        while (filename.length() < 51)
            filename += ' ';
        String user = "by " + username;
        while (user.length() < 20)
            user += ' ';
        String header;
        if (filename.contains("Makefile"))
            header = "#    Updated: " + dateFormat.format(date) + " " + user + "###   ########.fr        #\n";
        else
            header = "/*   Updated: " + dateFormat.format(date) + " " + user + "###   ########.fr       */\n";

        return (header);
    }
}
