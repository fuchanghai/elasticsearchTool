package com.hito.dataware.scheduler.bean;/**
 * @title: EsBaseField
 * @projectName dataware-ship-service
 * @description: TODO
 * @author 伏长海
 * @date 2019/6/2510:35
 */

import lombok.Data;
import net.sf.json.JSONObject;
import org.elasticsearch.search.SearchHit;

import javax.persistence.Transient;

/**
 *@author fuchanghai
 *@description Es查询后的基础字段比如：_id _score
 *@date 2019/6/2510:35
 */
@Data
public   abstract class EsBaseField {

      private String iid;
      private Float score;
      @Transient
     private Location point;
      public  abstract  Object transfrom(JSONObject jsonObject, SearchHit hit);

      public void insertBase(SearchHit hit){
          this.setIid(hit.getId());
          this.setScore(hit.getScore());
      }
}
