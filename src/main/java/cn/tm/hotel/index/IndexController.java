package cn.tm.hotel.index;

import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;

/*
    author: Timor
    date: 2020/3/21 0021
*/
@RequestMapping("//")
public class IndexController extends JbootController {
    public void index(){
        render("index_test.html");
    }
}

