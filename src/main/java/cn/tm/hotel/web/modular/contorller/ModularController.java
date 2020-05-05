package cn.tm.hotel.web.modular.contorller;

import cn.tm.hotel.web.article.service.ArticleService;
import cn.tm.hotel.web.modular.model.Modular;
import cn.tm.hotel.web.modular.service.ModularService;
import com.jfinal.core.paragetter.Para;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;
import org.tm.common.base.api.result.ApiResult;

/*
    author: Timor
    date: 2019/11/19 0019
*/
@RequestMapping("/web/modular")
public class ModularController extends JbootController {
    private static final ModularService service = new ModularService();
    private static final ArticleService articleService = new ArticleService();
    private ApiResult result;

    public void index(){
        render("index.html");
    }

    public void getModular(int id){
        result = ApiResult.success(service.getModular(id));
        renderJson(result);
    }
    public void add(@Para("") Modular modular) {
        result = ApiResult.success(service.add(modular));
        renderJson(result);
    }

    public void update(@Para("") Modular modular) {
        result = ApiResult.success(service.update(modular));
        renderJson(result);
    }
    public void delete(int id) {
        result = ApiResult.success(service.delete(id));
        renderJson(result);
    }

    public void list(int pageNumber, int pageSize){
        if (pageNumber < 1) pageNumber = 1;
        if (pageSize < 1 ) pageSize = 20;
        result = ApiResult.success(service.getAllModular(pageNumber, pageSize));
        renderJson(result);
    }

    public void articles(){
        forwardAction("/article/list");
    }

}
