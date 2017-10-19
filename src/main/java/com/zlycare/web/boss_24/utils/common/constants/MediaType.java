package com.zlycare.web.boss_24.utils.common.constants;

/**
 * Created by tonydeng on 15/1/19.
 */
public enum MediaType {
    /**
     * The value of a type or subtype wildcard: "*"
     */
    MEDIA_TYPE_WILDCARD("*"),

    /**
     * "application/xml"
     */
    APPLICATION_XML("application/xml"),

    /**
     * "application/atom+xml"
     */
    APPLICATION_ATOM_XML("application/atom+xml"),

    /**
     * "application/xhtml+xml"
     */
    APPLICATION_XHTML_XML("application/xhtml+xml"),

    /**
     * "application/svg+xml"
     */
    APPLICATION_SVG_XML("application/svg+xml"),

    /**
     * "application/json"
     */
    APPLICATION_JSON("application/json"),

    /**
     * "application/x-www-form-urlencoded"
     */
    APPLICATION_FORM_URLENCODED("application/x-www-form-urlencoded"),

    /**
     * "multipart/form-data"
     */
    MULTIPART_FORM_DATA("multipart/form-data"),

    /**
     * "application/octet-stream"
     */
    APPLICATION_OCTET_STREAM("application/octet-stream"),

    /**
     * "text/plain"
     */
    TEXT_PLAIN("text/plain"),
    /**
     * "image/jpeg"
     */
    IMAGE_JPEG("image/jpeg"),
    /**
     * "image/png"
     */
    IMAGE_PNG("image/png"),
    /**
     * "image/gif"
     */
    IMAGE_GIF("image/gif");
    /**
     * "text/xml"
     */
    public final static String TEXT_XML = "text/xml";
//
//    /**
//     * "text/html"
//     */
//    public final static String TEXT_HTML = "text/html";

    private String mediaType;
    private MediaType(String mediaType){
        this.mediaType = mediaType;
    }

    public String toString() {
        return this.mediaType;
    }

    public static MediaType getMediaType(String fileType){
        switch (fileType.toLowerCase()){
            case "jpg":
                return IMAGE_JPEG;
            case "jpeg":
                return IMAGE_JPEG;
            case "png":
                return IMAGE_PNG;
            case "gif":
                return IMAGE_GIF;
            case "xml":
                return APPLICATION_XML;
            case "atom":
                return APPLICATION_ATOM_XML;
            case "xhtml":
                return APPLICATION_XHTML_XML;
            case "svg":
                return APPLICATION_SVG_XML;
            case "json":
                return APPLICATION_JSON;
            case "txt":
                return TEXT_PLAIN;
            default:
                return APPLICATION_OCTET_STREAM;
        }
    }
}
