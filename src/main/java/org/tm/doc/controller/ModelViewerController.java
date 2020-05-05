package org.tm.doc.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.kit.Kv;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.StrKit;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tm.common.util.MyDbMetaUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/*
    author: Timor
    date: 2020/3/24 0024
*/
@RequestMapping("/model/view")
public class ModelViewerController extends JbootController {
    private static final Logger logger = LoggerFactory.getLogger(ModelViewerController.class);
    private static boolean isFirstRequest = true;
    private static final String docFile = "/doc/model.md.html";
    private static final String docPath = PathKit.getWebRootPath() + docFile;

    public void index(){
        boolean refresh = getParaToBoolean("refresh", false);
        if(isFirstRequest || refresh || !isDocExist()){
            String db = "hotel";
            String template = "/doc/template/model.one.md.html";
            String html = renderToString(template, Kv.by("tableMetas", new MyDbMetaUtil().getAllTableMeta(db)));
            writeDoc(html);
            renderHtml(html);
        }else {
            redirect(getBaseUrl() + docFile);
        }
        isFirstRequest = false;
    }

    @ActionKey("/model/view/get")
    public void getByName(String db){
        String template = "/doc/template/model.one.md.html";
        if(StrKit.isBlank(db)){
            render(template);
            return;
        }

        String html = renderToString(template, Kv.by("tableMetas", new MyDbMetaUtil().getAllTableMeta(db)));
        renderHtml(html);
    }

    private boolean isDocExist(){
        return new File(docPath).exists();
    }

    private void writeDoc(String html){
        File file = new File(docPath);
        try {
            FileUtils.writeStringToFile(file, html, Charset.forName("UTF-8"));
        } catch (IOException e) {
            logger.warn("write doc to file error.");
        }
    }

}
