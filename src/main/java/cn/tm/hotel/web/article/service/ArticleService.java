package cn.tm.hotel.web.article.service;

import cn.tm.hotel.web.article.model.Article;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.ehcache.CacheName;

/*
    author: Timor
    date: 2019/11/19 0019
*/
public class ArticleService {
    public static final Article dao = new Article();

    @CacheName("article")
    public Article get(int id) {
        return dao.findById(id);
    }
    public boolean add(Article article) {
        return article.save();
    }
    public boolean update(Article article) {
        return article.update();
    }
    public boolean delete(int id) {
        return dao.deleteById(id);
    }

    public Page<Article> getArticlesByPage(int pageNumber, int pageSize, Integer mid){
        String sql = "from article";
        if(null != mid){
            sql += " where mid = ?";
            return dao.paginate(pageNumber, pageSize, "select * ", sql, mid);
        }else {
            return dao.paginate(pageNumber, pageSize, "select * ", sql);
        }
    }
}
