package org.example.demo1.mybatis.dao;
import org.example.demo1.mybatis.model.Publisher;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface PublisherMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PUBLISHER
     *
     * @mbg.generated Mon Apr 15 20:34:58 EEST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PUBLISHER
     *
     * @mbg.generated Mon Apr 15 20:34:58 EEST 2024
     */
    int insert(Publisher record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PUBLISHER
     *
     * @mbg.generated Mon Apr 15 20:34:58 EEST 2024
     */
    Publisher selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PUBLISHER
     *
     * @mbg.generated Mon Apr 15 20:34:58 EEST 2024
     */
    List<Publisher> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PUBLISHER
     *
     * @mbg.generated Mon Apr 15 20:34:58 EEST 2024
     */
    int updateByPrimaryKey(Publisher record);
}