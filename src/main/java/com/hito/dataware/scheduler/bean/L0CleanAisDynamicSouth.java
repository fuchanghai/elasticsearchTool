package com.hito.dataware.scheduler.bean;

import lombok.Data;
import net.sf.json.JSONObject;
import org.elasticsearch.search.SearchHit;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

@Table(name = "l0_clean_ais_dynamic_unknown")
@Data
@Document(indexName = "myidentitydynamic",type = "dynamic")
public class L0CleanAisDynamicSouth extends EsBaseField implements Serializable {


    @Column(name = "id")
    private Long id;
    /**
     * 在AIS消息中，使用30bit来表示  
     */


    @Column(name = "MMSI")
    private Integer mmsi;

    @Column(name = "Record_Datetime")
    private Integer recordDatetime;

    /**
     * 除以600000得度数。除完以后，范围是-180~180，181：不可用 182：非法  （±180°，东=正，西=负。  181°=不可用=默认值）  
     */
    @Column(name = "Longitude")
    private Integer longitude;

    /**
     * 除以600000得度数。范围是-90~90，91:不可用 92：是非法  （±90°，北=正，南=负。91°=不可用=默认值）  
     */
    @Column(name = "Latitude")
    private Integer latitude;

    /**
     * 船航向  范围是0-3600，超出为非法  COG，以1/10°为单位（0-3599）。3600 =不可用=默认值。3601：非法  
     */
    @Column(name = "Direction")
    private Short direction;

    /**
     * 船首向。范围是0-359以及511，512：非法  度（0-359）（511表明不可用=默认值）  
     */
    @Column(name = "Heading")
    private Short heading;

    /**
     * 除以10得节。范围0-1023，1024：非法  1 023=不可用，1 022=102.2节或更快  
     */
    @Column(name = "Speed")
    private Short speed;

    /**
     * 范围0-15. 16：非法。  船状态。0=机动船在航（under way using engine），1=锚泊，2=船舶失控（主机舵失灵，或者恶劣天气造成船舶无法正常行驶,由于某种情况，不能按本规则各条的要求进行操纵，因而不能给他船让路的船舶。），3=船舶操作受限（如航道挖泥船在港区作业，指由于工作性质，使其按本规则要  
     */
    @Column(name = "Status")
    private Byte status;

    /**
     * 传感器输出的转向率。  正常范围-708到708表示右转708°/min到左转708°/min之间  值为正负709时，分别表示右转和左转速度大于5°/30s,没装转向传感器Rate of Turn Indicator  值为-710时，表示没有转向信息(默认值)  值为-711时，表示转向信息非法    
     */
    @Column(name = "ROT_Sensor")
    private Short rotSensor;

    @Column(name = "AIS_Source")
    private Short aisSource;

/*    @Column(name = "created_time")
    private Long createdTime;*/


    @Transient
    private String shipName;

    /**
     * 呼号
     7*6比特ASCII字符，@@@@@@@=不可用=默认值
     */
    @Transient
    private String callSign;

    /**
     * IMO编号。范围0-999999999，1000000000为无效
     1-999999999；0=不可用=默认值 – 不适用于SAR航空器
     */
    @Transient
    private Integer imoNumber;

    @Transient
    private Integer identityTime;

/*    @Transient
    private Byte confidenceMmsi;

    @Transient
    private Byte confidenceShipName;

    @Transient
    private Byte confidenceCallSign;

    @Transient
    private Byte confidenceImoNumber;*/

    /**
     * 船舶和货物类型
     0=不可用或没有船舶=默认值
     1-99=如第3.3.2节的规定
     100-199=保留，用于区域性使用
     200-255=保留，用于将来使用
     不适用于SAR航空器
     */
    @Transient
    private Short shipType;

    /**
     * 船头距离
     */
    @Transient
    private Short toBow;

    /**
     * 船尾距离
     */
    @Transient
    private Short toStern;

    /**
     * 左舷距离
     */
    @Transient
    private Short toPort;

    /**
     * 右舷距离
     */
    @Transient
    private Short toStarboard;

    /**
     * 电子定位装置的类型
     -1:非法
     0=未规定（默认值）
     1=GPS
     2=GLONASS
     3=GPS/GLONASS组合
     4=Loran-C
     5=Chayka
     6=综合导航系统
     7=正在研究
     8=Galileo
     9-14=未使用
     15=内部 GNSS
     */
    @Transient
    private Byte fixingDevice;

    /**
     * IMO号是否有效
     0: 无效 1:有效
     */
    @Transient
    private Byte isImoNumberValid;


    @Column(name = "ship_length")
    private Integer shipLength;

    @Column(name = "ship_breadth")
    private Integer shipBreadth;

    @Column(name = "ukey")
    private String ukey;

    @Transient
    private String identityUkey;

    @Transient
    private Long identityId;
    @Transient
    private Location point;

    private static final long serialVersionUID = 1L;

    @Transient
    private Integer sourceId;

    @Transient
    private String countryFlag;

    /**
     * 国家中文名称
     */
    @Transient
    private String countryCnname;

    /**
     * 国家英文名称
     */
    @Transient
    private String countryEnname;
 /*   @Transient
    private Long identityCreatedTime;*/
    @Override
    public Object transfrom(JSONObject jsonObject, SearchHit hit) {
        L0CleanAisDynamicSouth o = (L0CleanAisDynamicSouth) JSONObject.toBean(jsonObject, L0CleanAisDynamicSouth.class);
        o.insertBase(hit);
        return o;
    }
}