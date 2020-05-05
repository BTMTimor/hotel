package cn.tm.hotel.web.modular.service;

import cn.tm.hotel.web.modular.model.Modular;
import com.jfinal.plugin.activerecord.Page;

/*
    author: Timor
    date: 2019/11/19 0019
*/
public class ModularService {
    private static final Modular dao = new Modular();

    public Modular getModular(int id){
        return dao.findById(id);
    }
    public boolean add(Modular modular){
        return modular.save();
    }
    public boolean update(Modular modular){
        return modular.update();
    }
    public boolean delete(int id){
        return dao.deleteById(id);
    }

    public Page<Modular> getAllModular(int pageNumber, int pageSize){
        return dao.paginate(pageNumber, pageSize, "select * ", "from modular");
    }
}
