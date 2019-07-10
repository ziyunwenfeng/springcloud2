package dao;

import entity.Cou;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* Author wenfeng
* Date  2019-07-09
*/
@Mapper
public interface CouDao {

    public Cou get(int id);

    public List<Cou> findList(Cou cou);

    public List<Cou> findAllList();

    public int insert(Cou cou);

    public int insertBatch(List<Cou> cous);

    public int update(Cou cou);

    public int delete(Cou cou);

    public int deleteById(int id);

}