package com.aolan.b365.manager;

import android.content.Context;


import com.aolan.b365.app.App;
import com.aolan.b365.constants.DbConstant;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.model.ColumnsValue;
import com.litesuits.orm.db.model.ConflictAlgorithm;

import java.util.List;



/**
 * Created by Administrator on 2018/4/2.
 */

public class DbManager {
    public static DbManager getInstance() {
        return SimpleFactory.instance;
    }

    private LiteOrm liteOrm;

    /**
     * 得到数据库实例
     */
    public LiteOrm getLiteOrm() {
        Context context = App.getInstance();
        if (liteOrm == null) {
            liteOrm = LiteOrm.newSingleInstance(context, DbConstant.DB_NAME);
            liteOrm.setDebugged(true); // open the log
        }

        return liteOrm;
    }

    /**
     * 插入一条记录
     * @param t
     */
    public <T> long insert(T t) {
        return getLiteOrm().save(t);
    }

    /**
     * 查询某个id 数据
     * @param id
     * @param t
     * @param <T>
     * @return
     */
    public <T> T getQueryById(String id, Class<T> t){
        return getLiteOrm().queryById(id,t);
    }


    /**
     * 查询某个id 数据
     * @param id
     * @param t
     * @param <T>
     * @return
     */
    public <T> T getQueryById(long id,Class<T> t){
        return getLiteOrm().queryById(id,t);
    }


    /**
     * 插入所有记录
     * @param list
     */
    public <T> void insertAll(List<T> list) {
        getLiteOrm().save(list);
    }

    /**
     * 查询所有
     * @param cla
     * @return
     */
    public <T> List<T> getQueryAll(Class<T> cla) {
        return getLiteOrm().query(cla);
    }

    /**
     * 查询  某字段 等于 Value的值
     * @param cla
     * @param field
     * @param value
     * @return
     */
    public <T> List<T> getQueryByWhere(Class<T> cla, String field, Object[] value) {
        return getLiteOrm().<T>query(new QueryBuilder(cla).where(field + "=?", value));
    }

    /**
     * 查询  某字段 等于 Value的值  可以指定从1-20，就是分页
     * @param cla
     * @param field
     * @param value
     * @param start
     * @param length
     * @return
     */
    public <T> List<T> getQueryByWhereLength(Class<T> cla, String field, Object[] value, int start, int length) {
        return getLiteOrm().<T>query(new QueryBuilder(cla).where(field + "=?", value).limit(start, length));
    }

    /**
     * 模糊查询某字段
     * @param cla
     * @param field
     * @param value
     * @param <T>
     * @return
     */
    public <T> List<T> getQueryLike(Class<T> cla, String field, Object[] value){
        return getLiteOrm().<T>query(new QueryBuilder(cla).where(field+" LIKE ?",value));
    }

    /**
     * 模糊查询某字段 可以指定从1-20，就是分页
     * @param cla
     * @param field
     * @param value
     * @param <T>
     * @return
     */
    public <T> List<T> getQueryLike(Class<T> cla, String field, Object[] value, int start, int length){
        return getLiteOrm().<T>query(new QueryBuilder(cla).where(field+" LIKE ?",value).limit(start, length));
    }


    /**
     * 更新某个数据
     * @param cla
     * @param <T>
     */
    public <T>void  update(T cla){
        getLiteOrm().update(cla);
    }

    /**
     * 更新某一列
     * @param columns 　字段名
     * @param values　  值
     */
    public <T>void  updateOneColumn(T cla, String[] columns, Object[] values){
        getLiteOrm().update(cla,new ColumnsValue(columns,values), ConflictAlgorithm.Fail);
    }

    /**
     * 删除一个数据
     * @param t
     * @param <T>
     */
    public <T> void delete( T t){
        getLiteOrm().delete( t ) ;
    }

    /**
     * 删除一个表
     * @param cla
     * @param <T>
     */
    public <T> void delete( Class<T> cla ){
        getLiteOrm().delete( cla ) ;
    }

    /**
     * 删除集合中的数据
     * @param list
     * @param <T>
     */
    public <T> void deleteList( List<T> list ){
        getLiteOrm().delete( list ) ;
    }



    /**
     * 删除数据库
     */
    boolean removeAll() {
        boolean result = getLiteOrm().deleteDatabase();
        getLiteOrm().openOrCreateDatabase();
        return result;
    }

    private static class SimpleFactory {
        private static DbManager instance = new DbManager();
    }
}
