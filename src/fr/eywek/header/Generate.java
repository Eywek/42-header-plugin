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

public class Generate extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent AnActionEvent) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        VirtualFile file = AnActionEvent.getData(VIRTUAL_FILE);
        String filename = file.getName();
        while (filename.length() < 51)
            filename += ' ';
        String header = "/* ************************************************************************** */\n" +
                "/*                                                                            */\n" +
                "/*                                                        :::      ::::::::   */\n" +
                "/*   " + filename + ":+:      :+:    :+:   */\n" +
                "/*                                                    +:+ +:+         +:+     */\n" +
                "/*   By: vtouffet <marvin@42.fr>                    +#+  +:+       +#+        */\n" +
                "/*                                                +#+#+#+#+#+   +#+           */\n" +
                "/*   Created: " + dateFormat.format(date) + " by vtouffet          #+#    #+#             */\n" +
                "/*   Updated: " + dateFormat.format(date) + " by vtouffet         ###   ########.fr       */\n" +
                "/*                                                                            */\n" +
                "/* ************************************************************************** */\n";

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                AnActionEvent.getData(LangDataKeys.EDITOR).getDocument().replaceString(0, 891, header);
            }
        };
        WriteCommandAction.runWriteCommandAction(getEventProject(AnActionEvent), runnable);
    }
}
