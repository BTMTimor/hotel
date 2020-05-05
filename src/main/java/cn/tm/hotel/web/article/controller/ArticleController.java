package cn.tm.hotel.web.article.controller;

import cn.tm.hotel.web.article.model.Article;
import cn.tm.hotel.web.article.service.ArticleService;
import com.alibaba.druid.util.StringUtils;
import com.jfinal.core.paragetter.Para;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;
import org.tm.common.base.api.result.ApiResult;

import java.util.Date;

/*
    author: Timor
    date: 2019/11/19 0019
*/
@RequestMapping("/web/article")
public class ArticleController extends JbootController {
    public static final String PAGE_NUMBER = "pageNumber";
    public static final String PAGE_SIZE = "pageSize";
    public static final String MID = "mid";
    public static final ArticleService service = new ArticleService();
    private ApiResult result;

    public void index(){
        render("index.html");
    }

    public void getArticle(int id) {
        String uid = getAttr("user_id");
        Article article = service.get(id);
        if(null == uid){
            article.remove(Article.UID, Article.ADD_TIME, Article.LAST_EDIT_TIME);
        }
        System.out.println(article);
        result = ApiResult.success(article);
        renderJson(result);
    }

    public void add(@Para("") Article article) {
        String uid = getAttr("user_id");
        article.setUid(uid);
        if (StringUtils.isEmpty(article.getAuthor())){
            // 设置发布人为发布文章管理员的署名
            // 略
        }

        result = ApiResult.success(service.add(article));
        renderJson(result);
    }

    public void update(@Para("") Article article) {
        System.out.println(article);
        String uid = getAttr("user_id");
        article.setUid(uid);
        article.setLastEditTime(new Date());

        result = ApiResult.success(service.update(article));
        renderJson(result);
    }

    /**
     * 假删除
     * @param id
     */
    public void delete(int id) {
        Article article = new Article();
        article.setId(id);
        article.setStatus(Article.Status.DELETE.getStatus());

        result = ApiResult.success(service.update(article));
        renderJson(result);
    }

    /**
     * 真删除
     * @param id
     */
    public void realDelete(int id) {
        result = ApiResult.success(service.delete(id));
        renderJson(result);
    }

    public void list(){
        int pageNumber = getParaToInt(PAGE_NUMBER, 1);
        int pageSize = getParaToInt(PAGE_SIZE, 10);
        if (pageNumber < 1) pageNumber = 1;
        if (pageSize < 1 ) pageSize = 20;
        if(pageSize > 500) pageSize = 500;
        Integer mid = getParaToInt(MID);
        result = ApiResult.success(service.getArticlesByPage(pageNumber, pageSize, mid));
        renderJson(result);
    }
}
