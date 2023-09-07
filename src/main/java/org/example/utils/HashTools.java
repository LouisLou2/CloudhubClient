package org.example.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class HashTools {
    public static String getFileHashCode(String filePath) {
        return calculateMD5(filePath);
    }
    public static String calculateMD5(String filePath) {
        try (InputStream is = new FileInputStream(filePath)) {
            return DigestUtils.md5Hex(is);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String calculateSHA1(String filePath) {
        try (InputStream is = new FileInputStream(filePath)) {
            return DigestUtils.sha1Hex(is);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String calculateSHA256(String filePath) {
        try (InputStream is = new FileInputStream(filePath)) {
            return DigestUtils.sha256Hex(is);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
