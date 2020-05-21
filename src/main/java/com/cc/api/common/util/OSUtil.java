package com.cc.api.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author teangtang
 */
public class OSUtil {
    public OSUtil() {
    }

    public static String getOSName() {
        return System.getProperty("os.name");
    }

    public static String getMac() {
        String OSName = getOSName();
        return OSName.toLowerCase().startsWith("win") ? getWinMac() : getLinuxMac();
    }

    public static String getWinMac() {
        String result = "";

        try {
            Process process = Runtime.getRuntime().exec("ipconfig /all");
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
            boolean var4 = true;

            String line;
            while((line = br.readLine()) != null) {
                int index = line.toLowerCase().indexOf("物理地址");
                if (index >= 0) {
                    index = line.indexOf(":");
                    if (index >= 0) {
                        result = line.substring(index + 1).trim();
                    }
                    break;
                }
            }
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        return result;
    }

    public static String getLinuxMac() {
        String result = "";

        try {
            String[] cmd = new String[]{"ifconfig"};
            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder sb = new StringBuilder();

            String line;
            while((line = br.readLine()) != null) {
                sb.append(line);
            }

            String str1 = sb.toString();
            String str2 = str1.split("ether")[1].trim();
            result = str2.split("txqueuelen")[0].trim();
            br.close();
        } catch (IOException var8) {
            var8.printStackTrace();
        } catch (InterruptedException var9) {
            var9.printStackTrace();
        }

        return result;
    }
}
