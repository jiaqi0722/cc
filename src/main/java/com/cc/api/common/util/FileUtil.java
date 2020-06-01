package com.cc.api.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author 特昂唐  2020/6/1  06:01
 * describe: FileUtil
 */
public class FileUtil {
    private String fFname = null;
    List<String> fileList = new ArrayList();

    public FileUtil() {
    }

    public static void upFile(File uploadFile, String fileName, String filePath) {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        FileInputStream is = null;
        BufferedInputStream bis = null;
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }

        File f = new File(filePath + "/" + fileName);

        try {
            is = new FileInputStream(uploadFile);
            bis = new BufferedInputStream(is);
            fos = new FileOutputStream(f);
            bos = new BufferedOutputStream(fos);
            byte[] bt = new byte[4096];

            int len;
            while((len = bis.read(bt)) > 0) {
                bos.write(bt, 0, len);
            }
        } catch (Exception var19) {
            var19.printStackTrace();
        } finally {
            try {
                if (null != bos) {
                    bos.close();
                    bos = null;
                }

                if (null != fos) {
                    fos.close();
                    fos = null;
                }

                if (null != is) {
                    is.close();
                    is = null;
                }

                if (null != bis) {
                    bis.close();
                    bis = null;
                }
            } catch (Exception var18) {
                var18.printStackTrace();
            }

        }

    }

    public static void upFile(InputStream is, String fileName, String filePath) {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }

        File f = new File(filePath + "/" + fileName);

        try {
            bis = new BufferedInputStream(is);
            fos = new FileOutputStream(f);
            bos = new BufferedOutputStream(fos);
            byte[] bt = new byte[4096];

            int len;
            while((len = bis.read(bt)) > 0) {
                bos.write(bt, 0, len);
            }
        } catch (Exception var18) {
            var18.printStackTrace();
        } finally {
            try {
                if (null != bos) {
                    bos.close();
                    bos = null;
                }

                if (null != fos) {
                    fos.close();
                    fos = null;
                }

                if (null != is) {
                    is.close();
                    is = null;
                }

                if (null != bis) {
                    bis.close();
                    bis = null;
                }
            } catch (Exception var17) {
                var17.printStackTrace();
            }

        }

    }

    public static void downloadFile(HttpServletRequest request, HttpServletResponse response, String downloadFile, String fileName) {
        BufferedInputStream bis = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedOutputStream bos = null;

        try {
            File file = new File(downloadFile);
            is = new FileInputStream(file);
            os = response.getOutputStream();
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(os);
            if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
                fileName = new String(fileName.getBytes("GB2312"), "ISO-8859-1");
            } else {
                fileName = URLEncoder.encode(fileName, "UTF-8");
                fileName = new String(fileName.getBytes("UTF-8"), "GBK");
            }

            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            byte[] buffer = new byte[4096];

            int bytesRead;
            while((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
        } catch (Exception var19) {
            var19.printStackTrace();
        } finally {
            try {
                if (null != bis) {
                    bis.close();
                    bis = null;
                }

                if (null != bos) {
                    bos.close();
                    bos = null;
                }

                if (null != is) {
                    is.close();
                    is = null;
                }

                if (null != os) {
                    os.close();
                    os = null;
                }
            } catch (Exception var18) {
                var18.printStackTrace();
            }

        }

    }

    public static void downloadFile(HttpServletResponse response, String downloadFile, String showFileName) {
        BufferedInputStream bis = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedOutputStream bos = null;

        try {
            File file = new File(downloadFile);
            String fileName = file.getName();
            is = new FileInputStream(file);
            os = response.getOutputStream();
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(os);
            fileName = URLEncoder.encode(showFileName, "UTF-8");
            fileName = new String(fileName.getBytes("UTF-8"), "GBK");
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            byte[] buffer = new byte[1024];

            int bytesRead;
            while((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
        } catch (Exception var18) {
            var18.printStackTrace();
            throw new RuntimeException(var18.getMessage());
        } finally {
            try {
                if (null != bis) {
                    bis.close();
                    bis = null;
                }

                if (null != bos) {
                    bos.close();
                    bos = null;
                }

                if (null != is) {
                    is.close();
                    is = null;
                }

                if (null != os) {
                    os.close();
                    os = null;
                }
            } catch (Exception var17) {
                var17.printStackTrace();
                throw new RuntimeException(var17.getMessage());
            }

        }

    }

    public String getFileName(String cfgFilename) {
        if (this.fFname == null) {
            String[] str = cfgFilename.split("/");
            String filename = str[str.length - 1];
            this.fFname = filename;
        }

        return this.fFname;
    }

    public static int writeFile(String fileName, byte[] content) {
        System.out.println("开始写文件:[" + fileName + "]");
        File file = new File(fileName);
        File fileparent = file.getParentFile();
        if (!fileparent.exists()) {
            System.out.println("文件夹不存在，创建该目录");
            fileparent.mkdirs();
        }

        FileOutputStream os = null;

        label77: {
            byte var6;
            try {
                os = new FileOutputStream(fileName);
                os.write(content);
                os.flush();
                break label77;
            } catch (Exception var16) {
                System.out.println("写文件:[" + fileName + "]异常，异常信息为:[" + var16.getMessage() + "]");
                var6 = -1;
            } finally {
                try {
                    if (null != os) {
                        os.close();
                    }
                } catch (IOException var15) {
                }

            }

            return var6;
        }

        os = null;
        System.out.println("写文件:[" + fileName + "]完成!");
        return 0;
    }

    public static byte[] readFile(String fileName) {
        FileInputStream fis = null;
        System.out.println("开始读文件:[" + fileName + "]");
        byte[] buffer = null;

        try {
            fis = new FileInputStream(fileName);
            buffer = new byte[fis.available()];
            fis.read(buffer);
        } catch (Exception var12) {
            System.out.println("读文件[" + fileName + "]失败 " + var12.toString());
        } finally {
            try {
                if (null != fis) {
                    fis.close();
                }
            } catch (IOException var11) {
            }

        }

        fis = null;
        System.out.println("读文件[" + fileName + "]成功");
        System.out.println("文件[" + fileName + "]转为字节数组");
        return buffer;
    }

    public static String ReadFileContent(String filePath) {
        File file = new File(filePath);
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gbk"));
        } catch (UnsupportedEncodingException var6) {
            var6.printStackTrace();
        } catch (FileNotFoundException var7) {
            var7.printStackTrace();
        }

        String str = null;
        StringBuffer Buff = new StringBuffer();

        try {
            while((str = br.readLine()) != null) {
                Buff.append(str + "\r\n");
            }
        } catch (IOException var8) {
            var8.printStackTrace();
        }

        return Buff.toString();
    }

    public List<String> getFiles(String filePath) {
        System.out.println("开始遍历[" + filePath + "]目录下的文件");
        File root = new File(filePath);
        File[] files = root.listFiles();
        if (files != null && files.length != 0) {
            File[] var4 = files;
            int var5 = files.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                File file = var4[var6];
                if (file.isDirectory()) {
                    this.getFiles(file.getAbsolutePath());
                } else {
                    this.fileList.add(filePath + File.separator + file.getName());
                    System.out.println(file.getName());
                }
            }

            System.out.println("[" + filePath + "]目录遍历完成，里面包括[" + this.fileList.size() + "]个文件");
            return this.fileList;
        } else {
            System.out.println("[" + filePath + "]目录下无文件，不继续后续操作");
            return null;
        }
    }

    public static void decompress(String zip, String destPath) throws Exception {
        if (zip != null && !zip.isEmpty() && destPath != null && !destPath.isEmpty()) {
            File zipFile = new File(zip);
            if (!zipFile.exists()) {
                throw new FileNotFoundException("zip file is not exists");
            } else {
                File destFolder = new File(destPath);
                if (!destFolder.exists() && !destFolder.mkdirs()) {
                    throw new FileNotFoundException("destPath mkdirs is failed");
                } else {
                    ZipInputStream zis = null;
                    BufferedOutputStream bos = null;

                    try {
                        zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(zip)));

                        while(true) {
                            ZipEntry ze;
                            while((ze = zis.getNextEntry()) != null) {
                                String filePath = destPath + File.separator + ze.getName();
                                if (ze.isDirectory()) {
                                    (new File(filePath)).mkdirs();
                                } else {
                                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                    byte[] buffer = new byte[1024];

                                    int count;
                                    while((count = zis.read(buffer)) != -1) {
                                        baos.write(buffer, 0, count);
                                    }

                                    byte[] bytes = baos.toByteArray();
                                    File entryFile = new File(filePath);
                                    if (!entryFile.getParentFile().exists() && !entryFile.getParentFile().mkdirs()) {
                                        throw new FileNotFoundException("zip entry mkdirs is failed");
                                    }

                                    bos = new BufferedOutputStream(new FileOutputStream(entryFile));
                                    bos.write(bytes);
                                    bos.flush();
                                }
                            }

                            return;
                        }
                    } finally {
                        closeQuietly(zis);
                        closeQuietly(bos);
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("zip or destPath is illegal");
        }
    }

    public static void compress(String srcPath, String destPath) throws Exception {
        if (srcPath != null && !srcPath.isEmpty() && destPath != null && !destPath.isEmpty()) {
            File srcFile = new File(srcPath);
            if (!srcFile.exists()) {
                throw new FileNotFoundException("srcPath file is not exists");
            } else {
                File destFile = new File(destPath);
                if (destFile.exists() && !destFile.delete()) {
                    throw new IllegalArgumentException("destFile is exist and do not delete.");
                } else {
                    CheckedOutputStream cos = null;
                    ZipOutputStream zos = null;

                    try {
                        cos = new CheckedOutputStream(new FileOutputStream(destPath), new CRC32());
                        zos = new ZipOutputStream(cos);
                        zos.setLevel(9);
                        if (srcFile.isFile()) {
                            compressFile("", srcFile, zos);
                        } else if (srcFile.isDirectory()) {
                            compressFolder("", srcFile, zos);
                        }
                    } finally {
                        closeQuietly(zos);
                    }

                }
            }
        } else {
            throw new IllegalArgumentException("srcPath or destPath is illegal");
        }
    }

    private static void compressFolder(String prefix, File srcFolder, ZipOutputStream zos) throws IOException {
        String new_prefix = prefix + srcFolder.getName() + "/";
        File[] files = srcFolder.listFiles();
        if (files.length == 0) {
            compressFile(prefix, srcFolder, zos);
        } else {
            File[] var5 = files;
            int var6 = files.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                File file = var5[var7];
                if (file.isFile()) {
                    compressFile(new_prefix, file, zos);
                } else if (file.isDirectory()) {
                    compressFolder(new_prefix, file, zos);
                }
            }
        }

    }

    private static void compressFile(String prefix, File src, ZipOutputStream zos) throws IOException {
        String relativePath = prefix + src.getName();
        if (src.isDirectory()) {
            relativePath = relativePath + "/";
        }

        ZipEntry entry = new ZipEntry(relativePath);
        zos.putNextEntry(entry);
        FileInputStream is = null;

        try {
            if (src.isFile()) {
                is = new FileInputStream(src);
                byte[] buffer = new byte[8192];
                boolean var7 = false;

                int len;
                while((len = is.read(buffer)) != -1) {
                    zos.write(buffer, 0, len);
                }
            }

            zos.closeEntry();
        } finally {
            closeQuietly(is);
        }

    }

    private static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException var2) {
        }

    }
}
