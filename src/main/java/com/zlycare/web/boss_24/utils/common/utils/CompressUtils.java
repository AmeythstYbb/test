package com.zlycare.web.boss_24.utils.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.zip.*;

/**
 * Created by tonydeng on 14-8-25.
 */
public class CompressUtils {
    private static final Logger log = LoggerFactory.getLogger(CompressUtils.class);

    /**
     * GZIP压缩
     *
     * @param content
     * @return gzip compress byte array
     */
    public static byte[] gzip(byte[] content) {
        if (content == null || content.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = null;
        GZIPOutputStream gzip = null;
        try {
            out = new ByteArrayOutputStream();
            gzip = new GZIPOutputStream(out);
            gzip.write(content);
            gzip.finish();
            return out.toByteArray();
        } catch (IOException e) {
            if (log.isErrorEnabled())
                log.error("gzip compress error:'" + e.getMessage() + "'");
        } finally {
            if (gzip != null) {
                try {
                    gzip.close();
                } catch (IOException e) {
                    if (log.isErrorEnabled())
                        log.error("gzip compress GZIPOutputStream close error:'" + e.getMessage() + "'");
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    if (log.isErrorEnabled())
                        log.error("gzip compress ByteArrayOutputStream close error:'" + e.getMessage() + "'");
                }
            }
        }
        return null;
    }

    /**
     * GZIP解压缩
     *
     * @param compressed
     * @return gzip decompress byte array
     */
    public static byte[] ungzip(byte[] compressed) {
        if (compressed == null || compressed.length <= 0)
            return new byte[0];

        ByteArrayInputStream in = null;

        ByteArrayOutputStream out = null;

        GZIPInputStream gzip = null;

        try {
            in = new ByteArrayInputStream(compressed);
            out = new ByteArrayOutputStream();
            gzip = new GZIPInputStream(in);
            byte[] buffer = new byte[1024];
            int n;
            while ((n = gzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
            return out.toByteArray();
        } catch (IOException e) {
            if (log.isErrorEnabled())
                log.error("ungzip decompress error:'" + e.getMessage() + "'");
        } finally {
            if (gzip != null) {
                try {
                    gzip.close();
                } catch (IOException e) {
                    if (log.isErrorEnabled())
                        log.error("ungzip decompress GZIPInputStream close error:'" + e.getMessage() + "'");
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    if (log.isErrorEnabled())
                        log.error("ungzip decompress ByteArrayOutputStream close error:'" + e.getMessage() + "'");
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    if (log.isErrorEnabled())
                        log.error("ungzip decompress ByteArrayInputStream close error:'" + e.getMessage() + "'");
                }
            }
        }
        return new byte[0];
    }

    /**
     * ZIP压缩
     *
     * @param content
     * @return zip compress byte array
     */
    public static byte[] zip(byte[] content) {
        if (content == null || content.length <= 0)
            return null;

        ByteArrayOutputStream out = null;
        ZipOutputStream zip = null;
        try {
            out = new ByteArrayOutputStream();
            zip = new ZipOutputStream(out);
            zip.putNextEntry(new ZipEntry("0"));
            zip.write(content);
            zip.closeEntry();
            zip.finish();
            return out.toByteArray();
        } catch (IOException e) {
            if (log.isErrorEnabled())
                log.error("zip compress error:'" + e.getMessage() + "'");
        } finally {
            if (zip != null) {
                try {
                    zip.close();
                } catch (IOException e) {
                    if (log.isErrorEnabled())
                        log.error("zip compress ZipOutputStream close error:'" + e.getMessage() + "'");
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    if (log.isErrorEnabled())
                        log.error("zip compress ByteArrayOutputStream close error:'" + e.getMessage() + "'");
                }
            }
        }
        return null;
    }

    /**
     * 文件和目录压缩
     */
    public static void zipFile(String srcFile, String desFile) {
        try {
            zip(desFile, new File(srcFile));
        } catch (Exception e) {
            if (log.isErrorEnabled())
                log.error("zip " + srcFile + " to " + desFile + " error:'" + e.getMessage() + "'");
        }
    }

    private static void zip(String zipFileName, File inputFile) throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
        zip(out, inputFile, "");
        log.info("zip done");
        out.close();
    }

    private static void zip(ZipOutputStream out, File f, String base) throws Exception {
        if (f.isDirectory()) {
            File[] fl = f.listFiles();
            out.putNextEntry(new ZipEntry(base + "/"));
            base = base.length() == 0 ? "" : base + "/";
            for (int i = 0; i < fl.length; i++) {
                zip(out, fl[i], base + fl[i].getName());
            }
        } else {
            out.putNextEntry(new ZipEntry(base));
            FileInputStream in = new FileInputStream(f);
            int b;
            log.info(base);
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            in.close();
        }
    }

    /**
     * ZIP解压缩
     *
     * @param compressed
     * @return gzip decompress byte array
     */
    public static byte[] unzip(byte[] compressed) {
        if (compressed == null || compressed.length <= 0)
            return new byte[0];

        ByteArrayInputStream in = null;

        ByteArrayOutputStream out = null;

        ZipInputStream zip = null;

        try {
            in = new ByteArrayInputStream(compressed);
            out = new ByteArrayOutputStream();
            zip = new ZipInputStream(in);
            ZipEntry entry = zip.getNextEntry();
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = zip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            zip.closeEntry();
            return out.toByteArray();
        } catch (IOException e) {
            if (log.isErrorEnabled())
                log.error("unzip decompress error:'" + e.getMessage() + "'");
        } finally {
            if (zip != null) {
                try {
                    zip.close();
                } catch (IOException e) {
                    if (log.isErrorEnabled())
                        log.error("unzip decompress ZipInputStream close error:'" + e.getMessage() + "'");
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    if (log.isErrorEnabled())
                        log.error("unzip decompress ByteArrayOutputStream close error:'" + e.getMessage() + "'");
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    if (log.isErrorEnabled())
                        log.error("unzip decompress ByteArrayInputStream close error:'" + e.getMessage() + "'");
                }
            }
        }
        return new byte[0];
    }
}
