package com.zlycare.web.boss_24.componet.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tonydeng on 14-8-4.
 */
public class UTF8StringHttpMessageConverter extends StringHttpMessageConverter {
    private static final Logger log = LoggerFactory.getLogger(UTF8StringHttpMessageConverter.class);
    private static final MediaType utf8 = new MediaType("text", "plain", Charset.forName("UTF-8"));

    private boolean writeAcceptCharset = true;

    @Override
    protected MediaType getDefaultContentType(String s) throws IOException {
        return utf8;
    }

    @Override
    protected List<Charset> getAcceptedCharsets() {
        return Arrays.asList(utf8.getCharSet());
    }

//    @Override
//    protected void writeInternal(String s, HttpOutputMessage outputMessage) throws IOException {
//        if(this.isWriteAcceptCharset()){
//            outputMessage.getHeaders().setAcceptCharset(getAcceptedCharsets());
//        }
//        Charset charset = utf8.getCharSet();
//        FileCopyUtils.copy(s,new OutputStreamWriter(outputMessage.getBody(),charset));
//    }
    //    @Override
//    protected void writeInternal(String s, HttpOutputMessage outputMessage) throws IOException {
//        if(this.writeAcceptCharset){
//            outputMessage.getHeaders().setAcceptCharset(getAcceptedCharsets());
//        }
//        if(log.isDebugEnabled())
//            log.debug("s:'"+s+"'");
//
//        Charset charset = utf8.getCharSet();
//
//        OutputStream out = outputMessage.getBody();
//        byte[] bytes = s.getBytes(charset);
//        String s2 = new String(bytes);
//        if(log.isDebugEnabled())
//            log.debug("s2:'"+s2+"'");
//
//        out.write(bytes);
//    }

//    @Override
//    protected Long getContentLength(String s, MediaType contentType) {
//        Long length =  super.getContentLength(s, contentType);
//        if(log.isDebugEnabled())
//            log.debug("length:'"+length+"'");
//        return new Long(s.length()*2);
//    }

    public boolean isWriteAcceptCharset() {
        return writeAcceptCharset;
    }

    public void setWriteAcceptCharset(boolean writeAcceptCharset) {
        this.writeAcceptCharset = writeAcceptCharset;
    }
}
