package com.cloudhub.cloudclient.utils;

public class SizeTools {
    public static String formatFileSize(double size) {
        String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int index = 0;
        while (size > 1024.0D) {
            size /= 1024.0D;
            ++index;
        }
        return String.format("%.2f", size) + units[index];
    }
}
