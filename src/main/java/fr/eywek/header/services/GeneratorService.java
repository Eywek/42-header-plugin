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

    protected String getStartComment(String filename)
    {
        return (filename.contains("Makefile") ? "#" : "/*");
    }

    protected String getEndComment(String filename)
    {
        return (filename.contains("Makefile") ? "#" : "*/");
    }

    public String generateHeader(String filename, String username, String mail)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        StringBuilder filenameBuilder = new StringBuilder(filename);
        while (filenameBuilder.length() < 51)
            filenameBuilder.append(' ');
        filename = filenameBuilder.toString();
        StringBuilder user = new StringBuilder("By: " + username + " " + "<" + mail + ">");
        while (user.length() < 47)
            user.append(' ');
        StringBuilder user2 = new StringBuilder("by " + username);
        while (user2.length() < 21)
            user2.append(' ');
        StringBuilder user3 = new StringBuilder("by " + username);
        while (user3.length() < 20)
            user3.append(' ');
        String startComment = this.getStartComment(filename);
        String endComment = this.getEndComment(filename);

        return (startComment + " " + (startComment.length() == 1 ? "*" : "") + "**************************************************************************" + (endComment.length() == 1 ? "*" : "") + " " + endComment + "\n" +
                startComment + (startComment.length() == 1 ? " " : "") + "                                                                            " + (endComment.length() == 1 ? " ": "") + endComment + "\n" +
                startComment + (startComment.length() == 1 ? " " : "") + "                                                        :::      ::::::::   " + (endComment.length() == 1 ? " ": "") + endComment + "\n" +
                startComment + (startComment.length() == 1 ? " " : "") + "   " + filename + ":+:      :+:    :+:   " + (endComment.length() == 1 ? " ": "") + endComment + "\n" +
                startComment + (startComment.length() == 1 ? " " : "") + "                                                    +:+ +:+         +:+     " + (endComment.length() == 1 ? " ": "") + endComment + "\n" +
                startComment + (startComment.length() == 1 ? " " : "") + "   " + user + "+#+  +:+       +#+        " + (endComment.length() == 1 ? " ": "") + endComment + "\n" +
                startComment + (startComment.length() == 1 ? " " : "") + "                                                +#+#+#+#+#+   +#+           " + (endComment.length() == 1 ? ' ': "") + endComment + "\n" +
                startComment + (startComment.length() == 1 ? " " : "") + "   Created: " + dateFormat.format(date) + " " + user2 + "#+#    #+#             " + (endComment.length() == 1 ? " ": "") + endComment + "\n" +
                startComment + (startComment.length() == 1 ? " " : "") + "   Updated: " + dateFormat.format(date) + " " + user3 + "###   ########.fr       " + (endComment.length() == 1 ? " ": "") + endComment + "\n" +
                startComment + (startComment.length() == 1 ? ' ' : "") + "                                                                            " + (endComment.length() == 1 ? " ": "") + endComment + "\n" +
                startComment + " " + (startComment.length() == 1 ? "*" : "") + "**************************************************************************" + (endComment.length() == 1 ? "*" : "") + " " + endComment + "\n");
    }

    public String updateLineUpdated(VirtualFile file, String username)
    {
        StringBuilder filename = new StringBuilder(file.getName());

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        while (filename.length() < 51)
            filename.append(' ');
        StringBuilder user = new StringBuilder("by " + username);
        while (user.length() < 20)
            user.append(' ');
        String header;
        if (filename.toString().contains("Makefile"))
            header = "#    Updated: " + dateFormat.format(date) + " " + user + "###   ########.fr        #\n";
        else
            header = "/*   Updated: " + dateFormat.format(date) + " " + user + "###   ########.fr       */\n";

        return (header);
    }

    public String updateLineFilename(VirtualFile file)
    {
        StringBuilder filename = new StringBuilder(file.getName());
        String startComment = this.getStartComment(filename.toString());
        String endComment = this.getEndComment(filename.toString());

        while (filename.length() < 51)
            filename.append(' ');

        return (startComment + (startComment.length() == 1 ? " " : "") + "   " + filename + ":+:      :+:    :+:   " + (endComment.length() == 1 ? " ": "") + endComment + "\n");
    }
}
